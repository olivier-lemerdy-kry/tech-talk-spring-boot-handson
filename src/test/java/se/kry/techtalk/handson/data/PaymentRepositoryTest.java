package se.kry.techtalk.handson.data;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import jakarta.validation.ValidationException;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Currency;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest
@Transactional
class PaymentRepositoryTest {

  private static final Currency EUR = Currency.getInstance("EUR");

  @Autowired
  private TestEntityManager testEntityManager;

  @Autowired
  private PaymentRepository repository;

  @Test
  void save_OK() {
    var toSave = new Payment.PaymentBuilder()
        .timestamp(Instant.EPOCH)
        .amountValue(BigDecimal.TEN)
        .amountCurrency(EUR)
        .build();

    assertThat(toSave.isNew()).isTrue();

    var saved = repository.save(toSave);

    assertThat(saved.isNew()).isFalse();
    assertThat(saved.getTimestamp()).isEqualTo(Instant.EPOCH);
    assertThat(saved.getAmountValue()).isEqualTo(BigDecimal.TEN);
    assertThat(saved.getAmountCurrency()).isEqualTo(EUR);

    testEntityManager.flush();
  }

  @Test
  void save_no_currency_KO() {
    var toSave = new Payment.PaymentBuilder()
        .timestamp(Instant.EPOCH)
        .amountValue(BigDecimal.TEN)
        .build();

    repository.save(toSave);

    assertThatThrownBy(() -> testEntityManager.flush())
        .isInstanceOf(ValidationException.class);
  }

}