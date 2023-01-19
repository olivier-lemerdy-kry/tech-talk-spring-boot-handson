package se.kry.techtalk.handson.web;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import se.kry.techtalk.handson.domain.AmountFixtures;
import se.kry.techtalk.handson.domain.PaymentFixtures;
import se.kry.techtalk.handson.domain.PaymentService;

@WebMvcTest
class PaymentsControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private PaymentService service;

  @Test
  void POST_payments_OK() throws Exception {
    when(service.createPayment(AmountFixtures.AMOUNT))
        .thenReturn(PaymentFixtures.PAYMENT);

    String payload = """
        {
          "value": 10,
          "currency": "EUR"
        }
        """;

    mockMvc.perform(post("/api/v1/payments")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .content(payload))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id").value(PaymentFixtures.ID_STRING))
        .andExpect(jsonPath("$.timestamp").value("1970-01-01T00:00:00Z"))
        .andExpect(jsonPath("$.amount.value").value(10))
        .andExpect(jsonPath("$.amount.currency").value("EUR"));
  }

  @Test
  void POST_payments_no_currency_BAD_REQUEST() throws Exception {
    String payload = """
        {
          "value": 10
        }
        """;

    mockMvc.perform(post("/api/v1/payments")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .content(payload))
        .andExpect(status().isBadRequest());
  }

  @Test
  void POST_payments_negative_value_BAD_REQUEST() throws Exception {
    String payload = """
        {
          "value": -10,
          "currency": "EUR"
        }
        """;

    mockMvc.perform(post("/api/v1/payments")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .content(payload))
        .andExpect(status().isBadRequest());
  }

  @Test
  void GET_payments_empty_OK() throws Exception {
    when(service.getPayments(Pageable.ofSize(20)))
        .thenReturn(Page.empty());

    mockMvc.perform(get("/api/v1/payments"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.totalElements").value(0))
        .andExpect(jsonPath("$.totalPages").value(1));
  }

  @Test
  void GET_payments_OK() throws Exception {
    when(service.getPayments(Pageable.ofSize(20)))
        .thenReturn(new PageImpl<>(List.of(PaymentFixtures.PAYMENT)));

    mockMvc.perform(get("/api/v1/payments"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.totalElements").value(1))
        .andExpect(jsonPath("$.totalPages").value(1));
  }

  @Test
  void GET_payment_ID_OK() throws Exception {
    when(service.getPayment(PaymentFixtures.ID))
        .thenReturn(Optional.of(PaymentFixtures.PAYMENT));

    mockMvc.perform(get("/api/v1/payments/{id}", PaymentFixtures.ID_STRING))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(PaymentFixtures.ID_STRING))
        .andExpect(jsonPath("$.timestamp").value("1970-01-01T00:00:00Z"))
        .andExpect(jsonPath("$.amount.value").value(10))
        .andExpect(jsonPath("$.amount.currency").value("EUR"));
  }

  @Test
  void GET_payment_ID_not_found_BAD_REQUEST() throws Exception {
    when(service.getPayments(Pageable.ofSize(20)))
        .thenReturn(Page.empty());

    mockMvc.perform(get("/api/v1/payments/{id}", PaymentFixtures.ID))
        .andExpect(status().isNotFound());
  }

}