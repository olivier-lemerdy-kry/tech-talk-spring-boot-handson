package se.kry.techtalk.handson.web;

import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.kry.techtalk.handson.domain.Amount;
import se.kry.techtalk.handson.domain.Payment;

@RestController
@RequestMapping("api/v1/payments")
public class PaymentsController {

  @PostMapping
  Payment createPayment(Amount amount) {
    throw new IllegalStateException();
  }

  @GetMapping("{id}")
  Payment getPayment(@PathVariable UUID id) {
    throw new IllegalArgumentException();
  }

  @GetMapping
  Page<Payment> getPayments(Pageable pageable) {
    throw new IllegalArgumentException();
  }
}
