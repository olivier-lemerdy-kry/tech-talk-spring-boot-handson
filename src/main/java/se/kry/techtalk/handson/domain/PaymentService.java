package se.kry.techtalk.handson.domain;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PaymentService {

  Payment createPayment(Amount amount);

  Optional<Payment> getPayment(UUID id);

  Page<Payment> getPayments(Pageable pageable);
}
