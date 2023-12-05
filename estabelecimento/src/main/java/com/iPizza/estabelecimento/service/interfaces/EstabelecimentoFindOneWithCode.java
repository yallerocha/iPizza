package com.iPizza.estabelecimento.service.interfaces;

import java.util.UUID;

import com.iPizza.estabelecimento.model.Estabelecimento;

public interface EstabelecimentoFindOneWithCode {

    public Estabelecimento findOneWithCode(UUID id, String codigo);
}
