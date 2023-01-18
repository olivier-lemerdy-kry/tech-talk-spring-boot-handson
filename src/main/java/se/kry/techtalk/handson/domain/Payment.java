package se.kry.techtalk.handson.domain;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import java.time.Instant;
import java.util.UUID;

public record Payment(@NotNull UUID id, @NotNull @Past Instant timestamp, @NotNull Amount amount) {
}
