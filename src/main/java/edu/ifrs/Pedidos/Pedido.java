package edu.ifrs.Pedidos;

import java.util.List;

public class Pedido {

    private List<ItemPedido> itens;
    private DescontoService descontoService;

    public Pedido(List<ItemPedido> itens, DescontoService descontoService) {
        this.itens = itens;
        this.descontoService = descontoService;
    }

    public double calcularValorTotal() {
        double valorTotal = 0.0;

        if (itens.equals(null)){
            return 0.0;
        }

        for (ItemPedido item : itens) {
            valorTotal += item.getSubtotal();
        }

        
        double desconto = descontoService.calcularDesconto(valorTotal);
        if (desconto > valorTotal){
            throw new IllegalArgumentException();
        }
        return valorTotal - desconto;
    }

    public double calcularValorTotalPorItem(ItemPedido itemPedido) {
        double valorTotalItem = 0.0;
        double desconto = 0.0;

        if (itens.equals(null)){
            return 0.0;
        }

        desconto = descontoService.calcularDesconto(itemPedido.getSubtotal());
        
        
        return valorTotalItem - desconto;
    }
}