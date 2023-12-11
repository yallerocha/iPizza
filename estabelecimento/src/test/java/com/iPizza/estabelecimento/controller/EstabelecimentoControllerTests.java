package com.iPizza.estabelecimento.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import com.iPizza.estabelecimento.dto.EstabelecimentoPostGetPutDTO;
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
	private MockMvc driver;

	@Autowired
	private EstabelecimentoRepository estabelecimentoRepository;

	private ObjectMapper objectMapper = new ObjectMapper();

	private Estabelecimento estabelecimento;

	@BeforeEach
	void setup() {

		objectMapper.registerModule(new JavaTimeModule());

		estabelecimento = estabelecimentoRepository.save(Estabelecimento.builder()
				.codigo("123456")
				.email("estabelecimento@email.com")
				.build()
		);
	}

	@AfterEach
	void tearDown() {
		estabelecimentoRepository.deleteAll();
	}

	@Nested
	@DisplayName("Conjunto de casos de verificação dos fluxos básicos API Rest")
	class EstabelecimentoVerificacaoFluxosBasicosApiRest {

		final String URI_ESTABELECIMENTOS = "/estabelecimentos";

		private EstabelecimentoPostGetPutDTO estabelecimentoPutRequestDTO;

		private EstabelecimentoPostGetPutDTO estabelecimentoPostRequestDTO;

		@BeforeEach
		void setup() {

			estabelecimentoPutRequestDTO = EstabelecimentoPostGetPutDTO.builder()
					.codigo("123456")
					.email("estabelecimento@gmail.com")
					.build();

			estabelecimentoPostRequestDTO = EstabelecimentoPostGetPutDTO.builder()
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
			
			EstabelecimentoResponseDTO resultado = objectMapper.readValue(responseJsonString, EstabelecimentoResponseDTO.class);

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
			assertAll(
				() -> assertTrue(responseJsonString.isBlank())
			);
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

			EstabelecimentoResponseDTO resultado = objectMapper.readValue(responseJsonString, EstabelecimentoResponseDTO.class);

			// Assert
			assertAll(
				() -> assertEquals(resultado.getId(), estabelecimento.getId()),
				() -> assertEquals("131289", resultado.getCodigo())
			);
		}

		@Test 
		@DisplayName("Quando alteramos um estabelecimento com codigo de acesso inválido")
		void quandoAlterarEstabelecimentoInvalido() throws Exception {
			// Arrange
			EstabelecimentoPostGetPutDTO estabelecimentoPostGetPutDTO = EstabelecimentoPostGetPutDTO.builder()
					.codigo("13")
					.email("estabelecimento@gmail.com")
					.build();

			// Act
			String responseJsonString = driver.perform(put(URI_ESTABELECIMENTOS + "/" + estabelecimento.getId())
							.contentType(MediaType.APPLICATION_JSON)
							.param("codigo", estabelecimento.getCodigo())
							.content(objectMapper.writeValueAsString(estabelecimentoPostGetPutDTO)))
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
			EstabelecimentoPostGetPutDTO estabelecimentoPostGetPutDTO = EstabelecimentoPostGetPutDTO.builder()
					.codigo("13")
					.email("estabelecimento@gmail.com")
					.build();

			// Act
			String responseJsonString = driver.perform(post(URI_ESTABELECIMENTOS)
							.contentType(MediaType.APPLICATION_JSON)
							.content(objectMapper.writeValueAsString(estabelecimentoPostGetPutDTO)))
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
	}
}