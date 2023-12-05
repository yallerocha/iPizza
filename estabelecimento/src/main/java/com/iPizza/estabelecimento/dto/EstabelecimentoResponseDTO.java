package com.iPizza.estabelecimento.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.iPizza.estabelecimento.model.Estabelecimento;
import com.iPizza.estabelecimento.model.Pedido;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EstabelecimentoResponseDTO implements Serializable {

    private UUID id;

    private String codigo;

    private String email;

    @JsonIgnore
    private List<Pedido> pedidos;

    public EstabelecimentoResponseDTO(Estabelecimento estabelecimento) {
        this.id = estabelecimento.getId();
        this.codigo = estabelecimento.getCodigo();
        this.email = estabelecimento.getEmail();
    }
}
