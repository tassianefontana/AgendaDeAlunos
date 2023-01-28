package br.com.tassicompany.agendadealunos.view;

import static br.com.tassicompany.agendadealunos.view.ConstantesActivities.CHAVE_ALUNO;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import br.com.tassicompany.agendadealunos.R;
import br.com.tassicompany.agendadealunos.dao.AlunoDAO;
import br.com.tassicompany.agendadealunos.model.Aluno;
import br.com.tassicompany.agendadealunos.view.adapter.ListaAlunosAdapter;

public class ListaAlunosActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Lista de alunos";
    public final String TAG = ListaAlunosActivity.class.getSimpleName();
    private final AlunoDAO alunoDAO = new AlunoDAO();
    private ListaAlunosAdapter adapter;
    private Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);
        setTitle(TITULO_APPBAR);
        mContext = this.getApplicationContext();
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
            confirmaRemocao(item);
        }
        return super.onContextItemSelected(item);
    }

    private void confirmaRemocao(final MenuItem item) {
        new AlertDialog.Builder(this)
                .setTitle("Removendo aluno")
                .setMessage("Tem certeza de que deseja remover o aluno?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        AdapterView.AdapterContextMenuInfo adapterContextMenuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                        Aluno alunoEscolhido = adapter.getItem(adapterContextMenuInfo.position);
                        removeAluno(alunoEscolhido);
                    }
                })
                .setNegativeButton("Não", null)
                .show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.AtualizaListaAlunos(alunoDAO.getAlunos());
    }

    private void configuraNovoAluno() {
        FloatingActionButton botaoCadastrarAluno = findViewById(R.id.activity_main_fab_addAlunos);
        botaoCadastrarAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abreFormularioCadastraAluno();
            }
        });
    }

    private void abreFormularioCadastraAluno() {
        startActivity(new Intent(this, FormularioAlunoActivity.class));
    }

    private void configuraLista() {
        ListView listaAlunos = findViewById(R.id.activity_main_lista_alunos);
        configuraAdapter(listaAlunos);
        configuraCliqueDeListenerPorItem(listaAlunos);
        registerForContextMenu(listaAlunos);
    }

    private void removeAluno(Aluno aluno) {
        alunoDAO.remove(aluno);
        adapter.remove(aluno);
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

    private void configuraAdapter(ListView listaAlunos) {
        adapter = new ListaAlunosAdapter(mContext);
        listaAlunos.setAdapter(adapter);
    }
}
