package com.iPizza.estabelecimento.service;

import com.iPizza.estabelecimento.dto.EstabelecimentoPostPutDTO;
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

    public Estabelecimento update(UUID id, EstabelecimentoPostPutDTO estabelecimentoPostPutDTO) {
        
        Estabelecimento estabelecimentoUpdated = estabelecimentoFindOneService.findOne(id);

        estabelecimentoUpdated.setCodigo(estabelecimentoPostPutDTO.getCodigo());
        estabelecimentoUpdated.setEmail(estabelecimentoPostPutDTO.getEmail());

        return estabelecimentoRepository.save(estabelecimentoUpdated);
    }
}
