package se.kry.techtalk.handson.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Currency;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

@JsonTest
class PaymentJsonTest {

  private static final UUID PAYMENT_ID = UUID.fromString("4e1c772a-24b8-4444-92cb-842d7c5f1a18");
  private static final Currency EUR = Currency.getInstance("EUR");

  @Autowired
  private JacksonTester<Payment> tester;

  @Test
  void serialize() throws IOException {
    var payment = new Payment(
        PAYMENT_ID,
        Instant.EPOCH,
        new Amount(
            BigDecimal.TEN,
            EUR));

    var jsonContent = tester.write(payment);

    assertThat(jsonContent).isEqualToJson("Payment.json");
  }

  @Test
  void deserialize() throws IOException {
    var payment = tester.readObject("Payment.json");

    assertThat(payment.id()).isEqualTo(PAYMENT_ID);
    assertThat(payment.timestamp()).isEqualTo(Instant.EPOCH);
    assertThat(payment.amount().value()).isEqualTo(BigDecimal.TEN);
    assertThat(payment.amount().currency()).isEqualTo(EUR);
  }

}