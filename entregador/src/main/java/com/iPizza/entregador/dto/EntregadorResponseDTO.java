package com.iPizza.entregador.dto;

import java.util.UUID;

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

    private UUID id;

    private String nome;

    private String placaVeiculo;

    private String corVeiculo;

    private String tipoVeiculo;

    public EntregadorResponseDTO(Entregador entregador) {
        this.id = entregador.getId();
        this.nome = entregador.getNome();
        this.placaVeiculo = entregador.getPlacaVeiculo();
        this.corVeiculo = entregador.getCorVeiculo();
        this.tipoVeiculo = entregador.getTipoVeiculo();
    }
}
