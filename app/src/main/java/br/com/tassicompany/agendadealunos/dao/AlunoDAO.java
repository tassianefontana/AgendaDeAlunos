package br.com.tassicompany.agendadealunos.dao;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import br.com.tassicompany.agendadealunos.model.Aluno;

public class AlunoDAO {

    private final static List<Aluno> listaAlunos = new ArrayList<>();
    private static int contadorDeId = 1;

    public void salvarAluno(Aluno aluno) {
        aluno.setId(contadorDeId);
        listaAlunos.add(aluno);
        atualizaId();
    }

    private void atualizaId() {
        contadorDeId++;
    }

    public void editarAluno(Aluno aluno) {
        Aluno alunoEncontrado = buscaAlunoPeloId(aluno);

        if (alunoEncontrado != null) {
            int posicaoDoAluno = listaAlunos.indexOf(alunoEncontrado);
            listaAlunos.set(posicaoDoAluno, aluno);
        }
    }

    @Nullable
    private Aluno buscaAlunoPeloId(Aluno aluno) {
        for (Aluno a : listaAlunos) {
            if (a.getId() == aluno.getId()) {
                return a;
            }
        }
        return null;
    }

    public List<Aluno> getAlunos() {
        //Devolve uma cópia da lista original. Dessa forma, apenas esta classe tem a capacidade
        //de modificar os elementos da lista.
        return new ArrayList<>(listaAlunos);
    }

    public void remove(Aluno aluno) {
        Aluno alunoDevolvido = buscaAlunoPeloId(aluno);
        if (alunoDevolvido != null){
            listaAlunos.remove(alunoDevolvido);
        }
    }
}
