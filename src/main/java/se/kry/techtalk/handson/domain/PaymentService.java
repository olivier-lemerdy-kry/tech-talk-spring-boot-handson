package se.kry.techtalk.handson.domain;

import jakarta.validation.constraints.NotNull;
import java.time.Clock;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import se.kry.techtalk.handson.data.Payment;
import se.kry.techtalk.handson.data.PaymentRepository;

@Service
@RequiredArgsConstructor
public class PaymentService {

  private final Clock clock;

  private final PaymentRepository repository;

  public PaymentInfo createPayment(@NotNull Amount amount) {
    var toSave = Payment.builder()
        .timestamp(clock.instant())
        .amountValue(amount.value())
        .amountCurrency(amount.currency())
        .build();
    var saved = repository.save(toSave);
    return new PaymentInfo(
        saved.getId(),
        saved.getTimestamp(),
        new Amount(
            saved.getAmountValue(),
            saved.getAmountCurrency()));
  }

  public Optional<PaymentInfo> getPayment(@NotNull UUID id) {
    throw new IllegalArgumentException();
  }

  public Page<PaymentInfo> getPayments(@NotNull Pageable pageable) {
    throw new IllegalArgumentException();
  }
}
