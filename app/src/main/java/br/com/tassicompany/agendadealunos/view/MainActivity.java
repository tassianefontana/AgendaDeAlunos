package br.com.tassicompany.agendadealunos.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
import java.util.List;

import br.com.tassicompany.agendadealunos.R;
import br.com.tassicompany.agendadealunos.dao.AlunoDAO;
import br.com.tassicompany.agendadealunos.model.Aluno;

public class MainActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Lista de alunos";
    public static final String TAG = MainActivity.class.getSimpleName();
    private final AlunoDAO alunoDAO = new AlunoDAO();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(TITULO_APPBAR);

        configuraNovoAluno();

        alunoDAO.salvarAluno(new Aluno("Tassiane","41 99999999",
                "tassiane.fontana@gmail.com"));
        alunoDAO.salvarAluno(new Aluno("Carol","41 99999999",
                "exemploemail@gmail.com"));
    }

    @Override
    protected void onResume() {
        super.onResume();
        configuraLista();
    }

    private void configuraNovoAluno() {
        FloatingActionButton botaoCadastrarAluno = findViewById(R.id.activity_main_fab_addAlunos);
        botaoCadastrarAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abreCadastroActivity();
            }
        });
    }

    private void abreCadastroActivity() {
        startActivity(new Intent(this, CadastroActivity.class));
    }

    private void configuraLista() {
        ListView listaAlunos = findViewById(R.id.activity_main_lista_alunos);
        final List<Aluno> alunos = alunoDAO.getAlunos();
        listaAlunos.setAdapter(new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1,
                alunos));

        listaAlunos.setOnItemClickListener((adapterView, view, posicao, id) -> {
            Aluno alunoEscolhido = alunos.get(posicao);
            Log.d(TAG, "Aluno: " + alunoEscolhido);

            Intent intent = new Intent(this, CadastroActivity.class);
            intent.putExtra("aluno", alunoEscolhido);
            startActivity(intent);

        });

    }
}
