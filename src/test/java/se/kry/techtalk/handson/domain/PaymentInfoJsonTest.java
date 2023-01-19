package se.kry.techtalk.handson.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

@JsonTest
class PaymentInfoJsonTest {

  @Autowired
  private JacksonTester<PaymentInfo> tester;

  @Test
  void serialize() throws IOException {
    var jsonContent = tester.write(PaymentFixtures.PAYMENT_INFO);

    assertThat(jsonContent).isEqualToJson("PaymentInfo.json");
  }

  @Test
  void deserialize() throws IOException {
    var payment = tester.readObject("PaymentInfo.json");

    assertThat(payment).isEqualTo(PaymentFixtures.PAYMENT_INFO);
  }

}