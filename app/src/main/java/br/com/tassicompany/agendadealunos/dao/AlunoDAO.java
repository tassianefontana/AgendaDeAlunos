package br.com.tassicompany.agendadealunos.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.tassicompany.agendadealunos.model.Aluno;

public class AlunoDAO {
    private final static List<Aluno> listaAlunos = new ArrayList<>();

    public void salvarAluno(Aluno aluno) {
        listaAlunos.add(aluno);
    }

    public List<Aluno> getAlunos() {
        //Devolve uma cópia da lista original. Dessa forma, apenas esta classe tem a capacidade
        //de modificar os elementos da lista.
        return new ArrayList<>(listaAlunos);
    }

}
