package com.iPizza.entregador.service.interfaces;

import java.util.UUID;

import com.iPizza.entregador.model.Entregador;

public interface EntregadorFindOneWithCode {

    public Entregador findOneWithCode(UUID id, String codigo);
}
