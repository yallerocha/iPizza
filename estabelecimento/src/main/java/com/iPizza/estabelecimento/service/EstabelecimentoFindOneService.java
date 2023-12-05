package com.iPizza.estabelecimento.service;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iPizza.estabelecimento.exception.NotFoundException;
import com.iPizza.estabelecimento.model.Estabelecimento;
import com.iPizza.estabelecimento.repository.EstabelecimentoRepository;
import com.iPizza.estabelecimento.service.interfaces.EstabelecimentoFindOne;

@Service
public class EstabelecimentoFindOneService implements EstabelecimentoFindOne {

    @Autowired
    private EstabelecimentoRepository estabelecimentoRepository;

    @Override
    public Estabelecimento findOne(UUID id) {

        Estabelecimento estabelecimento = estabelecimentoRepository.findById(id).orElseThrow(
            () -> new NotFoundException("Estabelecimento não encontrado")
        );
        return estabelecimento;
    }
}
