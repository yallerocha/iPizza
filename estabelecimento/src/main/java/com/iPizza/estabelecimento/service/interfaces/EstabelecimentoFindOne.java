package com.iPizza.estabelecimento.service.interfaces;

import java.util.UUID;

import com.iPizza.estabelecimento.model.Estabelecimento;

public interface EstabelecimentoFindOne {

    public Estabelecimento findOne(UUID id);
}
