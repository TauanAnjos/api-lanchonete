package com.lanchonet.lanchonet.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

import java.util.List;

@Entity
@Table(name = "pedidos_db")
public class Pedido implements Serializable {
    private static final  long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPedido;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemPedido> itens;

    private LocalDateTime dataPedido;

    private Double valorTotal;

    public Pedido() {
    }

    public Pedido(List<ItemPedido> itens, LocalDateTime dataPedido, Double valorTotal) {
        this.itens = itens;
        this.dataPedido = dataPedido;
        this.valorTotal = valorTotal;
    }

    public Long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Long idPedido) {
        this.idPedido = idPedido;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }

    public LocalDateTime getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDateTime dataPedido) {
        this.dataPedido = dataPedido;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }
    public void calcularTotalPedido(){
        double total = 0;
        for(ItemPedido itemPedido : itens){
            if(itemPedido.getPedido() != null && itemPedido.getProduto().getPreco() != null){
                total += itemPedido.getProduto().getPreco() * itemPedido.getQuantidade();
            }

        }
        this.valorTotal = total;
    }
}
