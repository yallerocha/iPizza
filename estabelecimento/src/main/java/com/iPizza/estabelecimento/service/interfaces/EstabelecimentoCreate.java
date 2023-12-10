package com.iPizza.estabelecimento.service.interfaces;

import com.iPizza.estabelecimento.dto.EstabelecimentoPostPutDTO;
import com.iPizza.estabelecimento.model.Estabelecimento;

public interface EstabelecimentoCreate {

    public Estabelecimento create(EstabelecimentoPostPutDTO estabelecimentoPostPutDTO);
}
