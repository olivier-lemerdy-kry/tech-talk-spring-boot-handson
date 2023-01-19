package se.kry.techtalk.handson;

import static java.util.Objects.requireNonNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.jayway.jsonpath.JsonPath;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class ApplicationTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void GET_actuator_health_OK() throws Exception {
    mockMvc.perform(get("/actuator/health"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.status").value("UP"));
  }

  @Test
  void scenario() throws Exception {
    var id = scenario_step1_POST_payments();
    scenario_step2_GET_payments(id);
  }

  private UUID scenario_step1_POST_payments() throws Exception {
    String payload = """
        {
          "value": 10,
          "currency": "EUR"
        }
        """;

    var result = mockMvc.perform(post("/api/v1/payments")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .content(payload))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id").isNotEmpty())
        .andExpect(jsonPath("$.timestamp").value("1970-01-01T00:00:00Z"))
        .andExpect(jsonPath("$.amount.value").value(10))
        .andExpect(jsonPath("$.amount.currency").value("EUR"))
        .andReturn();

    try (InputStream stream = new ByteArrayInputStream(requireNonNull(result.getResponse().getContentAsByteArray()))) {
      return UUID.fromString(JsonPath.read(stream, "$.id"));
    }
  }

  private void scenario_step2_GET_payments(UUID id) throws Exception {
    mockMvc.perform(get("/api/v1/payments/{id}", id))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(id))
        .andExpect(jsonPath("$.timestamp").value("1970-01-01T00:00:00Z"))
        .andExpect(jsonPath("$.amount.value").value(10))
        .andExpect(jsonPath("$.amount.currency").value("EUR"));
  }

  @TestConfiguration
  static class ApplicationTestConfiguration {

    @Bean
    @Primary
    Clock fixedClock() {
      return Clock.fixed(Instant.EPOCH, ZoneId.systemDefault());
    }

  }

}
