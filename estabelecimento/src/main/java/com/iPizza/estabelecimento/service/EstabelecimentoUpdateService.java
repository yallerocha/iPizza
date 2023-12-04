package com.iPizza.estabelecimento.service;

import com.iPizza.estabelecimento.dto.EstabelecimentoPostPutDTO;
import com.iPizza.estabelecimento.exception.NotFoundException;
import com.iPizza.estabelecimento.model.Estabelecimento;
import com.iPizza.estabelecimento.repository.EstabelecimentoRepository;
import com.iPizza.estabelecimento.service.interfaces.EstabelecimentoUpdate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EstabelecimentoUpdateService implements EstabelecimentoUpdate {
    @Autowired
    private EstabelecimentoRepository estabelecimentoRepository;

    public Estabelecimento update(Long id, EstabelecimentoPostPutDTO estabelecimentoPutDTO) {
        if (!estabelecimentoRepository.existsById(id)) {
            throw new NotFoundException("Estabelecimento não encontrado");
        }

        Optional<Estabelecimento> r1 = estabelecimentoRepository.findById(id);
        Estabelecimento estabelecimentoUpdated = r1.get();
        estabelecimentoUpdated.setCodigo(estabelecimentoPutDTO.getCodigo());
        estabelecimentoUpdated.setEmail(estabelecimentoPutDTO.getEmail());

        return estabelecimentoRepository.save(estabelecimentoUpdated);
    }
}
