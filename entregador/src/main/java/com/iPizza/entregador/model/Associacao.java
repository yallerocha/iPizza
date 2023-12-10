package com.iPizza.entregador.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import com.iPizza.entregador.enuns.StatusAssociacao;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Associacao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private StatusAssociacao status;

    private String statusEntregador;

    @ManyToOne
    @JoinColumn(name = "estabelecimento")
    private Estabelecimento estabelecimentoId;

    @ManyToOne
    @JoinColumn(name = "entregador")
    private Entregador entregadorId;

    @OneToMany(mappedBy = "associacaoId", fetch = FetchType.EAGER)
    private List<Entrega> entregas;

}
