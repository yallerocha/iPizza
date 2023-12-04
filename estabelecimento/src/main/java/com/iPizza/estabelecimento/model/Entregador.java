package com.iPizza.estabelecimento.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Entregador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "entregador")
    @Embedded
    @JsonIgnore
    private List<Associacao> associacoes;

    @Column(name = "nome" ,nullable = false)
    private String nome;

    @Column(name = "placa" ,nullable = false)
    private String placaVeiculo;

    @Column(name = "corVeiculo" ,nullable = false)
    private String corVeiculo;

    @Column(name = "tipoVeiculo" ,nullable = false)
    private String tipoVeiculo;

    @Column(name = "codigoAcesso" ,nullable = false)
    @JsonIgnore
    private String codigoAcesso;
}
