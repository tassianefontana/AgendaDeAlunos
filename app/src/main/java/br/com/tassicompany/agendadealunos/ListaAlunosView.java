package br.com.tassicompany.agendadealunos;

import android.app.AlertDialog;
import android.content.Context;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import br.com.tassicompany.agendadealunos.asynctask.BuscaAlunoTask;
import br.com.tassicompany.agendadealunos.database.AgendaDatabase;
import br.com.tassicompany.agendadealunos.database.dao.AlunoDAO;
import br.com.tassicompany.agendadealunos.model.Aluno;
import br.com.tassicompany.agendadealunos.view.adapter.ListaAlunosAdapter;
import br.com.tassicompany.agendadealunos.asynctask.RemoveAlunoTask;

public class ListaAlunosView {

    private final ListaAlunosAdapter adapter;
    private final Context context;
    private final AlunoDAO alunoDAO;

    public ListaAlunosView(Context context) {
        this.context = context;
        this.adapter = new ListaAlunosAdapter(this.context);
        this.alunoDAO = AgendaDatabase.getInstance(context).getAlunoDAO();
    }

    public void confirmaRemocao(final MenuItem item) {
        new AlertDialog.Builder(context)
                .setTitle("Removendo aluno")
                .setMessage("Tem certeza de que deseja remover o aluno?")
                .setPositiveButton("Sim", (dialogInterface, i) -> {
                    AdapterView.AdapterContextMenuInfo adapterContextMenuInfo =
                            (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                    Aluno alunoEscolhido = adapter.getItem(adapterContextMenuInfo.position);
                    removeAluno(alunoEscolhido);
                })
                .setNegativeButton("Não", null)
                .show();
    }

    public void atualizaAlunos() {
        //Método execute cria uma thread paralela para rodar simultaneamente à main thread
        new BuscaAlunoTask(alunoDAO, adapter).execute();

    }

    private void removeAluno(Aluno aluno) {
        new RemoveAlunoTask(aluno, alunoDAO, adapter).execute();
    }

    public void configuraAdapter(ListView listaAlunos) {
        listaAlunos.setAdapter(adapter);
    }
}
