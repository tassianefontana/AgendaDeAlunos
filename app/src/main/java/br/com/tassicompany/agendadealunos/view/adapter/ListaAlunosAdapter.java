package br.com.tassicompany.agendadealunos.view.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.tassicompany.agendadealunos.R;
import br.com.tassicompany.agendadealunos.model.Aluno;

public class ListaAlunosAdapter extends BaseAdapter {

    private final Context context;
    private final List<Aluno> alunos = new ArrayList<>();


    public ListaAlunosAdapter(Context context) {
        this.context = context;
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
        vinculaDados(posicao, viewCriada);
        return viewCriada;
    }

    @SuppressLint("SetTextI18n")
    private void vinculaDados(int posicao, View viewCriada) {
        Aluno alunoDevolvido = alunos.get(posicao);
        TextView nome = viewCriada.findViewById(R.id.item_aluno_nome);
        nome.setText(alunoDevolvido.getNomeCompleto() + " " + alunoDevolvido.dataFormatada());
        TextView telefoneFixo = viewCriada.findViewById(R.id.item_aluno_telefone);
        telefoneFixo.setText(alunoDevolvido.getTelefoneFixo());
    }

    private View criaView(ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.item_aluno, viewGroup, false);
    }

    public void AtualizaListaAlunos(List<Aluno> alunos) {
        this.alunos.clear();
        this.alunos.addAll(alunos);
    }

    public void remove(Aluno aluno) {
        alunos.remove(aluno);
        notifyDataSetChanged();
    }
}
