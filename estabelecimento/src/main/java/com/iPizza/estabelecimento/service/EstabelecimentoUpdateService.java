package com.iPizza.estabelecimento.service;

import com.iPizza.estabelecimento.dto.EstabelecimentoPostGetPutDTO;
import com.iPizza.estabelecimento.model.Estabelecimento;
import com.iPizza.estabelecimento.repository.EstabelecimentoRepository;
import com.iPizza.estabelecimento.service.interfaces.EstabelecimentoFindOne;
import com.iPizza.estabelecimento.service.interfaces.EstabelecimentoUpdate;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstabelecimentoUpdateService implements EstabelecimentoUpdate {

    @Autowired
    private EstabelecimentoRepository estabelecimentoRepository;
    @Autowired
    private EstabelecimentoFindOne estabelecimentoFindOneService;

    public Estabelecimento update(UUID id, EstabelecimentoPostGetPutDTO estabelecimentoPostGetPutDTO) {
        
        Estabelecimento estabelecimentoUpdated = estabelecimentoFindOneService.findOne(id);

        estabelecimentoUpdated.setCodigo(estabelecimentoPostGetPutDTO.getCodigo());
        estabelecimentoUpdated.setEmail(estabelecimentoPostGetPutDTO.getEmail());

        return estabelecimentoRepository.save(estabelecimentoUpdated);
    }
}
