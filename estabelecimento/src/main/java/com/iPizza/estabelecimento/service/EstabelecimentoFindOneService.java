package com.iPizza.estabelecimento.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iPizza.estabelecimento.exception.NotFoundException;
import com.iPizza.estabelecimento.model.Estabelecimento;
import com.iPizza.estabelecimento.repository.EstabelecimentoRepository;
import com.iPizza.estabelecimento.service.interfaces.EstabelecimentoFindOne;

import java.util.Optional;
@Service
public class EstabelecimentoFindOneService implements EstabelecimentoFindOne {

    @Autowired
    private EstabelecimentoRepository estabelecimentoRepository;

    @Override
    public Estabelecimento findOne(Long id) {
        Optional<Estabelecimento> estabelecimento = estabelecimentoRepository.findById(id);

        if(estabelecimento.isEmpty()){
            throw new NotFoundException("Estabelecimento não encontrado");
        }

        return  estabelecimento.get();
    }
}
