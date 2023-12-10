package com.iPizza.entregador.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.iPizza.entregador.enuns.StatusPedido;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Pedido implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Estabelecimento estabelecimentoId;

    @OneToOne(mappedBy = "pedidoId")
    @JsonIgnore
    private Entrega entrega;

    @ManyToOne
    @JoinColumn(name = "entregador")
    private Entregador entregador;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<Pizza> pizzas;

    private Double preco;

    private StatusPedido status;

    @Column(nullable = false)
    private Endereco enderecoEntrega;

    private boolean statusPagamento;

    public void calcValor() {
        Double total = 0.0;
        for (Pizza pizza: pizzas) {
            total += pizza.getPreco();
        }
        this.preco = total;
    }
}
