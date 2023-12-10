package com.iPizza.entregador.service.interfaces;

import java.util.UUID;
import com.iPizza.entregador.model.Entregador;

public interface EntregadorFindOne {

    public Entregador findOne(UUID id);
}
