package se.kry.techtalk.handson.web;

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
}
