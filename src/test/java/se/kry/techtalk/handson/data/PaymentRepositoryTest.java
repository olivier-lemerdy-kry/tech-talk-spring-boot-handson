package se.kry.techtalk.handson.data;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import jakarta.validation.ValidationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.transaction.annotation.Transactional;
import se.kry.techtalk.handson.domain.AmountFixtures;
import se.kry.techtalk.handson.domain.PaymentFixtures;

@DataJpaTest
@Transactional
class PaymentRepositoryTest {

  @Autowired
  private TestEntityManager testEntityManager;

  @Autowired
  private PaymentRepository repository;

  @Test
  void save_OK() {
    var toSave = new Payment.PaymentBuilder()
        .timestamp(PaymentFixtures.TIMESTAMP)
        .amountValue(AmountFixtures.VALUE)
        .amountCurrency(AmountFixtures.CURRENCY)
        .build();

    assertThat(toSave.isNew()).isTrue();

    var saved = repository.save(toSave);

    assertThat(saved.isNew()).isFalse();
    assertThat(saved.getTimestamp()).isEqualTo(PaymentFixtures.TIMESTAMP);
    assertThat(saved.getAmountValue()).isEqualTo(AmountFixtures.VALUE);
    assertThat(saved.getAmountCurrency()).isEqualTo(AmountFixtures.CURRENCY);

    testEntityManager.flush();
  }

  @Test
  void save_no_currency_KO() {
    var toSave = new Payment.PaymentBuilder()
        .timestamp(PaymentFixtures.TIMESTAMP)
        .amountValue(AmountFixtures.VALUE)
        .build();

    repository.save(toSave);

    assertThatThrownBy(() -> testEntityManager.flush())
        .isInstanceOf(ValidationException.class);
  }

}