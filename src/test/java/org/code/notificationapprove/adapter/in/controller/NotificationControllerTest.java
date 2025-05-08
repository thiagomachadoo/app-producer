package org.code.notificationapprove.adapter.in.controller;

import org.code.notificationapprove.adapter.out.entities.*;
import org.code.notificationapprove.adapter.out.repositories.*;
import org.code.notificationapprove.application.port.repositories.*;
import org.junit.jupiter.api.*;
import org.springframework.amqp.rabbit.core.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.context.*;
import org.springframework.http.*;
import org.springframework.test.context.*;
import org.springframework.test.web.servlet.*;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
//@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class NotificationControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private NotificationRepository repository;

  private NotificationEntity savedData;

  @BeforeEach
  void setup() {
    repository.deleteAll();

    NotificationEntity personOne = new NotificationEntity();
    personOne.setName("Marco");
    personOne.setLastName("Silvio");
    personOne.setAge(21);
    personOne.setCountry("Brasil");

    savedData = repository.save(personOne);

    NotificationEntity personTwo = new NotificationEntity();
    personTwo.setName("Maria");
    personTwo.setLastName("Antonia");
    personTwo.setAge(45);
    personTwo.setCountry("EUA");

    repository.save(personTwo);
  }

  @Test
  @DisplayName("Deve salvar uma nova notificacao.")
  void shouldTrySaveNewNotification() throws Exception {
    String requestBody = """
                {
        "name": "John",
        "lastName": "Doe",
        "age": 44,
        "country": "Canada"
                }
        """;

    mockMvc.perform(post("/notify")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestBody))
        .andExpect(status().isAccepted());

    List<NotificationEntity> all = repository.findAll();

    assertThat(all).hasSize(3);
  }

  @Test
  @DisplayName("Deve buscar todas as insercoes no banco de dados.")
  void shouldSearchAllValuesInDatabase() throws Exception {

    mockMvc.perform(get("/notify/all")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());

    List<NotificationEntity> all = repository.findAll();

    assertThat(all).hasSize(2);
  }

  @Test
  @DisplayName("Deve buscar conta existente.")
  void shouldReturnValidAccount() throws Exception {

    mockMvc.perform(get("/notify")
            .param("id", savedData.getId())
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name").value("Marco"))
        .andExpect(jsonPath("$.lastName").value("Silvio"))
        .andExpect(jsonPath("$.age").value("21"));
  }

  @Test
  @DisplayName("Deve alterar conta existente.")
  void shouldChangeValidAccount() throws Exception {

    String requestBody = """
                {
        "name": "John",
        "lastName": "Doe",
        "age": 44,
        "country": "Canada"
                }
        """;

    mockMvc.perform(patch("/notify")
            .param("id", savedData.getId())
            .content(requestBody)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name").value("John"))
        .andExpect(jsonPath("$.lastName").value("Doe"))
        .andExpect(jsonPath("$.age").value("44"));
  }

  @Test
  @DisplayName("Deve retornar erro 500 ao tentar salvar uma mensagem na fila.")
  void shouldReturn404ForChangeInValidAccount() throws Exception {

    String requestBody = """
                {
        "name": "John",
        "lastName": "Doe",
        "age": 44,
        "country": "Canada"
                }
        """;

    mockMvc.perform(patch("/notify")
            .param("id", "3044589903")
            .content(requestBody)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
  }

  @Test
  @DisplayName("Deve excluir conta existente.")
  void shouldDeleteValidAccount() throws Exception {

    mockMvc.perform(delete("/notify")
            .param("id", savedData.getId())
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNoContent());
  }

  @Test
  @DisplayName("Deve retornar 404 ao tentar encontrar conta inexistente.")
  void shouldReturn404ForInValidAccount() throws Exception {

    mockMvc.perform(delete("/notify")
            .param("id", "758348092394578")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().is4xxClientError());
  }
}