package br.com.tassicompany.agendadealunos.view;

import static br.com.tassicompany.agendadealunos.view.ConstantesActivities.CHAVE_ALUNO;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import br.com.tassicompany.agendadealunos.R;
import br.com.tassicompany.agendadealunos.dao.AlunoDAO;
import br.com.tassicompany.agendadealunos.model.Aluno;

public class FormularioAlunoActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR_EDITA_ALUNO = "Novo Aluno";
    public static final String TITULO_APPBAR_NOVO_ALUNO = "Editar Aluno";
    private EditText etNome;
    private EditText etTelefone;
    private EditText etEmail;
    private final AlunoDAO alunoDAO = new AlunoDAO();
    private Aluno alunoCadastrado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        inicializacaoDosCampos();
        configuraBotaoSalvar();
        carregaAluno();
    }

    private void carregaAluno() {
        Intent dados = getIntent();
        if (dados.hasExtra(CHAVE_ALUNO)) {
            setTitle(TITULO_APPBAR_EDITA_ALUNO);
            alunoCadastrado = (Aluno) dados.getSerializableExtra(CHAVE_ALUNO);
            preencheCampos();
        } else {
            setTitle(TITULO_APPBAR_NOVO_ALUNO);
            alunoCadastrado = new Aluno();
        }
    }

    private void preencheCampos() {
        etNome.setText(alunoCadastrado.getNome());
        etTelefone.setText(alunoCadastrado.getTelefone());
        etEmail.setText(alunoCadastrado.getEmail());
    }

    private void configuraBotaoSalvar() {
        Button btnSalvar = findViewById(R.id.btn_salvar);
        btnSalvar.setOnClickListener(view -> {
            finalizaFormulario();
        });
    }

    private void finalizaFormulario() {
        preencheAluno();
        if (alunoCadastrado.temIdValido()) {
            alunoDAO.editarAluno(alunoCadastrado);
        } else {
            alunoDAO.salvarAluno(alunoCadastrado);
        }

        finish();
    }

    private void inicializacaoDosCampos() {
        etNome = findViewById(R.id.cadastro_activity_etNome);
        etTelefone = findViewById(R.id.cadastro_activity_etTelefone);
        etEmail = findViewById(R.id.cadastro_activity_etEmail);
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