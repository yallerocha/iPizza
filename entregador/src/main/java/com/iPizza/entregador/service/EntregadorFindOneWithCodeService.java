package com.iPizza.entregador.service;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iPizza.entregador.exception.InvalidCodeException;
import com.iPizza.entregador.model.Entregador;
import com.iPizza.entregador.service.interfaces.EntregadorFindOne;
import com.iPizza.entregador.service.interfaces.EntregadorFindOneWithCode;

@Service
public class EntregadorFindOneWithCodeService implements EntregadorFindOneWithCode {

    @Autowired
    private EntregadorFindOne entregadorFindOneService;

    @Override
    public Entregador findOneWithCode(UUID id, String codigo) {

        Entregador entregador = entregadorFindOneService.findOne(id);

        if(!entregador.getCodigo().equals(codigo)) {
            throw new InvalidCodeException("Código de acesso do entregador é inválido");
        }
        return entregador;
    }
}
