package com.iPizza.entregador.service.interfaces;

import java.util.UUID;

import com.iPizza.entregador.dto.EntregadorDeleteRequestDTO;

public interface EntregadorDelete {

    public void delete(UUID id, EntregadorDeleteRequestDTO entregadorDeleteRequestDTO);
}
