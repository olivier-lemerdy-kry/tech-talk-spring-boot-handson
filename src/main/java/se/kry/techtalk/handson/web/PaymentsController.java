package se.kry.techtalk.handson.web;

import jakarta.validation.Valid;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.kry.techtalk.handson.domain.Amount;
import se.kry.techtalk.handson.domain.Payment;
import se.kry.techtalk.handson.domain.PaymentService;

@RestController
@RequestMapping("api/v1/payments")
@RequiredArgsConstructor
public class PaymentsController {

  private final PaymentService service;

  @PostMapping
  Payment createPayment(@Valid Amount amount) {
    return service.createPayment(amount);
  }

  @GetMapping("{id}")
  Optional<Payment> getPayment(@PathVariable UUID id) {
    return service.getPayment(id);
  }

  @GetMapping
  Page<Payment> getPayments(Pageable pageable) {
    return service.getPayments(pageable);
  }
}
