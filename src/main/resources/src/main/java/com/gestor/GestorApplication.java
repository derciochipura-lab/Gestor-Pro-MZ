package com.gestor;

import jakarta.persistence.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@SpringBootApplication
public class GestorApplication {
    public static void main(String[] args) {
        SpringApplication.run(GestorApplication.class, args);
    }
}

// TABELA NO BANCO DE DADOS
@Entity
class Produto {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String nome;
    public Double precoCusto;
    public Double precoVenda;
    public Integer stockActual;
    public Integer stockMinimo = 5;
}

// INTERFACE PARA O BANCO
interface ProdutoRepository extends JpaRepository<Produto, Long> {}

// CONTROLADOR DA API
@RestController
@RequestMapping("/api/produtos")
@CrossOrigin("*")
class ProdutoController {
    @Autowired private ProdutoRepository repo;

    @GetMapping
    public List<Produto> listar() { return repo.findAll(); }

    @PostMapping
    public Produto salvar(@RequestBody Produto p) { return repo.save(p); }
}
