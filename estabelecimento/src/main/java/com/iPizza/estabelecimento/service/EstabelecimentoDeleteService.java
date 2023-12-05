package com.iPizza.estabelecimento.service;


import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iPizza.estabelecimento.exception.NotFoundException;
import com.iPizza.estabelecimento.repository.EstabelecimentoRepository;
import com.iPizza.estabelecimento.service.interfaces.EstabelecimentoDelete;

@Service
public class EstabelecimentoDeleteService implements EstabelecimentoDelete {

    @Autowired
    private EstabelecimentoRepository estabelecimentoRepository;

    public void delete(UUID id) {
        
        if (!estabelecimentoRepository.existsById(id)) {
            throw new NotFoundException("Estabelecimento não encontrado");
        }
        estabelecimentoRepository.deleteById(id);
    }
}
