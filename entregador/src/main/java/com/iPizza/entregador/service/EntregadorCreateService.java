package com.iPizza.entregador.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iPizza.entregador.dto.EntregadorPostGetPutRequestDTO;
import com.iPizza.entregador.model.Entregador;
import com.iPizza.entregador.repository.EntregadorRepository;
import com.iPizza.entregador.service.interfaces.EntregadorCreate;

@Service
public class EntregadorCreateService implements EntregadorCreate {

    @Autowired
    private EntregadorRepository entregadorRepository;

    @Override
    public Entregador create(EntregadorPostGetPutRequestDTO entregadorPostGetPutRequestDTO) {

        return entregadorRepository.save(Entregador.builder()
                .nome(entregadorPostGetPutRequestDTO.getNome())
                .codigo(entregadorPostGetPutRequestDTO.getCodigo())
                .corVeiculo(entregadorPostGetPutRequestDTO.getCorVeiculo())
                .placaVeiculo(entregadorPostGetPutRequestDTO.getPlacaVeiculo())
                .tipoVeiculo(entregadorPostGetPutRequestDTO.getTipoVeiculo())
                .build());
    }
}
