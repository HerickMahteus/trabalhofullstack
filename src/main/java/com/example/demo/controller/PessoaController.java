package com.example.demo.controller;

import com.example.demo.model.Pessoa;
import com.example.demo.service.PessoaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    public PessoaService pessoaService;

    @GetMapping
    public List<Pessoa> listar() {
        return pessoaService.listar();
    }

    @PostMapping
    public ResponseEntity<Pessoa> criar(@Valid @RequestBody Pessoa pessoa) {
        Pessoa pessoaSalva = pessoaService.criar(pessoa);
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaSalva);
    }

    @PutMapping("/{cpf}")
    public ResponseEntity<?> atualizar(@PathVariable int cpf, @RequestBody Pessoa pessoa) {
        if (pessoaService.atualizar(cpf, pessoa) == null) {
            String mensagem = "O id (cpf) não existe na base de dados";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensagem);
        }
        return ResponseEntity.ok(pessoa);
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<?> deletar(@PathVariable int cpf) {
        if (pessoaService.deletar(cpf)) {
            String mensagem = "A deleção do id (cpf): " + cpf + " foi realizada com sucesso.";
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(mensagem);
        }
        String mensagem = "O id (cpf) informado não existe na base de dados";
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensagem);
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<Pessoa> buscarPorCpf(@PathVariable int cpf) {
        return pessoaService.buscarPorCpf(cpf)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
}