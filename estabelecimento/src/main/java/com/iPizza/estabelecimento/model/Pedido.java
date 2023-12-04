package com.iPizza.estabelecimento.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.iPizza.estabelecimento.enuns.StatusPedido;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente", nullable = false)
    @JsonProperty("cliente")
    private Cliente  cliente;

    @ManyToOne
    @JoinColumn(name = "estabelecimento_id", nullable = false)
    @JsonProperty("estabelecimento")
    private Estabelecimento  estabelecimento;

    @OneToOne(mappedBy = "pedido")
    @JsonIgnore
    private Entrega entrega;

    @ManyToOne
    @JoinColumn(name = "entregador")
    @JsonProperty("entregador")
    private Entregador  entregador;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<Pizza> pizzas;

    @Column(name = "preco")
    private Double preco;

    @Column(name = "status")
    @JsonProperty("status")
    private StatusPedido status;

    @Column(name = "enderecoEntrega", nullable = false)
    private Endereco enderecoEntrega;

    @Column(name = "statusPagamento")
    private boolean statusPagamento;

    public void calcValor(){
        Double total = 0.0;
        for (Pizza pizza: pizzas) {
            total += pizza.getPreco();
        }

        this.preco = total;
    }
}
