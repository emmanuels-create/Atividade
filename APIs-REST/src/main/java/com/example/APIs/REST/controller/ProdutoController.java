package com.example.APIs.REST.controller;

public class ProdutoController {

    package com.example.api.controller;

import com.example.APIs.model.Produto;
import com.example.api.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

    @RestController
    @RequestMapping("/api/produtos")
    public class ProdutoController {

        @Autowired
        private ProdutoRepository repository;

        // CREATE
        @PostMapping
        public Produto criar(@RequestBody Produto produto) {
            return repository.save(produto);
        }

        // READ (todos)
        @GetMapping
        public List<Produto> listar() {
            return repository.findAll();
        }

        // READ (por ID)
        @GetMapping("/{id}")
        public ResponseEntity<Produto> buscarPorId(@PathVariable Long id) {
            return repository.findById(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        }

        // UPDATE
        @PutMapping("/{id}")
        public ResponseEntity<Produto> atualizar(@PathVariable Long id, @RequestBody Produto produtoAtualizado) {
            return repository.findById(id)
                    .map(produto -> {
                        produto.setNome(produtoAtualizado.getNome());
                        produto.setPreco(produtoAtualizado.getPreco());
                        produto.setQuantidade(produtoAtualizado.getQuantidade());
                        repository.save(produto);
                        return ResponseEntity.ok(produto);
                    })
                    .orElse(ResponseEntity.notFound().build());
        }

        // DELETE
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deletar(@PathVariable Long id) {
            return repository.findById(id)
                    .map(produto -> {
                        repository.delete(produto);
                        return ResponseEntity.noContent().build();
                    })
                    .orElse(ResponseEntity.notFound().build());
        }
    }

}
