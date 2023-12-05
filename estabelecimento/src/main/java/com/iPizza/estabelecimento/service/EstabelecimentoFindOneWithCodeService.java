package com.iPizza.estabelecimento.service;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iPizza.estabelecimento.exception.InvalidCodeException;
import com.iPizza.estabelecimento.model.Estabelecimento;
import com.iPizza.estabelecimento.service.interfaces.EstabelecimentoFindOne;
import com.iPizza.estabelecimento.service.interfaces.EstabelecimentoFindOneWithCode;

@Service
public class EstabelecimentoFindOneWithCodeService implements EstabelecimentoFindOneWithCode {

    @Autowired
    private EstabelecimentoFindOne estabelecimentoFindOneService;

    public Estabelecimento findOneWithCode(UUID id, String codigo) {

        Estabelecimento estabelecimento = estabelecimentoFindOneService.findOne(id);

        if (!estabelecimento.getCodigo().equals(codigo)) {
            throw new InvalidCodeException("Código de acesso do estabelecimento é inválido");
        }
        return estabelecimento;
    }
}