package com.iPizza.entregador.service;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iPizza.entregador.exception.InvalidCodeException;
import com.iPizza.entregador.exception.NotFoundException;
import com.iPizza.entregador.model.Entregador;
import com.iPizza.entregador.repository.EntregadorRepository;
import com.iPizza.entregador.service.interfaces.EntregadorFindOneWithCode;

import java.util.Optional;

@Service
public class EntregadorFindOneWithCodeService implements EntregadorFindOneWithCode {

    @Autowired
    private EntregadorRepository entregadorRepository;

    @Override
    public Entregador findOneWithCode(UUID id, String code) {
        Optional<Entregador> optionalEntregador = entregadorRepository.findById(id);
        if(optionalEntregador.isEmpty()){
            throw new NotFoundException("Entregador não encontrado");
        }
        Entregador entregador = optionalEntregador.get();

        if(!entregador.getCodigoAcesso().equals(code)){
            throw new InvalidCodeException("Código de acesso do entregador é inválido");
        }
        return entregador;
    }
}
