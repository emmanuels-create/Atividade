package com.example.APIs.REST.repository;

public class ProdutoRepository {
    package com.example.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.api.model.Produto;

    public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    }

}
