package com.iPizza.entregador.controller;

import java.util.stream.Collectors;

import java.util.UUID;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.iPizza.entregador.dto.EntregadorDeleteRequestDTO;
import com.iPizza.entregador.dto.EntregadorPostPutRequestDTO;
import com.iPizza.entregador.dto.EntregadorResponseDTO;
import com.iPizza.entregador.service.interfaces.EntregadorCreate;
import com.iPizza.entregador.service.interfaces.EntregadorDelete;
import com.iPizza.entregador.service.interfaces.EntregadorFindAll;
import com.iPizza.entregador.service.interfaces.EntregadorFindOne;
import com.iPizza.entregador.service.interfaces.EntregadorUpdate;

import java.util.List;

@RestController
@RequestMapping("/entregadores")
public class EntregadorController {

    @Autowired
    private EntregadorFindAll entregadorFindAll;

    @Autowired
    private EntregadorFindOne entregadorFindOne;

    @Autowired
    private EntregadorCreate entregadorCreate;

    @Autowired
    private EntregadorUpdate entregadorUpdate;

    @Autowired
    private EntregadorDelete entregadorDelete;

    @PostMapping
    public ResponseEntity<EntregadorResponseDTO> entregadorPost(
            @RequestBody @Valid EntregadorPostPutRequestDTO entregadorPostPutRequestDTO
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new EntregadorResponseDTO(entregadorCreate.store(entregadorPostPutRequestDTO)));
    }

    @GetMapping
    public ResponseEntity<List<EntregadorResponseDTO>> entregadorGetAll() {

        List<EntregadorResponseDTO> entregadoresResponseDTO = entregadorFindAll.findAll().stream()
                .map(EntregadorResponseDTO::new)
                .collect(Collectors.toList());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(entregadoresResponseDTO);
    }

    @GetMapping("{id}")
    public ResponseEntity<EntregadorResponseDTO> entregadorGet(
            @PathVariable("id") UUID id
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new EntregadorResponseDTO(entregadorFindOne.findOne(id)));
    }

    @PutMapping("{id}")
    public ResponseEntity<EntregadorResponseDTO> entregadorPut(
            @PathVariable ("id") UUID id, 
            @RequestBody @Valid EntregadorPostPutRequestDTO entregadorPostPutRequestDTO
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new EntregadorResponseDTO(entregadorUpdate.update(id, entregadorPostPutRequestDTO)));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> entregadorDelete(
            @PathVariable ("id") UUID id, 
            @RequestBody @Valid EntregadorDeleteRequestDTO entregadorDeleteRequestDTO
    ) {
        entregadorDelete.delete(id, entregadorDeleteRequestDTO);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
