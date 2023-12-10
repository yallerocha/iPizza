package com.iPizza.entregador.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.iPizza.entregador.enuns.StatusEntrega;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Entrega implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime enviadoEm;

    @Column(nullable = true)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime entregueEm;

    @Column(nullable = false)
    private StatusEntrega status;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "id")
    private Pedido pedidoId;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_associacao", foreignKeyDefinition = "FOREIGN KEY (associacaoId) REFERENCES associacao(id) ON DELETE CASCADE"))
    private Associacao associacaoId;
}
