package br.com.tassicompany.agendadealunos.view;

import static br.com.tassicompany.agendadealunos.view.ConstantesActivities.CHAVE_ALUNO;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import br.com.tassicompany.agendadealunos.R;
import br.com.tassicompany.agendadealunos.dao.AlunoDAO;
import br.com.tassicompany.agendadealunos.model.Aluno;

public class MainActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Lista de alunos";
    public final String TAG = MainActivity.class.getSimpleName();
    private final AlunoDAO alunoDAO = new AlunoDAO();
    private ArrayAdapter<Aluno> adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(TITULO_APPBAR);

        configuraNovoAluno();
        configuraLista();

        alunoDAO.salvarAluno(new Aluno("Tassiane", "41 99999999",
                "tassiane.fontana@gmail.com"));
        alunoDAO.salvarAluno(new Aluno("Carol", "41 99999999",
                "exemploemail@gmail.com"));
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add("Remover");
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo adapterContextMenuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Aluno alunoEscolhido = adapter.getItem(adapterContextMenuInfo.position);
        removeAluno(alunoEscolhido);
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.clear();
        adapter.addAll(alunoDAO.getAlunos());
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
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        listaAlunos.setAdapter(adapter);
    }
}
