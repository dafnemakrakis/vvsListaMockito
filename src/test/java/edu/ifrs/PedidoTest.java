package edu.ifrs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import edu.ifrs.Pedidos.DescontoService;
import edu.ifrs.Pedidos.ItemPedido;
import edu.ifrs.Pedidos.Pedido;

@ExtendWith(MockitoExtension.class)
public class PedidoTest {

    @org.mockito.Mock
    DescontoService descontoService;

    Pedido pedido;


    @Test
    public void calcularValorTotalPorcentagem (){

        List<ItemPedido> itemPedidoList = new ArrayList<>();
        itemPedidoList.add(new ItemPedido(50));
        itemPedidoList.add(new ItemPedido(50));

        when(descontoService.calcularDesconto(100)).thenReturn(10.0);

        pedido = new Pedido(itemPedidoList, descontoService);

        double valorTotal = pedido.calcularValorTotal();
        assertEquals(90.0,valorTotal);
    }


     @Test
    public void calcularValorTotalSemDesconto (){

        List<ItemPedido> itemPedidoList = new ArrayList<>();
        itemPedidoList.add(new ItemPedido(50));
        itemPedidoList.add(new ItemPedido(50));

        when(descontoService.calcularDesconto(100)).thenReturn(0.0);

        pedido = new Pedido(itemPedidoList, descontoService);

        double valorTotal = pedido.calcularValorTotal();
        assertEquals(100.0,valorTotal);
    }

    @Test
    public void calcularValorTotalDescontoMaior (){

        List<ItemPedido> itemPedidoList = new ArrayList<>();
        itemPedidoList.add(new ItemPedido(50));
        itemPedidoList.add(new ItemPedido(50));

        when(descontoService.calcularDesconto(100)).thenReturn(200.0);

        pedido = new Pedido(itemPedidoList, descontoService);

        double valorTotal = pedido.calcularValorTotal();
        assertEquals(0.0,valorTotal);
    }

    @Test
    public void calcularValorTotalNegativo (){

        List<ItemPedido> itemPedidoList = new ArrayList<>();
        itemPedidoList.add(new ItemPedido(50));
        itemPedidoList.add(new ItemPedido(50));

        when(descontoService.calcularDesconto(100)).thenReturn(200.0);

        pedido = new Pedido(itemPedidoList, descontoService);

       
        assertThrows(IllegalArgumentException.class, ()->pedido.calcularValorTotal());
    }

    @Test
    public void calcularValorSemItens (){

        List<ItemPedido> itemPedidoList = new ArrayList<>();

        pedido = new Pedido(itemPedidoList, descontoService);

        double valorTotal = pedido.calcularValorTotal();
        assertEquals(0.0,valorTotal);
    }

    @Test
    public void calcularValorTotalDiferentesDescontos (){

        List<ItemPedido> itemPedidoList = new ArrayList<>();
        itemPedidoList.add(new ItemPedido(100));
        itemPedidoList.add(new ItemPedido(100));

        when(descontoService.calcularDesconto(anyDouble())).thenReturn(10.0, 10.0);

        pedido = new Pedido(itemPedidoList, descontoService);

        double valorTotal = pedido.calcularValorTotalPorItem(itemPedidoList.get(1));
        assertEquals(0.0,valorTotal);
    }

    @Test
    public void calcularValorTotalMetodo (){

        List<ItemPedido> itemPedidoList = new ArrayList<>();
        itemPedidoList.add(new ItemPedido(50));
        itemPedidoList.add(new ItemPedido(50));

        when(descontoService.calcularDesconto(100)).thenReturn(10.0);

        pedido = new Pedido(itemPedidoList, descontoService);

        double valorTotal = pedido.calcularValorTotal();
        verify(descontoService, times(1)).calcularDesconto(100);

        assertEquals(90.0,valorTotal);
    }


    
}
