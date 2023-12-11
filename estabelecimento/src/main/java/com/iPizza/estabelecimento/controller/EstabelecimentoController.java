package com.iPizza.estabelecimento.controller;

import jakarta.validation.Valid;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.iPizza.estabelecimento.dto.EstabelecimentoPostGetPutDTO;
import com.iPizza.estabelecimento.dto.EstabelecimentoResponseDTO;
import com.iPizza.estabelecimento.service.interfaces.EstabelecimentoCreate;
import com.iPizza.estabelecimento.service.interfaces.EstabelecimentoDelete;
import com.iPizza.estabelecimento.service.interfaces.EstabelecimentoFindOne;
import com.iPizza.estabelecimento.service.interfaces.EstabelecimentoFindOneWithCode;
import com.iPizza.estabelecimento.service.interfaces.EstabelecimentoUpdate;

@RestController
@RequestMapping(
	value = "/estabelecimentos",
	produces = MediaType.APPLICATION_JSON_VALUE
)
public class EstabelecimentoController {

	@Autowired
	private EstabelecimentoDelete estabelecimentoDelete;
	@Autowired
	private EstabelecimentoCreate estabelecimentoCreate;
	@Autowired
	private EstabelecimentoUpdate estabelecimentoUpdate;
	@Autowired
	private EstabelecimentoFindOne estabelecimentoFindOne;
	@Autowired
	private EstabelecimentoFindOneWithCode EstabelecimentoFindOneWithCode;

	@PostMapping
	public ResponseEntity<EstabelecimentoResponseDTO> estabelecimentoPost(
			@Valid @RequestBody EstabelecimentoPostGetPutDTO estabelecimentoPostGetPutDTO
	) {
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(new EstabelecimentoResponseDTO(estabelecimentoCreate.create(estabelecimentoPostGetPutDTO)));
	}

	@GetMapping("/{id}")
	public ResponseEntity<EstabelecimentoResponseDTO> estabelecimentoGet(
			@PathVariable ("id") UUID id
	) {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(new EstabelecimentoResponseDTO(estabelecimentoFindOne.findOne(id)));
	}
	
	@GetMapping("/{id}/codigo/")
	public ResponseEntity<EstabelecimentoResponseDTO> estabelecimentoGetWithCode(
			@PathVariable ("id") UUID id,
			@Valid @RequestBody EstabelecimentoPostGetPutDTO estabelecimentoPostGetPutDTO
	) {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(new EstabelecimentoResponseDTO(EstabelecimentoFindOneWithCode.findOneWithCode(id, estabelecimentoPostGetPutDTO.getCodigo())));
	}

	@PutMapping("/{id}")
	public ResponseEntity<EstabelecimentoResponseDTO> estabelecimentoPut(
			@PathVariable ("id") UUID id,
			@Valid @RequestBody EstabelecimentoPostGetPutDTO estabelecimentoPostGetPutDTO
	) {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(new EstabelecimentoResponseDTO(estabelecimentoUpdate.update(id, estabelecimentoPostGetPutDTO)));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> estabelecimentoDelete(
			@PathVariable ("id") UUID id
	) {
		estabelecimentoDelete.delete(id);
		return ResponseEntity
				.status(HttpStatus.NO_CONTENT)
				.build();
	}
}
