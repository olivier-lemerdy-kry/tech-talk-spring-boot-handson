package se.kry.techtalk.handson.domain;

import java.time.Instant;
import java.util.UUID;

public interface PaymentFixtures {

  String ID_STRING = "4e1c772a-24b8-4444-92cb-842d7c5f1a18";

  UUID ID = UUID.fromString(ID_STRING);

  Instant TIMESTAMP = Instant.EPOCH;

  Amount AMOUNT = AmountFixtures.AMOUNT;

  PaymentInfo PAYMENT_INFO = new PaymentInfo(ID, TIMESTAMP, AMOUNT);
}
