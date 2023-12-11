package com.iPizza.entregador.dto;

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
public class EntregadorPostGetPutRequestDTO {

    @NotNull(message = "Nome e obrigatorio")
    @NotBlank(message = "Nome e obrigatorio")
    private String nome;

    @NotNull
    @NotBlank
    @Size(min = 6, max = 6, message = "O código de acesso deve ter exatamente 6 dígitos")
    private String codigo;

    @NotNull(message = "Placa do veiculo e obrigatoria")
    @NotBlank(message = "Placa do veiculo e obrigatoria")
    private String placaVeiculo;

    @NotNull(message = "Cor do veiculo e obrigatoria")
    @NotBlank(message = "Cor do veiculo e obrigatoria")
    private String corVeiculo;

    @NotNull(message = "Tipo do veiculo e obrigatorio")
    @NotBlank(message = "Tipo do veiculo e obrigatorio")
    @TipoVeiculo
    private String tipoVeiculo;

}
