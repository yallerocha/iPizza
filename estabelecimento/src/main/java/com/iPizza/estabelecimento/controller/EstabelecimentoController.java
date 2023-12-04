package com.iPizza.estabelecimento.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.iPizza.estabelecimento.dto.EstabelecimentoPostPutDTO;
import com.iPizza.estabelecimento.service.interfaces.EstabelecimentoCreate;
import com.iPizza.estabelecimento.service.interfaces.EstabelecimentoDelete;
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

    @PostMapping
    public ResponseEntity<?> criarEstabelecimento(
            @Valid
            @RequestBody EstabelecimentoPostPutDTO estabelecimentoPostDTO
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(estabelecimentoCreate.create(estabelecimentoPostDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateInfoEstabelecimento(
            @PathVariable ("id") Long id,
            @Valid
            @RequestBody EstabelecimentoPostPutDTO estabelecimentoPutDTO
    ){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(estabelecimentoUpdate.update(id, estabelecimentoPutDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEstabelecimento(
            @PathVariable ("id") Long id
    ){
        estabelecimentoDelete.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(null);
    }
}
