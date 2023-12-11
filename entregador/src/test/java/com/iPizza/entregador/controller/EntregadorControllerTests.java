package com.iPizza.entregador.controller;

import java.util.UUID;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.iPizza.entregador.dto.EntregadorDeleteRequestDTO;
import com.iPizza.entregador.dto.EntregadorPostGetPutRequestDTO;
import com.iPizza.entregador.dto.EntregadorResponseDTO;
import com.iPizza.entregador.exception.CustomErrorType;
import com.iPizza.entregador.model.Entregador;
import com.iPizza.entregador.repository.EntregadorRepository;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Testes do controlador de Entregadores")
public class EntregadorControllerTests {

    final String URI_ENTREGADORES = "/entregadores";

    @Autowired
    private MockMvc driver;
	
    @Autowired
    private EntregadorRepository entregadorRepository;

    private ObjectMapper objectMapper = new ObjectMapper();

    private Entregador entregador;

    private EntregadorPostGetPutRequestDTO entregadorPostGetPutRequestDTO;

    private EntregadorResponseDTO entregadorDTO;

    @BeforeEach
    void setup() {
        objectMapper.registerModule(new JavaTimeModule());
        entregador = entregadorRepository.save(Entregador.builder()
                .nome("Lana Del Rey")
                .placaVeiculo("ABC-1234")
                .corVeiculo("Azul")
                .tipoVeiculo("moto")
                .codigo("123456")
                .build()
        );
        entregadorPostGetPutRequestDTO = EntregadorPostGetPutRequestDTO.builder()
                .nome(entregador.getNome())
                .codigo(entregador.getCodigo())
                .placaVeiculo(entregador.getPlacaVeiculo())
                .corVeiculo(entregador.getCorVeiculo())
                .tipoVeiculo(entregador.getTipoVeiculo())
                .build();

        entregadorDTO = new EntregadorResponseDTO(entregador);

    }

    @AfterEach
    void tearDown() {
        entregadorRepository.deleteAll();
    }

    @Nested
    @DisplayName("Conjunto de casos de verificação de fluxos básicos API Rest")
    class EntregadorVerificacaoFluxosBasicosApiRest {

        @Test
        @DisplayName("Quando buscamos por todos entregadores salvos")
        void quandoBuscamosTodosEntregadores() throws Exception {
            // Arrange
            // Vamos ter 3 entregadores no banco
            Entregador entregador1 = Entregador.builder()
                    .nome("Jose")
                    .placaVeiculo("GHF-1212")
                    .corVeiculo("Prata")
                    .tipoVeiculo("carro")
                    .codigo("654321")
                    .build();
            Entregador entregador2 = Entregador.builder()
                    .nome("Halloran")
                    .placaVeiculo("MRD-0217")
                    .corVeiculo("Preto")
                    .tipoVeiculo("carro")
                    .codigo("217217")
                    .build();
					
            entregadorRepository.saveAll(Arrays.asList(entregador1, entregador2));

            // Act
            String responseJsonString = driver.perform(get(URI_ENTREGADORES)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(entregadorPostGetPutRequestDTO)))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            List<EntregadorResponseDTO> resultado = objectMapper.readValue(responseJsonString, new TypeReference<>() {
            });

            // Assert
            assertEquals(3, resultado.size());
        }

        @Test
        @DisplayName("Quando buscamos por um entregador salvo pelo id")
        void quandoBuscamosEntregadorPorId() throws Exception {
            // Arrange
            // nenhuma necessidade além do setup()

            // Act
            String responseJsonString = driver.perform(get(URI_ENTREGADORES + "/" + entregador.getId())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(entregadorPostGetPutRequestDTO)))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            EntregadorResponseDTO resultado = objectMapper.readValue(responseJsonString, EntregadorResponseDTO.class);

            // Assert
            assertAll(
                    () -> assertEquals(entregadorDTO.getId(), resultado.getId()),
                    () -> assertEquals(entregadorDTO.getNome(), resultado.getNome()),
                    () -> assertEquals(entregadorDTO.getPlacaVeiculo(), resultado.getPlacaVeiculo()),
                    () -> assertEquals(entregadorDTO.getCorVeiculo(), resultado.getCorVeiculo()),
                    () -> assertEquals(entregadorDTO.getTipoVeiculo(), resultado.getTipoVeiculo())
            );
        }

        @Test
        @DisplayName("Quando buscamos um entregador inexistente")
        void quandoBuscamosEntregadorInexistente() throws Exception {
            // Arrange
            String idIncorreto = "550e8400-e29b-41d4-a716-446655440000";

            // Act
            String responseJsonString = driver.perform(get(URI_ENTREGADORES + "/" + idIncorreto)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(entregadorPostGetPutRequestDTO)))
                    .andExpect(status().isNotFound())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            CustomErrorType resultado = objectMapper.readValue(responseJsonString, CustomErrorType.class);

            // Assert
            assertEquals("Entregador não encontrado", resultado.getMessage());
        }

        @Test
        @DisplayName("Quando criamos um entregador com dados válidos")
        void quandoCriamosEntregadorValido() throws Exception {
            // Arrange
            // nenhuma necessidade além do setup()

            // Act
            String responseJsonString = driver.perform(post(URI_ENTREGADORES)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(entregadorPostGetPutRequestDTO)))
                    .andExpect(status().isCreated())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            EntregadorResponseDTO resultado = objectMapper.readValue(responseJsonString, EntregadorResponseDTO.class);

            // Assert
            assertAll(
                    () -> assertEquals(entregadorPostGetPutRequestDTO.getNome(), resultado.getNome()),
                    () -> assertEquals(entregadorPostGetPutRequestDTO.getPlacaVeiculo(), resultado.getPlacaVeiculo()),
                    () -> assertEquals(entregadorPostGetPutRequestDTO.getCorVeiculo(), resultado.getCorVeiculo()),
                    () -> assertEquals(entregadorPostGetPutRequestDTO.getTipoVeiculo(), resultado.getTipoVeiculo())
            );
        }

        @Test
        @DisplayName("Quando alteramos o entregador com dados válidos")
        void quandoAlteramosEntregadorValido() throws Exception {
            // Arrange
            UUID entregadorId = entregador.getId();

            // Act
            String responseJsonString = driver.perform(put(URI_ENTREGADORES + "/" + entregador.getId())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(entregadorPostGetPutRequestDTO)))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            EntregadorResponseDTO resultado = objectMapper.readValue(responseJsonString, EntregadorResponseDTO.class);

            // Assert
            assertAll(
                    () -> assertEquals(entregadorId, resultado.getId()),
                    () -> assertEquals(entregadorPostGetPutRequestDTO.getNome(), resultado.getNome()),
                    () -> assertEquals(entregadorPostGetPutRequestDTO.getPlacaVeiculo(), resultado.getPlacaVeiculo()),
                    () -> assertEquals(entregadorPostGetPutRequestDTO.getCorVeiculo(), resultado.getCorVeiculo()),
                    () -> assertEquals(entregadorPostGetPutRequestDTO.getTipoVeiculo(), resultado.getTipoVeiculo())
            );
        }

        @Test
        @DisplayName("Quando alteramos um entregador inexistente")
        void quandoAlteramosEntregadorInexistente() throws Exception {
            // Arrange
            String idIncorreto = "550e8400-e29b-41d4-a716-446655440000";

            // Act
            String responseJsonString = driver.perform(put(URI_ENTREGADORES + "/" + idIncorreto)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(entregadorPostGetPutRequestDTO)))
                    .andExpect(status().isNotFound())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            CustomErrorType resultado = objectMapper.readValue(responseJsonString, CustomErrorType.class);

            // Assert
            assertEquals("Entregador não encontrado", resultado.getMessage());
        }

        @Test
        @DisplayName("Quando alteramos um entregador passando um código de acesso inválido")
        void quandoAlteramosEntregadorComCodigoAcessoInvalido() throws Exception {
            // Arrange
            entregadorPostGetPutRequestDTO.setCodigo("8as54a");

            // Act
            String responseJsonString = driver.perform(put(URI_ENTREGADORES + "/" + entregador.getId())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(entregadorPostGetPutRequestDTO)))
                    .andExpect(status().isBadRequest())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            CustomErrorType resultado = objectMapper.readValue(responseJsonString, CustomErrorType.class);

            // Assert
            assertEquals("Código de acesso do entregador é inválido", resultado.getMessage());
        }

        @Test
        @DisplayName("Quando excluímos um entregador salvo")
        void quandoExcluimosEntregadorSalvo() throws Exception {
            // Arrange
            EntregadorDeleteRequestDTO entregadorDeleteRequestDTO = EntregadorDeleteRequestDTO.builder()
                    .codigo("123456")
                    .build();

            // Act
            String responseJsonString = driver.perform(delete(URI_ENTREGADORES + "/" + entregador.getId())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(entregadorDeleteRequestDTO)))
                    .andExpect(status().isNoContent())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            // Assert
            assertTrue(responseJsonString.isBlank());
        }

        @Test
        @DisplayName("Quando excluímos um entregador inexistente")
        void quandoExcluimosEntregadorInexistente() throws Exception {
            // Arrange
            EntregadorDeleteRequestDTO entregadorDeleteRequestDTO = EntregadorDeleteRequestDTO.builder()
                    .codigo("123456")
                    .build();

            String idIncorreto = "550e8400-e29b-41d4-a716-446655440000";

            // Act
            String responseJsonString = driver.perform(delete(URI_ENTREGADORES + "/" + idIncorreto)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(entregadorDeleteRequestDTO)))
                    .andExpect(status().isNotFound())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            CustomErrorType resultado = objectMapper.readValue(responseJsonString, CustomErrorType.class);

            // Assert
            assertEquals("Entregador não encontrado", resultado.getMessage());
        }

        @Test
        @DisplayName("Quando excluímos um entregador passando um código de acesso inválido")
        void quandoExcluimosEntregadorComCodigoAcessoInvalido() throws Exception {
            // Arrange
            EntregadorDeleteRequestDTO entregadorDeleteRequestDTO = EntregadorDeleteRequestDTO.builder()
                    .codigo("154744")
                    .build();

            // Act
            String responseJsonString = driver.perform(delete(URI_ENTREGADORES + "/" + entregador.getId())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(entregadorDeleteRequestDTO)))
                    .andExpect(status().isBadRequest())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            CustomErrorType resultado = objectMapper.readValue(responseJsonString, CustomErrorType.class);

            // Assert
            assertEquals("Código de acesso do entregador é inválido", resultado.getMessage());
        }
    }


    @Nested
    @DisplayName("Conjunto de casos de verificação de nome")
    class EntregadorVerificacaoNome {

        @Test
        @DisplayName("Quando alteramos o nome do entregador com dados válidos")
        void quandoAlteramosNomeDoEntregadorValido() throws Exception {
            // Arrange
            entregadorPostGetPutRequestDTO.setNome("Lana Del Rey Alterada");

            // Act
            String responseJsonString = driver.perform(put(URI_ENTREGADORES + "/" + entregador.getId())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(entregadorPostGetPutRequestDTO)))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            EntregadorResponseDTO resultado = objectMapper.readValue(responseJsonString, EntregadorResponseDTO.class);

            // Assert
            assertEquals("Lana Del Rey Alterada", resultado.getNome());
        }

        @Test
        @DisplayName("Quando alteramos o entregador com nome vazio")
        void quandoAlteramosEntregadorComNomeVazio() throws Exception {
            // Arrange
            entregadorPostGetPutRequestDTO.setNome("");

            // Act
            String responseJsonString = driver.perform(put(URI_ENTREGADORES + "/" + entregador.getId())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(entregadorPostGetPutRequestDTO)))
                    .andExpect(status().isBadRequest())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            CustomErrorType resultado = objectMapper.readValue(responseJsonString, CustomErrorType.class);

            // Assert
            assertAll(
                    () -> assertEquals("Erros de validacao encontrados", resultado.getMessage()),
                    () -> assertEquals("Nome e obrigatorio", resultado.getErrors().get(0))
            );
        }

        @Test
        @DisplayName("Quando alteramos o entregador passando codigo de acesso inválido")
        void quandoAlteramosEntregadorComCodigoAcessoInvalido() throws Exception {
            // Arrange
            entregadorPostGetPutRequestDTO.setCodigo("8as54a");

            // Act
            String responseJsonString = driver.perform(put(URI_ENTREGADORES + "/" + entregador.getId())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(entregadorPostGetPutRequestDTO)))
                    .andExpect(status().isBadRequest())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            CustomErrorType resultado = objectMapper.readValue(responseJsonString, CustomErrorType.class);

            // Assert
            assertEquals("Código de acesso do entregador é inválido", resultado.getMessage());
        }
    }

    @Nested
    @DisplayName("Conjunto de casos de verificação de placa")
    class EntregadorVerificacaoPlaca {

        @Test
        @DisplayName("Quando alteramos o entregador com placa válida")
        void quandoAlteramosEntregadorComPlacaValida() throws Exception {
            // Arrange
            entregadorPostGetPutRequestDTO.setPlacaVeiculo("DEF-3456");

            // Act
            String responseJsonString = driver.perform(put(URI_ENTREGADORES + "/" + entregador.getId())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(entregadorPostGetPutRequestDTO)))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            EntregadorResponseDTO resultado = objectMapper.readValue(responseJsonString, EntregadorResponseDTO.class);

            // Assert
            assertEquals("DEF-3456", resultado.getPlacaVeiculo());
        }

        @Test
        @DisplayName("Quando alteramos o entregador com placa vazia")
        void quandoAlteramosEntregadorComPlacaVazia() throws Exception {
            // Arrange
            entregadorPostGetPutRequestDTO.setPlacaVeiculo("");

            // Act
            String responseJsonString = driver.perform(put(URI_ENTREGADORES + "/" + entregador.getId())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(entregadorPostGetPutRequestDTO)))
                    .andExpect(status().isBadRequest())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            CustomErrorType resultado = objectMapper.readValue(responseJsonString, CustomErrorType.class);

            // Assert
            assertAll(
                    () -> assertEquals("Erros de validacao encontrados", resultado.getMessage()),
                    () -> assertEquals("Placa do veiculo e obrigatoria", resultado.getErrors().get(0))
            );
        }

        @Test
        @DisplayName("Quando alteramos o entregador passando codigo de acesso inválido")
        void quandoAlteramosEntregadorComCodigoAcessoInvalido() throws Exception {
            // Arrange
            entregadorPostGetPutRequestDTO.setCodigo("8as54a");

            // Act
            String responseJsonString = driver.perform(put(URI_ENTREGADORES + "/" + entregador.getId())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(entregadorPostGetPutRequestDTO)))
                    .andExpect(status().isBadRequest())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            CustomErrorType resultado = objectMapper.readValue(responseJsonString, CustomErrorType.class);

            // Assert
            assertEquals("Código de acesso do entregador é inválido", resultado.getMessage());
        }
    }

    @Nested
    @DisplayName("Conjunto de casos de verificação de tipo")
    class EntregadorVerificacaoTipo {

        @Test
        @DisplayName("Quando alteramos o entregador com tipo de veiculo válido")
        void quandoAlteramosEntregadorComTipoVeiculoValido() throws Exception {
            // Arrange
            entregadorPostGetPutRequestDTO.setTipoVeiculo("carro");

            // Act
            String responseJsonString = driver.perform(put(URI_ENTREGADORES + "/" + entregador.getId())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(entregadorPostGetPutRequestDTO)))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            EntregadorResponseDTO resultado = objectMapper.readValue(responseJsonString, EntregadorResponseDTO.class);

            // Assert
            assertEquals("carro", resultado.getTipoVeiculo());
        }

        @Test
        @DisplayName("Quando alteramos o entregador com tipo de veiculo nulo")
        void quandoAlteramosEntregadorComTipoVeiculoVazio() throws Exception {
            // Arrange
            entregadorPostGetPutRequestDTO.setTipoVeiculo(null);

            // Act
            String responseJsonString = driver.perform(put(URI_ENTREGADORES + "/" + entregador.getId())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(entregadorPostGetPutRequestDTO)))
                    .andExpect(status().isBadRequest())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            CustomErrorType resultado = objectMapper.readValue(responseJsonString, CustomErrorType.class);

            // Assert
            assertAll(
                    () -> assertEquals("Erros de validacao encontrados", resultado.getMessage()),
                    () -> assertEquals("Tipo do veiculo e obrigatorio", resultado.getErrors().get(0))
            );
        }

        @Test
        @DisplayName("Quando alteramos o entregador com tipo de veiculo inválido")
        void quandoAlteramosEntregadorComTipoVeiculoInvalido() throws Exception {
            // Arrange
            entregadorPostGetPutRequestDTO.setTipoVeiculo("bicicleta");

            // Act
            String responseJsonString = driver.perform(put(URI_ENTREGADORES + "/" + entregador.getId())
                            .contentType(MediaType.APPLICATION_JSON)
                            .param("codigoAcesso", entregador.getCodigo())
                            .content(objectMapper.writeValueAsString(entregadorPostGetPutRequestDTO)))
                    .andExpect(status().isBadRequest())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            CustomErrorType resultado = objectMapper.readValue(responseJsonString, CustomErrorType.class);

            // Assert
            assertAll(
                    () -> assertEquals("Erros de validacao encontrados", resultado.getMessage()),
                    () -> assertEquals("Tipo do veiculo deve ser moto ou carro", resultado.getErrors().get(0))
            );
        }

        @Test
        @DisplayName("Quando alteramos o tipo passando codigo de acesso inválido")
        void quandoAlteramosEntregadorComCodigoAcessoInvalido() throws Exception {
            // Arrange
            entregadorPostGetPutRequestDTO.setCodigo("8as54a");

            // Act
            String responseJsonString = driver.perform(put(URI_ENTREGADORES + "/" + entregador.getId())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(entregadorPostGetPutRequestDTO)))
                    .andExpect(status().isBadRequest())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            CustomErrorType resultado = objectMapper.readValue(responseJsonString, CustomErrorType.class);

            // Assert
            assertEquals("Código de acesso do entregador é inválido", resultado.getMessage());
        }
    }

    @Nested
    @DisplayName("Conjunto de casos de verificação de cor do veiculo")
    class EntregadorVerificacaoCorVeiculo {

        @Test
        @DisplayName("Quando alteramos o entregador com cor do veiculo válida")
        void quandoAlteramosEntregadorComCorVeiculoValida() throws Exception {
            // Arrange
            entregadorPostGetPutRequestDTO.setCorVeiculo("preto");

            // Act
            String responseJsonString = driver.perform(put(URI_ENTREGADORES + "/" + entregador.getId())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(entregadorPostGetPutRequestDTO)))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            EntregadorResponseDTO resultado = objectMapper.readValue(responseJsonString, EntregadorResponseDTO.class);

            // Assert
            assertEquals("preto", resultado.getCorVeiculo());
        }

        @Test
        @DisplayName("Quando alteramos o entregador com cor do veiculo vazia")
        void quandoAlteramosEntregadorComCorVeiculoVazia() throws Exception {
            // Arrange
            entregadorPostGetPutRequestDTO.setCorVeiculo("");

            // Act
            String responseJsonString = driver.perform(put(URI_ENTREGADORES + "/" + entregador.getId())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(entregadorPostGetPutRequestDTO)))
                    .andExpect(status().isBadRequest())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            CustomErrorType resultado = objectMapper.readValue(responseJsonString, CustomErrorType.class);

            // Assert
            assertAll(
                    () -> assertEquals("Erros de validacao encontrados", resultado.getMessage()),
                    () -> assertEquals("Cor do veiculo e obrigatoria", resultado.getErrors().get(0))
            );
        }

        @Test
        @DisplayName("Quando alteramos a cor do veiculo passando codigo de acesso inválido")
        void quandoAlteramosEntregadorComCodigoAcessoInvalido() throws Exception {
            // Arrange
            entregadorPostGetPutRequestDTO.setCodigo("8as54a");

            // Act
            String responseJsonString = driver.perform(put(URI_ENTREGADORES + "/" + entregador.getId())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(entregadorPostGetPutRequestDTO)))
                    .andExpect(status().isBadRequest())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            CustomErrorType resultado = objectMapper.readValue(responseJsonString, CustomErrorType.class);

            // Assert
            assertEquals("Código de acesso do entregador é inválido", resultado.getMessage());
        }
    }
}
