package br.com.tassicompany.agendadealunos.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.tassicompany.agendadealunos.model.Aluno;

public class AlunoDAO {

    private final static List<Aluno> listaAlunos = new ArrayList<>();
    private static int contadorDeId = 1;

    public void salvarAluno(Aluno aluno) {
        aluno.setId(contadorDeId);
        listaAlunos.add(aluno);
        contadorDeId++;
    }

    public void editarAluno(Aluno aluno) {
        Aluno alunoEncontrado = null;
        for (Aluno a : listaAlunos) {
            if (a.getId() == aluno.getId()) {
                alunoEncontrado = a;
            }

            if(alunoEncontrado != null){
                int posicaoDoAluno = listaAlunos.indexOf(alunoEncontrado);
                listaAlunos.set(posicaoDoAluno, aluno);
            }
        }
    }

    public List<Aluno> getAlunos() {
        //Devolve uma cópia da lista original. Dessa forma, apenas esta classe tem a capacidade
        //de modificar os elementos da lista.
        return new ArrayList<>(listaAlunos);
    }

}
