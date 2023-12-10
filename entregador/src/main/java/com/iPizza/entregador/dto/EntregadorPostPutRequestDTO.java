package com.iPizza.entregador.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.iPizza.entregador.rule.TipoVeiculo;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EntregadorPostPutRequestDTO {

    @NotNull(message = "Nome e obrigatorio")
    @NotBlank(message = "Nome e obrigatorio")
    @JsonProperty("nome")
    private String nome;

    @NotNull
    @Size(min = 6, max = 6, message = "O código de acesso deve ter exatamente 6 dígitos")
    @NotBlank
    @JsonProperty("codigoAcesso")
    private String codigoAcesso;

    @NotNull(message = "Placa do veiculo e obrigatoria")
    @NotBlank(message = "Placa do veiculo e obrigatoria")
    @JsonProperty("placaVeiculo")
    private String placaVeiculo;

    @NotNull(message = "Cor do veiculo e obrigatoria")
    @NotBlank(message = "Cor do veiculo e obrigatoria")
    @JsonProperty("corVeiculo")
    private String corVeiculo;

    @NotNull(message = "Tipo do veiculo e obrigatorio")
    @NotBlank(message = "Tipo do veiculo e obrigatorio")
    @TipoVeiculo
    @JsonProperty("tipoVeiculo")
    private String tipoVeiculo;

}
