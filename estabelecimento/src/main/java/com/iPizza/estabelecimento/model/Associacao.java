package com.iPizza.estabelecimento.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.iPizza.estabelecimento.enuns.StatusAssociacao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Associacao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "estabelecimento")
    @JsonProperty("estabelecimento")
    private Estabelecimento estabelecimento;

    @ManyToOne
    @JoinColumn(name = "entregador")
    @JsonProperty("entregador")
    private Entregador entregador;

    @OneToMany(mappedBy = "associacao", fetch = FetchType.EAGER)
    @JsonProperty("entregas")
    private List<Entrega> entregas;

    @Column(name = "status", nullable = false)
    @JsonProperty("status")
    private StatusAssociacao status;

    @Column(name = "statusEntregador")
    @JsonProperty("statusEntregador")
    private String statusEntregador;

}
