package com.lanchonet.lanchonet.controllers;

import com.lanchonet.lanchonet.models.Produto;
import com.lanchonet.lanchonet.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public List<Produto> getAllProdutos(){
        return produtoService.ListProdutos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneProduct(@PathVariable(name = "id")Long id){
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.OneProduct(id));
    }


    @PostMapping
    public ResponseEntity<Produto> createProduct(@RequestBody Produto produto){
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.saveProduct(produto));
    }
}
