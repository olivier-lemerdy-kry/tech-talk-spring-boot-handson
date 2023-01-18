package se.kry.techtalk.handson.domain;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

  public Payment createPayment(Amount amount) {
    throw new IllegalArgumentException();
  }

  public Optional<Payment> getPayment(UUID id) {
    throw new IllegalArgumentException();
  }

  public Page<Payment> getPayments(Pageable pageable) {
    throw new IllegalArgumentException();
  }
}
