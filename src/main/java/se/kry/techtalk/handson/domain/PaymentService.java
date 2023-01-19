package se.kry.techtalk.handson.domain;

import java.time.Clock;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import se.kry.techtalk.handson.data.PaymentRepository;

@Service
@RequiredArgsConstructor
public class PaymentService {

  private final Clock clock;

  private final PaymentRepository repository;

  public PaymentInfo createPayment(Amount amount) {
    throw new IllegalArgumentException();
  }

  public Optional<PaymentInfo> getPayment(UUID id) {
    throw new IllegalArgumentException();
  }

  public Page<PaymentInfo> getPayments(Pageable pageable) {
    throw new IllegalArgumentException();
  }
}
