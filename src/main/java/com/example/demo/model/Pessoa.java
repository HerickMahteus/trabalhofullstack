package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class Pessoa {
    @NotNull
    private String nome;
    @Id
    @NotNull
    private Integer cpf;
    @NotNull
    private int telefone;

    public Pessoa() {}

    public Pessoa(String nome, int cpf, int telefone) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
    }

    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}
    public Integer getCpf() {return cpf;}
    public void setCpf(Integer cpf) {this.cpf = cpf;}
    public int getTelefone() {return telefone;}
    public void setTelefone(int telefone) {this.telefone = telefone;}
}
