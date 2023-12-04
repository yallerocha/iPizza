package com.iPizza.estabelecimento.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iPizza.estabelecimento.exception.CommerceException;
import com.iPizza.estabelecimento.model.Estabelecimento;
import com.iPizza.estabelecimento.service.EstabelecimentoFindOneWithCodeService;

@Component
public class EstabelecimentoValidator {

    @Autowired
    private EstabelecimentoFindOneWithCodeService estabelecimentoFindOneWithCodeService;

    public void valid(Long idEstabelecimento, String codigoEstabelecimento) {
        
        Estabelecimento Estabelecimento = estabelecimentoFindOneWithCodeService.findOneWithCode(idEstabelecimento, codigoEstabelecimento);

        if (!Estabelecimento.getCodigo().equals(codigoEstabelecimento)) {
            throw new CommerceException("Código de acesso de Estabelecimento " + idEstabelecimento + " não confere");
        }
    }
}