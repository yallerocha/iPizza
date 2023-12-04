package com.iPizza.estabelecimento.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iPizza.estabelecimento.dto.EstabelecimentoPostPutDTO;
import com.iPizza.estabelecimento.model.Estabelecimento;
import com.iPizza.estabelecimento.repository.EstabelecimentoRepository;
import com.iPizza.estabelecimento.service.interfaces.EstabelecimentoCreate;

@Service
public class EstabelecimentoCreateService implements EstabelecimentoCreate {
    @Autowired
    private EstabelecimentoRepository estabelecimentoRepository;

    public Estabelecimento create(EstabelecimentoPostPutDTO estabelecimentoPostDTO){
            return estabelecimentoRepository.save(
                    Estabelecimento.builder()
                            .codigo(estabelecimentoPostDTO.getCodigo())
                            .email(estabelecimentoPostDTO.getEmail())
                            .build()
            );
    }
}
