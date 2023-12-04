package com.iPizza.estabelecimento.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.iPizza.estabelecimento.enuns.StatusEntrega;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Entrega {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty
    private Long id;

    @ManyToOne
    @JsonProperty("associacao")
    @JoinColumn(name = "associacao_id", foreignKey = @ForeignKey(name = "fk_associacao", foreignKeyDefinition = "FOREIGN KEY (associacao_id) REFERENCES associacao(id) ON DELETE CASCADE"))
    private Associacao associacao;

    @Column(name = "enviado_em", nullable = false)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonProperty
    private LocalDateTime enviadoEm;

    @Column(name = "entregue_em", nullable = true)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonProperty
    private LocalDateTime entregueEm;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pedido_id", referencedColumnName = "id")
    private Pedido pedido;

    @Column(name = "status", nullable = false)
    @JsonProperty("status")
    private StatusEntrega status;
}
