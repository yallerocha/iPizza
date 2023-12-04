package com.iPizza.estabelecimento.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {
    @Column(name = "cep", nullable = false)
    private String cep;
    @Column(name = "bairro", nullable = false)
    private String bairro;
    @Column(name = "rua", nullable = false)
    private String rua;
    @Column(name = "numero", nullable = false)
    private String numero;
    @Column(name = "complemento", nullable = false)
    private String complemento;
    @Column(name = "estado", nullable = false)
    private String estado;
    @Column(name = "cidade", nullable = false)
    private String cidade;
}
