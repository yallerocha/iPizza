package com.iPizza.estabelecimento.service.interfaces;

import java.util.UUID;

import com.iPizza.estabelecimento.dto.EstabelecimentoPostGetPutDTO;
import com.iPizza.estabelecimento.model.Estabelecimento;

public interface EstabelecimentoUpdate {

    public Estabelecimento update(UUID id, EstabelecimentoPostGetPutDTO estabelecimentoPostGetPutDTO);
}
