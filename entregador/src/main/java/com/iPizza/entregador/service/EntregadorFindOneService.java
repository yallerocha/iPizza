package com.iPizza.entregador.service;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iPizza.entregador.exception.NotFoundException;
import com.iPizza.entregador.model.Entregador;
import com.iPizza.entregador.repository.EntregadorRepository;
import com.iPizza.entregador.service.interfaces.EntregadorFindOne;

@Service
public class EntregadorFindOneService implements EntregadorFindOne {

    @Autowired
    private EntregadorRepository entregadorRepository;

    @Override
    public Entregador findOne(UUID id) {

        Entregador entregador = entregadorRepository.findById(id).orElseThrow(
            () -> new NotFoundException("Entregador não encontrado")
        );   
        return entregador;
    }
}
