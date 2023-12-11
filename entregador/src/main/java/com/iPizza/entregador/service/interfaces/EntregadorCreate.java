package com.iPizza.entregador.service.interfaces;

import com.iPizza.entregador.dto.EntregadorPostGetPutRequestDTO;
import com.iPizza.entregador.model.Entregador;

public interface EntregadorCreate {

    public Entregador create(EntregadorPostGetPutRequestDTO entregadorPostGetPutRequestDTO);
}
