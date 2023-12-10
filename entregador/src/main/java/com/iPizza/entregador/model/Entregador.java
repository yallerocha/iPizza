package com.iPizza.entregador.model;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

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
public class Entregador implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String placaVeiculo;

    @Column(nullable = false)
    private String corVeiculo;

    @Column(nullable = false)
    private String tipoVeiculo;

    @Column(nullable = false)
    @JsonIgnore
    private String codigoAcesso;

    @Embedded
    @JsonIgnore
    @OneToMany(mappedBy = "entregadorId")
    private List<Associacao> associacoes;
}
