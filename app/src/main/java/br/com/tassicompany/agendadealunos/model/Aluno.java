package br.com.tassicompany.agendadealunos.model;

import androidx.annotation.NonNull;

public class Aluno {

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

    @NonNull
    @Override
    public String toString() {
        return nome;
    }
}
