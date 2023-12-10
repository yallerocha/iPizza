package com.iPizza.entregador.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iPizza.entregador.model.Entregador;
import com.iPizza.entregador.repository.EntregadorRepository;
import com.iPizza.entregador.service.interfaces.EntregadorFindAll;

import java.util.List;

@Service
public class EntregadorFindAllService  implements EntregadorFindAll {

    @Autowired
    private EntregadorRepository entregadorRepository;

    @Override
    public List<Entregador> findAll() {
        return entregadorRepository.findAll();
    }
}
