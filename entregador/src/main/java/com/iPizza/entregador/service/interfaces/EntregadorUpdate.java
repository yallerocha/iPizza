package com.iPizza.entregador.service.interfaces;

import java.util.UUID;

import com.iPizza.entregador.dto.EntregadorPostPutRequestDTO;
import com.iPizza.entregador.model.Entregador;

public interface EntregadorUpdate {

    public Entregador update(UUID id, EntregadorPostPutRequestDTO entregadorPostPutRequestDTO);
}
