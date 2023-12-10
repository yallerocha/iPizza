package com.iPizza.entregador.components;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iPizza.entregador.exception.CommerceException;
import com.iPizza.entregador.model.Entregador;
import com.iPizza.entregador.service.interfaces.EntregadorFindOneWithCode;

@Component
public class EntregadorValidator {

    @Autowired
    private EntregadorFindOneWithCode entregadorFindOneWithCodeService;

    public void valid(UUID id, String codigo) {
        
        Entregador entregador = entregadorFindOneWithCodeService.findOneWithCode(id, codigo);

        if (!entregador.getCodigoAcesso().equals(codigo)) {
            throw new CommerceException("Código de acesso de entregador " + id + " não confere");
        }
    }
}