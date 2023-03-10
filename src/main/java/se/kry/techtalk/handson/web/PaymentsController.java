package se.kry.techtalk.handson.web;

import jakarta.validation.Valid;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import se.kry.techtalk.handson.domain.Amount;
import se.kry.techtalk.handson.domain.PaymentInfo;
import se.kry.techtalk.handson.domain.PaymentService;

@RestController
@RequestMapping("api/v1/payments")
@RequiredArgsConstructor
public class PaymentsController {

  private final PaymentService service;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  PaymentInfo createPayment(@RequestBody @Valid Amount amount) {
    return service.createPayment(amount);
  }

  @GetMapping("{id}")
  ResponseEntity<PaymentInfo> getPayment(@PathVariable UUID id) {
    return ResponseEntity.of(service.getPayment(id));
  }

  @GetMapping
  Page<PaymentInfo> getPayments(Pageable pageable) {
    return service.getPayments(pageable);
  }
}
