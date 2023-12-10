package com.iPizza.entregador.model;

import java.io.Serializable;

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
public class Endereco implements Serializable {

    @Column(nullable = false)
    private String cep;

    @Column(nullable = false)
    private String bairro;

    @Column(nullable = false)
    private String rua;

    @Column(nullable = false)
    private String numero;

    @Column(nullable = false)
    private String complemento;

    @Column(nullable = false)
    private String estado;
    
    @Column(nullable = false)
    private String cidade;
}
