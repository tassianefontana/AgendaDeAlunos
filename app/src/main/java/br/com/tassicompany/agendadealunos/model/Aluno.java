package br.com.tassicompany.agendadealunos.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Aluno implements Serializable {

    int id = 0;
    String nome;
    String telefone;
    String email;

    public Aluno(String nome, String telefone, String email) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String endereco) {
        this.email = endereco;
    }

    public void setId(int id) { this.id = id; }

    public int getId() {return id; }

    @NonNull
    @Override
    public String toString() {
        return nome;
    }

}
