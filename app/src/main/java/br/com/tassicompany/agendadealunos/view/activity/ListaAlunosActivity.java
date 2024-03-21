package br.com.tassicompany.agendadealunos.view.activity;

import static br.com.tassicompany.agendadealunos.view.activity.ConstantesActivities.CHAVE_ALUNO;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import br.com.tassicompany.agendadealunos.R;
import br.com.tassicompany.agendadealunos.model.Aluno;
import br.com.tassicompany.agendadealunos.ListaAlunosView;

public class ListaAlunosActivity extends AppCompatActivity {

    private static final String TITULO_APPBAR = "Lista de alunos";
    private final String TAG = ListaAlunosActivity.class.getSimpleName();
    private ListaAlunosView listaAlunosView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);
        listaAlunosView = new ListaAlunosView(this);
        setTitle(TITULO_APPBAR);
        configuraNovoAluno();
        configuraLista();

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activity_lista_alunos_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.activity_lista_alunos_menu_remover) {
            listaAlunosView.confirmaRemocao(item);
        }
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        listaAlunosView.atualizaAlunos();
    }

    private void configuraNovoAluno() {
        FloatingActionButton botaoCadastrarAluno = findViewById(R.id.activity_main_fab_addAlunos);
        botaoCadastrarAluno.setOnClickListener(view -> abreFormularioCadastraAluno());
    }

    private void abreFormularioCadastraAluno() {
        startActivity(new Intent(this, FormularioAlunoActivity.class));
    }

    private void configuraLista() {
        ListView listaAlunos = findViewById(R.id.activity_main_lista_alunos);
        listaAlunosView.configuraAdapter(listaAlunos);
        configuraCliqueDeListenerPorItem(listaAlunos);
        registerForContextMenu(listaAlunos);
    }

    private void configuraCliqueDeListenerPorItem(ListView listaAlunos) {
        listaAlunos.setOnItemClickListener((adapterView, view, posicao, id) -> {
            Aluno alunoEscolhido = (Aluno) adapterView.getItemAtPosition(posicao);
            Log.d(TAG, "Aluno: " + alunoEscolhido);

            abreFormularioEditaAluno(alunoEscolhido);
        });
    }

    private void abreFormularioEditaAluno(Aluno alunoEscolhido) {
        Intent intent = new Intent(this, FormularioAlunoActivity.class);
        intent.putExtra(CHAVE_ALUNO, alunoEscolhido);
        startActivity(intent);
    }

}
