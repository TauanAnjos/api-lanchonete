package com.lanchonet.lanchonet.controllers;

import com.lanchonet.lanchonet.models.Pedido;
import com.lanchonet.lanchonet.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public List<Pedido> getAll(){
        List<Pedido> pedidos = pedidoService.ListPedido();
        pedidos.forEach(pedido -> pedido.calcularTotalPedido());
        return pedidos;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOne(@PathVariable(value = "id")Long id){
        return ResponseEntity.status(HttpStatus.OK).body(pedidoService.OnePedido(id));
    }

    @PostMapping
    public ResponseEntity<Pedido> createPedido(@RequestBody Pedido pedido){
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoService.savePedido(pedido));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePedido(@PathVariable(value = "id")Long id, @RequestBody Pedido pedido){
        return ResponseEntity.status(HttpStatus.OK).body(pedidoService.pedidoUpdate(id, pedido));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePedido(@PathVariable(value = "id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(pedidoService.deletePedido(id));
    }
}
