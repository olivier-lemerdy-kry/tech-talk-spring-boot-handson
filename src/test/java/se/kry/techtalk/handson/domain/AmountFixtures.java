package se.kry.techtalk.handson.domain;

import java.math.BigDecimal;
import java.util.Currency;

public interface AmountFixtures {

  BigDecimal VALUE = BigDecimal.TEN;

  Currency CURRENCY = Currency.getInstance("EUR");

  Amount AMOUNT = new Amount(VALUE, CURRENCY);

}
