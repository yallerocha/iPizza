package com.iPizza.estabelecimento.controller;

import jakarta.validation.Valid;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.iPizza.estabelecimento.dto.EstabelecimentoPostPutDTO;
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
        public ResponseEntity<?> estabelecimentoPost (
                @Valid @RequestBody EstabelecimentoPostPutDTO estabelecimentoPostPutDTO
        ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(estabelecimentoCreate.create(estabelecimentoPostPutDTO));
        }

        @PutMapping("/{id}")
        public ResponseEntity<?> estabelecimentoPut (
                @PathVariable ("id") UUID id,
                @Valid @RequestBody EstabelecimentoPostPutDTO estabelecimentoPostPutDTO
        ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(estabelecimentoUpdate.update(id, estabelecimentoPostPutDTO));
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<?> estabelecimentoDelete (
                @PathVariable ("id") UUID id
        ) {
        estabelecimentoDelete.delete(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
        }

        @GetMapping("/{id}")
        public ResponseEntity<?> estabelecimentoGetOne (
                @PathVariable ("id") UUID id
        ) {
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(estabelecimentoFindOne.findOne(id));
        }

        @GetMapping("/{id}/codigo/{codigo}")
        public ResponseEntity<?> estabelecimentoGetOneWithCode (
                @PathVariable ("id") UUID id,
                @PathVariable ("codigo") String codigo
        ) {
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(EstabelecimentoFindOneWithCode.findOneWithCode(id, codigo));
        }
}
