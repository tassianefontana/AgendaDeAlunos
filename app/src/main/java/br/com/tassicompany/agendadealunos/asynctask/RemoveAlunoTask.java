package br.com.tassicompany.agendadealunos.asynctask;

import android.os.AsyncTask;

import java.util.List;

import br.com.tassicompany.agendadealunos.database.dao.AlunoDAO;
import br.com.tassicompany.agendadealunos.database.dao.TelefoneDAO;
import br.com.tassicompany.agendadealunos.model.Aluno;
import br.com.tassicompany.agendadealunos.model.Telefone;
import br.com.tassicompany.agendadealunos.view.adapter.ListaAlunosAdapter;

public class RemoveAlunoTask extends AsyncTask<Void, Void, Void> {

    private final Aluno aluno;
    private final AlunoDAO alunoDAO;
    private final ListaAlunosAdapter listaAlunosAdapter;

    public RemoveAlunoTask(Aluno aluno, AlunoDAO alunoDAO, ListaAlunosAdapter listaAlunosAdapter) {
        this.aluno = aluno;
        this.alunoDAO = alunoDAO;
        this.listaAlunosAdapter = listaAlunosAdapter;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        alunoDAO.remove(aluno);
        return null;
    }


    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        listaAlunosAdapter.remove(aluno);
    }

}
