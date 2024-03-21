package br.com.tassicompany.agendadealunos.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.tassicompany.agendadealunos.R;
import br.com.tassicompany.agendadealunos.asynctask.BuscaPrimeiroTelefoneAlunoTask;
import br.com.tassicompany.agendadealunos.asynctask.RemoveAlunoTask;
import br.com.tassicompany.agendadealunos.database.AgendaDatabase;
import br.com.tassicompany.agendadealunos.database.dao.TelefoneDAO;
import br.com.tassicompany.agendadealunos.model.Aluno;

public class ListaAlunosAdapter extends BaseAdapter {

    private final Context context;
    private final List<Aluno> alunos = new ArrayList<>();
    private final TelefoneDAO telefoneDAO;

    public ListaAlunosAdapter(Context context) {
        this.context = context;
        this.telefoneDAO = AgendaDatabase.getInstance(context).getTelefoneDAO();
    }

    @Override
    public int getCount() {
        return alunos.size();
    }

    @Override
    public Aluno getItem(int posicao) {
        return alunos.get(posicao);
    }

    @Override
    public long getItemId(int posicao) {
        return alunos.get(posicao).getId();
    }

    @Override
    public View getView(int posicao, View view, ViewGroup viewGroup) {
        View viewCriada = criaView(viewGroup);
        Aluno alunoDevolvido = alunos.get(posicao);
        vinculaDados(viewCriada, alunoDevolvido);
        return viewCriada;
    }

    private void vinculaDados(View viewCriada, Aluno aluno) {
        TextView nome = viewCriada.findViewById(R.id.item_aluno_nome);
        nome.setText(aluno.getNome());
        TextView telefone = viewCriada.findViewById(R.id.item_aluno_telefone);
        new BuscaPrimeiroTelefoneAlunoTask(telefoneDAO, aluno.getId(), telefoneEncontrado ->
            telefone.setText(telefoneEncontrado.getNumero())).execute();
    }

    private View criaView(ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.item_aluno, viewGroup, false);
    }

    public void atualizaListaAlunos(List<Aluno> alunos) {
        this.alunos.clear();
        this.alunos.addAll(alunos);
        notifyDataSetChanged();
    }

    public void remove(Aluno aluno) {
        alunos.remove(aluno);
        notifyDataSetChanged();
    }
}
