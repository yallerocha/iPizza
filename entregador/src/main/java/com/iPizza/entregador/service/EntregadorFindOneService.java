package com.iPizza.entregador.service;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iPizza.entregador.exception.NotFoundException;
import com.iPizza.entregador.model.Entregador;
import com.iPizza.entregador.repository.EntregadorRepository;
import com.iPizza.entregador.service.interfaces.EntregadorFindOne;

import java.util.Optional;

@Service
public class EntregadorFindOneService implements EntregadorFindOne {
    @Autowired
    private EntregadorRepository entregadorRepository;
    @Override
    public Entregador findOne(UUID id) {
        Optional<Entregador> optionalEntregador = entregadorRepository.findById(id);
        if(optionalEntregador.isEmpty()){
            throw new NotFoundException("Entregador não encontrado");
        }
        return optionalEntregador.get();

    }
}
