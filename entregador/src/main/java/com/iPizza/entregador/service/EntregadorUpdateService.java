package com.iPizza.entregador.service;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.iPizza.entregador.dto.EntregadorPostPutRequestDTO;
import com.iPizza.entregador.model.Entregador;
import com.iPizza.entregador.repository.EntregadorRepository;
import com.iPizza.entregador.service.interfaces.EntregadorFindOneWithCode;
import com.iPizza.entregador.service.interfaces.EntregadorUpdate;

@Service
public class EntregadorUpdateService implements EntregadorUpdate {

    @Autowired
    private EntregadorRepository entregadorRepository;
    @Autowired
    private EntregadorFindOneWithCode entregadorFindOneWithCodeService;

    @Override
    public Entregador update(UUID id, EntregadorPostPutRequestDTO entregadorPostPutRequestDTO) {

        Entregador entregador = entregadorFindOneWithCodeService.findOneWithCode(id, entregadorPostPutRequestDTO.getCodigoAcesso());

        entregador.setNome(entregadorPostPutRequestDTO.getNome());
        entregador.setCorVeiculo(entregadorPostPutRequestDTO.getCorVeiculo());
        entregador.setTipoVeiculo(entregadorPostPutRequestDTO.getTipoVeiculo());
        entregador.setPlacaVeiculo(entregadorPostPutRequestDTO.getPlacaVeiculo());

        return entregadorRepository.save(entregador);
    }
}
