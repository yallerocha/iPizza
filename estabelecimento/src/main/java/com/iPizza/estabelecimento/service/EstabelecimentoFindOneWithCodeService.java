package com.iPizza.estabelecimento.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iPizza.estabelecimento.exception.InvalidCodeException;
import com.iPizza.estabelecimento.exception.NotFoundException;
import com.iPizza.estabelecimento.model.Estabelecimento;
import com.iPizza.estabelecimento.repository.EstabelecimentoRepository;

import java.util.Optional;

@Service
public class EstabelecimentoFindOneWithCodeService{

    @Autowired
    private EstabelecimentoRepository estabelecimentoRepository;

    public Estabelecimento findOneWithCode(Long id, String code) {
        Optional<Estabelecimento> optionalEstabelecimento = estabelecimentoRepository.findById(id);
        if(optionalEstabelecimento.isEmpty()){
            throw new NotFoundException("Estabelecimento não encontrado");
        }
        Estabelecimento estabelecimento = optionalEstabelecimento.get();

        if(!estabelecimento.getCodigo().equals(code)){
            throw new InvalidCodeException("Código de acesso do estabelecimento é inválido");
        }
        return estabelecimento;
    }
}