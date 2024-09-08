package com.example.demo.service;

import com.example.demo.model.Pessoa;
import com.example.demo.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public List<Pessoa> listar() {
        return pessoaRepository.findAll();
    }

    public Pessoa criar(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    public Pessoa atualizar(int cpf, Pessoa pessoa) {
        if (pessoaRepository.existsById(cpf)) {
            pessoa.setCpf(cpf);
            return pessoaRepository.save(pessoa);
        }
        return null;
    }

    public boolean deletar(int cpf) {
        if (pessoaRepository.existsById(cpf)) {
            pessoaRepository.deleteById(cpf);
            return true;
        }
        return false;
    }

    public Optional<Pessoa> buscarPorCpf(int cpf) {
        return pessoaRepository.findById(cpf);
    }
}