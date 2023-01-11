package br.com.tassicompany.agendadealunos.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import br.com.tassicompany.agendadealunos.R;
import br.com.tassicompany.agendadealunos.dao.AlunoDAO;
import br.com.tassicompany.agendadealunos.model.Aluno;

public class CadastroActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Novo Aluno";
    private EditText etNome;
    private EditText etTelefone;
    private EditText etEmail;
    private final AlunoDAO alunoDAO = new AlunoDAO();
    private String email;
    private Aluno alunoCadastrado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        setTitle(TITULO_APPBAR);

        inicializacaoDosCampos();

        configuraBotaoSalvar();

        Intent dados = getIntent();
        if (dados.hasExtra("aluno")){
            alunoCadastrado = (Aluno) dados.getSerializableExtra("aluno");
        }
    }

    private void configuraBotaoSalvar() {
        Button btnSalvar = findViewById(R.id.btn_salvar);
        btnSalvar.setOnClickListener(view -> {
            preencheAluno();
            alunoDAO.editarAluno(alunoCadastrado);
        });
    }

    private void inicializacaoDosCampos() {
        etNome = findViewById(R.id.cadastro_activity_etNome);
        etTelefone = findViewById(R.id.cadastro_activity_etTelefone);
        etEmail = findViewById(R.id.cadastro_activity_etEmail);
    }

    private void salvaAluno(Aluno aluno) {
        alunoDAO.salvarAluno(aluno);

        finish();
    }

        private void preencheAluno() {
        String nome = etNome.getText().toString();
        String telefone = etTelefone.getText().toString();
        String email = etEmail.getText().toString();

        alunoCadastrado.setNome(nome);
        alunoCadastrado.setTelefone(telefone);
        alunoCadastrado.setEmail(email);

    }
}