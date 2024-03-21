package br.com.tassicompany.agendadealunos.asynctask;

import android.os.AsyncTask;

import java.util.List;

import br.com.tassicompany.agendadealunos.database.dao.AlunoDAO;
import br.com.tassicompany.agendadealunos.model.Aluno;
import br.com.tassicompany.agendadealunos.view.adapter.ListaAlunosAdapter;

public class BuscaAlunoTask extends AsyncTask<Void, Void, List<Aluno>> {
    private final ListaAlunosAdapter adapter;
    private final AlunoDAO alunoDAO;

    public BuscaAlunoTask(AlunoDAO alunoDAO, ListaAlunosAdapter adapter) {
        this.adapter = adapter;
        this.alunoDAO = alunoDAO;
    }

    @Override
    protected List<Aluno> doInBackground(Void[] objects) {
        return alunoDAO.getAlunos();
    }

    @Override
    protected void onPostExecute(List<Aluno> alunos) {
        super.onPostExecute(alunos);
        adapter.atualizaListaAlunos(alunos);
    }
}
