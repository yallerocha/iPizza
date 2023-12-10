package com.iPizza.entregador.service.interfaces;

import com.iPizza.entregador.dto.EntregadorPostPutRequestDTO;
import com.iPizza.entregador.model.Entregador;

public interface EntregadorCreate {

    public Entregador store(EntregadorPostPutRequestDTO entregadorPostPutRequestDTO);
}
