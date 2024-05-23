package com.lanchonet.lanchonet.services;

import com.lanchonet.lanchonet.models.Produto;
import com.lanchonet.lanchonet.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;
    public List<Produto> ListProdutos() {
        return produtoRepository.findAll();
    }

    public ResponseEntity<Object> OneProduct(Long id) {
        Optional<Produto> produtoOptional = produtoRepository.findById(id);
        if(produtoOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(produtoOptional);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public Produto saveProduct(Produto produto){
        return produtoRepository.save(produto);
    }

    public ResponseEntity<Object> produtoUpdate(Long id, Produto produto) {
        Optional<Produto> produtoOptional = produtoRepository.findById(id);
        if (produtoOptional.isPresent()){
            Produto updateProduto = produtoOptional.get();

            updateProduto.setNomeProduto(produto.getNomeProduto());
            updateProduto.setDescricao(produto.getDescricao());
            updateProduto.setPreco(produto.getPreco());
            return ResponseEntity.status(HttpStatus.OK).body(produtoRepository.save(updateProduto));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    public Object produtoDelete(Long id) {
        Optional<Produto> optionalProduto = produtoRepository.findById(id);
        if (optionalProduto.isPresent()){
            produtoRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
