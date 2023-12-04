package com.iPizza.estabelecimento.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.iPizza.estabelecimento.dto.EstabelecimentoPostPutDTO;
import com.iPizza.estabelecimento.dto.EstabelecimentoResponseDTO;
import com.iPizza.estabelecimento.exception.CustomErrorType;
import com.iPizza.estabelecimento.model.Estabelecimento;
import com.iPizza.estabelecimento.repository.EstabelecimentoRepository;

import org.springframework.boot.test.context.SpringBootTest;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Testes do controlador de estabelecimentos")
public class EstabelecimentoControllerTests {
    @Autowired
    MockMvc driver;

    @Autowired
    EstabelecimentoRepository estabelecimentoRepository;

    ObjectMapper objectMapper = new ObjectMapper();

    Estabelecimento estabelecimento;

    @BeforeEach
    void setup() {
        objectMapper.registerModule(new JavaTimeModule());
        estabelecimento = estabelecimentoRepository.save(Estabelecimento.builder()
                .codigo("123456")
                .email("estabelecimento@email.com")
                .build());
    }

    @AfterEach
    void tearDown() {
        estabelecimentoRepository.deleteAll();
    }

    @Nested
    @DisplayName("Conjunto de casos de verificação dos fluxos básicos API Rest")
    class EstabelecimentoVerificacaoFluxosBasicosApiRest {
        final String URI_ESTABELECIMENTOS = "/estabelecimentos";
        EstabelecimentoPostPutDTO estabelecimentoPutRequestDTO;
        EstabelecimentoPostPutDTO estabelecimentoPostRequestDTO;

        @BeforeEach
        void setup() {
            estabelecimentoPutRequestDTO = EstabelecimentoPostPutDTO.builder()
                    .codigo("123456")
                    .email("estabelecimento@gmail.com")
                    .build();
            estabelecimentoPostRequestDTO = EstabelecimentoPostPutDTO.builder()
                    .codigo("654321")
                    .email("estabelecimento@gmail.com")
                    .build();
        }

        @Test
        @DisplayName("Quando criamos um novo estabelecimento com dados válidos")
        void quandoCriarEstabelecimentoValido() throws Exception {
            // Arrange
            // nenhuma necessidade além do setup()

            // Act
            String responseJsonString = driver.perform(post(URI_ESTABELECIMENTOS)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(estabelecimentoPostRequestDTO)))
                    .andExpect(status().isCreated()) // Codigo 201
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            EstabelecimentoResponseDTO resultado = objectMapper.readValue(responseJsonString, EstabelecimentoResponseDTO.EstabelecimentoResponseDTOBuilder.class).build();

            // Assert
            assertAll(
                    () -> assertNotNull(resultado.getId()),
                    () -> assertEquals(estabelecimentoPostRequestDTO.getCodigo(), resultado.getCodigo())
            );
        }

        @Test
        @DisplayName("Quando excluímos um estabelecimento salvo")
        void quandoExcluimosEstabelecimentoValido() throws Exception {
            // Arrange
            // nenhuma necessidade além do setup()

            // Act
            String responseJsonString = driver.perform(delete(URI_ESTABELECIMENTOS + "/" + estabelecimento.getId())
                            .contentType(MediaType.APPLICATION_JSON)
                            .param("codigo", estabelecimento.getCodigo()))
                    .andExpect(status().isNoContent()) // Codigo 204
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            // Assert
            assertTrue(responseJsonString.isBlank());
        }

        @Test
        @DisplayName("Quando atualizamos um estabelecimento salvo")
        void quandoAtualizamosEstabelecimentoValido() throws Exception {
            // Arrange
            estabelecimentoPutRequestDTO.setCodigo("131289");

            // Act
            String responseJsonString = driver.perform(put(URI_ESTABELECIMENTOS + "/" + estabelecimento.getId())
                            .contentType(MediaType.APPLICATION_JSON)
                            .param("codigo", estabelecimento.getCodigo())
                            .content(objectMapper.writeValueAsString(estabelecimentoPutRequestDTO)))
                    .andExpect(status().isOk()) // Codigo 200
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            EstabelecimentoResponseDTO resultado = objectMapper.readValue(responseJsonString, EstabelecimentoResponseDTO.EstabelecimentoResponseDTOBuilder.class).build();

            // Assert
            assertAll(
                    () -> assertEquals(resultado.getId().longValue(), estabelecimento.getId().longValue()),
                    () -> assertEquals("131289", resultado.getCodigo())
            );
        }

        @Test
        @DisplayName("Quando alteramos um estabelecimento com codigo de acesso inválido")
        void quandoAlterarEstabelecimentoInvalido() throws Exception {
            // Arrange
            EstabelecimentoPostPutDTO estabelecimentoPostPutDTO = EstabelecimentoPostPutDTO.builder()
                    .codigo("13")
                    .email("estabelecimento@gmail.com")
                    .build();

            // Act
            String responseJsonString = driver.perform(put(URI_ESTABELECIMENTOS + "/" + estabelecimento.getId())
                            .contentType(MediaType.APPLICATION_JSON)
                            .param("codigo", estabelecimento.getCodigo())
                            .content(objectMapper.writeValueAsString(estabelecimentoPostPutDTO)))
                    .andExpect(status().isBadRequest()) // Codigo 400
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            CustomErrorType resultado = objectMapper.readValue(responseJsonString, CustomErrorType.class);

            // Assert
            assertAll(
                    () -> assertEquals("Erros de validacao encontrados", resultado.getMessage()),
                    () -> assertEquals("O código de acesso deve ter exatamente 6 dígitos", resultado.getErrors().get(0))
            );
        }

        @Test
        @DisplayName("Quando criamos um novo estabelecimento com dados inválidos")
        void quandoCriarEstabelecimentoInvalido() throws Exception {
            // Arrange
            EstabelecimentoPostPutDTO estabelecimentoPostPutDTO = EstabelecimentoPostPutDTO.builder()
                    .codigo("13")
                    .email("estabelecimento@gmail.com")
                    .build();

            // Act
            String responseJsonString = driver.perform(post(URI_ESTABELECIMENTOS)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(estabelecimentoPostPutDTO)))
                    .andExpect(status().isBadRequest()) // Codigo 400
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            CustomErrorType resultado = objectMapper.readValue(responseJsonString, CustomErrorType.class);

            // Assert
            assertAll(
                    () -> assertEquals("Erros de validacao encontrados", resultado.getMessage()),
                    () -> assertEquals("O código de acesso deve ter exatamente 6 dígitos", resultado.getErrors().get(0))
            );
        }
/*
        @Test
        @DisplayName("Quando buscamos o cardapio de um estabelecimento")
        void quandoBuscarCardapioEstabelecimento() throws Exception {
            // Arrange
            Sabor sabor1 = Sabor.builder()
                    .nome("Calabresa")
                    .precoM(25.0)
                    .precoG(35.0)
                    .tipo("salgado")
                    .build();

            Sabor sabor2 = Sabor.builder()
                    .nome("Mussarela")
                    .precoM(20.0)
                    .precoG(30.0)
                    .tipo("salgado")
                    .build();
            Sabor sabor3 = Sabor.builder()
                    .nome("Chocolate")
                    .precoM(25.0)
                    .precoG(35.0)
                    .tipo("doce")
                    .build();

            Sabor sabor4 = Sabor.builder()
                    .nome("Morango")
                    .precoM(20.0)
                    .precoG(30.0)
                    .tipo("doce")
                    .build();
            Estabelecimento estabelecimento1 = Estabelecimento.builder()
                    .codigo("123456")
                    .sabores(Set.of(sabor1, sabor2, sabor3, sabor4))
                    .build();
            estabelecimentoRepository.save(estabelecimento1);

            // Act
            String responseJsonString = driver.perform(get(URI_ESTABELECIMENTOS + "/" + estabelecimento1.getId() + "/sabores")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(estabelecimentoPostRequestDTO)))
                    .andExpect(status().isOk()) // Codigo 200
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            List<SaborResponseDTO> resultado = objectMapper.readValue(responseJsonString, new TypeReference<>() {
            });

            // Assert
            assertAll(
                    () -> assertEquals(4, resultado.size())
            );
        }

        @Test
        @DisplayName("Quando buscamos o cardapio de um estabelecimento que não existe")
        void quandoBuscarCardapioEstabelecimentoInexistente() throws Exception {
            // Arrange
            // Nenhuma necessidade além do setup()

            // Act
            String responseJsonString = driver.perform(get(URI_ESTABELECIMENTOS + "/" + 9999 + "/sabores")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(estabelecimentoPostRequestDTO)))
                    .andExpect(status().isBadRequest()) // Codigo 404
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            CustomErrorType resultado = objectMapper.readValue(responseJsonString, CustomErrorType.class);

            // Assert
            assertAll(
                    () -> assertEquals("O estabelecimento consultado nao existe!", resultado.getMessage())
            );
        }

        @Test
        @DisplayName("Quando buscamos o cardapio de um estabelecimento por tipo (salgado)")
        void quandoBuscarCardapioEstabelecimentoPorTipo() throws Exception {
            // Arrange
            Sabor sabor1 = Sabor.builder()
                    .nome("Calabresa")
                    .precoM(25.0)
                    .precoG(35.0)
                    .tipo("salgado")
                    .build();

            Sabor sabor2 = Sabor.builder()
                    .nome("Mussarela")
                    .precoM(20.0)
                    .precoG(30.0)
                    .tipo("salgado")
                    .build();
            Sabor sabor3 = Sabor.builder()
                    .nome("Chocolate")
                    .precoM(25.0)
                    .precoG(35.0)
                    .tipo("doce")
                    .build();

            Sabor sabor4 = Sabor.builder()
                    .nome("Morango")
                    .precoM(20.0)
                    .precoG(30.0)
                    .tipo("doce")
                    .build();
            Estabelecimento estabelecimento1 = Estabelecimento.builder()
                    .codigo("123456")
                    .sabores(Set.of(sabor1, sabor2, sabor3, sabor4))
                    .build();
            estabelecimentoRepository.save(estabelecimento1);

            // Act
            String responseJsonString = driver.perform(get(URI_ESTABELECIMENTOS + "/" + estabelecimento1.getId() + "/sabores" + "/tipo")
                            .contentType(MediaType.APPLICATION_JSON)
                            .param("tipo", "salgado")
                            .content(objectMapper.writeValueAsString(estabelecimentoPostRequestDTO)))
                    .andExpect(status().isOk()) // Codigo 200
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            List<SaborResponseDTO> resultado = objectMapper.readValue(responseJsonString, new TypeReference<>() {
            });

            // Assert
            assertAll(
                    () -> assertEquals(2, resultado.size())
            );
        }

        @Test
        @DisplayName("Quando buscamos o cardapio de um estabelecimento por tipo (doce)")
        void quandoBuscarCardapioEstabelecimentoPorTipoDoce() throws Exception {
            // Arrange
            Sabor sabor1 = Sabor.builder()
                    .nome("Calabresa")
                    .precoM(25.0)
                    .precoG(35.0)
                    .tipo("salgado")
                    .build();

            Sabor sabor2 = Sabor.builder()
                    .nome("Mussarela")
                    .precoM(20.0)
                    .precoG(30.0)
                    .tipo("salgado")
                    .build();
            Sabor sabor3 = Sabor.builder()
                    .nome("Chocolate")
                    .precoM(25.0)
                    .precoG(35.0)
                    .tipo("doce")
                    .build();

            Sabor sabor4 = Sabor.builder()
                    .nome("Morango")
                    .precoM(20.0)
                    .precoG(30.0)
                    .tipo("doce")
                    .build();
            Estabelecimento estabelecimento1 = Estabelecimento.builder()
                    .codigo("123456")
                    .sabores(Set.of(sabor1, sabor2, sabor3, sabor4))
                    .build();
            estabelecimentoRepository.save(estabelecimento1);

            // Act
            String responseJsonString = driver.perform(get(URI_ESTABELECIMENTOS + "/" + estabelecimento1.getId() + "/sabores" + "/tipo")
                            .contentType(MediaType.APPLICATION_JSON)
                            .param("tipo", "doce")
                            .content(objectMapper.writeValueAsString(estabelecimentoPostRequestDTO)))
                    .andExpect(status().isOk()) // Codigo 200
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            List<SaborResponseDTO> resultado = objectMapper.readValue(responseJsonString, new TypeReference<>() {
            });

            // Assert
            assertAll(
                    () -> assertEquals(2, resultado.size())
            );
        }*/
    }
}