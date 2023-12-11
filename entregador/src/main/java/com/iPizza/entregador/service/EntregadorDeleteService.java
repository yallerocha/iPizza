package com.iPizza.entregador.service;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iPizza.entregador.dto.EntregadorDeleteRequestDTO;
import com.iPizza.entregador.model.Entregador;
import com.iPizza.entregador.repository.EntregadorRepository;
import com.iPizza.entregador.service.interfaces.EntregadorDelete;
import com.iPizza.entregador.service.interfaces.EntregadorFindOneWithCode;

@Service
public class EntregadorDeleteService implements EntregadorDelete {

    @Autowired
    private EntregadorRepository entregadorRepository;
    @Autowired
    private EntregadorFindOneWithCode entregadorFindOneWithCode;

    @Override
    public void delete(UUID id, EntregadorDeleteRequestDTO entregadorDeleteRequestDTO) {
        
        Entregador entregador = entregadorFindOneWithCode.findOneWithCode(id, entregadorDeleteRequestDTO.getCodigo());
        entregadorRepository.deleteById(entregador.getId());
    }


}
