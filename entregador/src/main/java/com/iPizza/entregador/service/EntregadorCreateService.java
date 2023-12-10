package com.iPizza.entregador.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iPizza.entregador.dto.EntregadorPostPutRequestDTO;
import com.iPizza.entregador.model.Entregador;
import com.iPizza.entregador.repository.EntregadorRepository;
import com.iPizza.entregador.service.interfaces.EntregadorCreate;

@Service
public class EntregadorCreateService implements EntregadorCreate {

    @Autowired
    private EntregadorRepository entregadorRepository;

    @Override
    public Entregador create(EntregadorPostPutRequestDTO entregadorPostPutRequestDTO) {
        
        return entregadorRepository.save(Entregador.builder()
                .nome(entregadorPostPutRequestDTO.getNome())
                .codigoAcesso(entregadorPostPutRequestDTO.getCodigoAcesso())
                .corVeiculo(entregadorPostPutRequestDTO.getCorVeiculo())
                .placaVeiculo(entregadorPostPutRequestDTO.getPlacaVeiculo())
                .tipoVeiculo(entregadorPostPutRequestDTO.getTipoVeiculo())
                .build());
    }
}
