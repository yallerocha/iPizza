package com.iPizza.entregador.dto;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.iPizza.entregador.model.Entregador;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EntregadorResponseDTO {

    @JsonProperty("id")
    private UUID id;
    @JsonProperty("nome")
    private String nome;
    @JsonProperty("placaVeiculo")
    private String placaVeiculo;
    @JsonProperty("corVeiculo")
    private String corVeiculo;
    @JsonProperty("tipoVeiculo")
    private String tipoVeiculo;

    public EntregadorResponseDTO(Entregador entregador){
        this.id = entregador.getId();
        this.nome = entregador.getNome();
        this.placaVeiculo = entregador.getPlacaVeiculo();
        this.corVeiculo = entregador.getCorVeiculo();
        this.tipoVeiculo = entregador.getTipoVeiculo();
    }



}
