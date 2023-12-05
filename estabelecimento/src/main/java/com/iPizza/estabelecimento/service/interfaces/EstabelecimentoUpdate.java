package com.iPizza.estabelecimento.service.interfaces;

import java.util.UUID;

import com.iPizza.estabelecimento.dto.EstabelecimentoPostPutDTO;
import com.iPizza.estabelecimento.model.Estabelecimento;

public interface EstabelecimentoUpdate {

    public Estabelecimento update(UUID id, EstabelecimentoPostPutDTO estabelecimentoPostPutDTO);

}
