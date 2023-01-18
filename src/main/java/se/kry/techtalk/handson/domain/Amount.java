package se.kry.techtalk.handson.domain;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.Currency;

public record Amount(@NotNull @Positive BigDecimal value, @NotNull Currency currency) {
}
