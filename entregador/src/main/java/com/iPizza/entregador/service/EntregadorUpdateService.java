package com.iPizza.entregador.service;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.iPizza.entregador.dto.EntregadorPostGetPutRequestDTO;
import com.iPizza.entregador.model.Entregador;
import com.iPizza.entregador.repository.EntregadorRepository;
import com.iPizza.entregador.service.interfaces.EntregadorFindOneWithCode;
import com.iPizza.entregador.service.interfaces.EntregadorUpdate;

@Service
public class EntregadorUpdateService implements EntregadorUpdate {

    @Autowired
    private EntregadorRepository entregadorRepository;
    @Autowired
    private EntregadorFindOneWithCode entregadorFindOneWithCode;

    @Override
    public Entregador update(UUID id, EntregadorPostGetPutRequestDTO entregadorPostGetPutRequestDTO) {

        Entregador entregador = entregadorFindOneWithCode.findOneWithCode(id, entregadorPostGetPutRequestDTO.getCodigo());

        entregador.setNome(entregadorPostGetPutRequestDTO.getNome());
        entregador.setCorVeiculo(entregadorPostGetPutRequestDTO.getCorVeiculo());
        entregador.setTipoVeiculo(entregadorPostGetPutRequestDTO.getTipoVeiculo());
        entregador.setPlacaVeiculo(entregadorPostGetPutRequestDTO.getPlacaVeiculo());

        return entregadorRepository.save(entregador);
    }
}
