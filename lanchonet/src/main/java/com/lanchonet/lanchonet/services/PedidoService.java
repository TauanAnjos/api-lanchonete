package com.lanchonet.lanchonet.services;

import com.lanchonet.lanchonet.models.ItemPedido;
import com.lanchonet.lanchonet.models.Pedido;
import com.lanchonet.lanchonet.models.Produto;
import com.lanchonet.lanchonet.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public List<Pedido> ListPedido(){
        return pedidoRepository.findAll();
    }

    public ResponseEntity<Object> OnePedido(Long idPedido) {
        Optional<Pedido> pedidoOptional = pedidoRepository.findById(idPedido);
        if (pedidoOptional.isPresent()){
            Pedido pedido = pedidoOptional.get();
            pedido.calcularTotalPedido();
            return ResponseEntity.status(HttpStatus.OK).body(pedidoOptional);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public Pedido savePedido(Pedido pedido) {
        if(pedido.getItens() == null || pedido.getItens().isEmpty()){
            return null;
        }

        for (ItemPedido itemPedido : pedido.getItens()){

            itemPedido.setPedido(pedido);
            itemPedido.setProduto(itemPedido.getProduto());

        }
        pedido.calcularTotalPedido();
       return pedidoRepository.save(pedido);
    }


    public ResponseEntity<Object> pedidoUpdate(Long id, Pedido pedido) {
        Optional<Pedido> pedidoOptional = pedidoRepository.findById(id);
        if (pedidoOptional.isPresent()){
            Pedido updatePedido = pedidoOptional.get();
            updatePedido.getItens().clear();

            for(ItemPedido itemPedido : pedido.getItens()){
                itemPedido.setPedido(updatePedido);
                updatePedido.getItens().add(itemPedido);
            }
            updatePedido.setDataPedido(pedido.getDataPedido());
            updatePedido.calcularTotalPedido();

            return ResponseEntity.status(HttpStatus.OK).body(pedidoRepository.save(updatePedido));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    public ResponseEntity<Object> deletePedido(Long id){
        Optional<Pedido> pedidoOptional = pedidoRepository.findById(id);
        if (pedidoOptional.isPresent()){
            pedidoRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
