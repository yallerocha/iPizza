package com.iPizza.estabelecimento.service.interfaces;

import com.iPizza.estabelecimento.dto.EstabelecimentoPostGetPutDTO;
import com.iPizza.estabelecimento.model.Estabelecimento;

public interface EstabelecimentoCreate {

    public Estabelecimento create(EstabelecimentoPostGetPutDTO estabelecimentoPostGetPutDTO);
}
