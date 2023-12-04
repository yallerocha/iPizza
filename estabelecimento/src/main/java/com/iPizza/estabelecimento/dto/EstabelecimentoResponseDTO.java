package com.iPizza.estabelecimento.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.iPizza.estabelecimento.model.Estabelecimento;
import com.iPizza.estabelecimento.model.Pedido;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EstabelecimentoResponseDTO {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("codigo")
    private String codigo;
    @JsonProperty("email")
    private String email;
    @JsonIgnore
    @JsonProperty("pedidos")
    private List<Pedido> pedidos;

    public EstabelecimentoResponseDTO(Estabelecimento estabelecimento){
        this.id = estabelecimento.getId();
        this.codigo = estabelecimento.getCodigo();
        this.email = estabelecimento.getEmail();
    }
}
