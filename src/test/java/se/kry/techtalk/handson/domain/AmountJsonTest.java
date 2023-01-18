package se.kry.techtalk.handson.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Currency;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

@JsonTest
class AmountJsonTest {

  private static final Currency EUR = Currency.getInstance("EUR");

  @Autowired
  private JacksonTester<Amount> tester;

  @Test
  void serialize() throws IOException {
    var amount = new Amount(BigDecimal.TEN, EUR);

    var jsonContent = tester.write(amount);

    assertThat(jsonContent).isEqualToJson("Amount.json");
  }

  @Test
  void deserialize() throws IOException {
    var amount = tester.readObject("Amount.json");

    assertThat(amount.value()).isEqualTo(BigDecimal.TEN);
    assertThat(amount.currency()).isEqualTo(EUR);
  }

}