package se.kry.techtalk.handson.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

@JsonTest
class AmountJsonTest {

  @Autowired
  private JacksonTester<Amount> tester;

  @Test
  void serialize() throws IOException {
    var jsonContent = tester.write(AmountFixtures.AMOUNT);

    assertThat(jsonContent).isEqualToJson("Amount.json");
  }

  @Test
  void deserialize() throws IOException {
    var amount = tester.readObject("Amount.json");

    assertThat(amount).isEqualTo(AmountFixtures.AMOUNT);
  }

}