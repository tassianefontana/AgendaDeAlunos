package br.com.tassicompany.agendadealunos.view;

import static br.com.tassicompany.agendadealunos.view.ConstantesActivities.CHAVE_ALUNO;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import br.com.tassicompany.agendadealunos.R;
import br.com.tassicompany.agendadealunos.database.AgendaDatabase;
import br.com.tassicompany.agendadealunos.database.dao.AlunoDAO;
import br.com.tassicompany.agendadealunos.model.Aluno;

public class FormularioAlunoActivity extends AppCompatActivity {

    private static final String TITULO_APPBAR_EDITA_ALUNO = "Editar Aluno";
    private static final String TITULO_APPBAR_NOVO_ALUNO = "Novo Aluno";
    private EditText etNome;
    private EditText etTelefoneFixo;
    private EditText etTelefoneCelular;
    private EditText etEmail;
    private AlunoDAO alunoDAO;
    private Aluno alunoCadastrado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        AgendaDatabase database = AgendaDatabase.getInstance(this);
        alunoDAO = database.getRoomAlunoDAO();
        inicializacaoDosCampos();
        carregaAluno();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.activity_formulario_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.activity_formulario_menu_salvar) {
            finalizaFormulario();
        }
        return super.onOptionsItemSelected(item);
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
        etTelefoneCelular.setText(alunoCadastrado.getTelefoneCelular());
        etTelefoneFixo.setText(alunoCadastrado.getTelefoneFixo());
        etEmail.setText(alunoCadastrado.getEmail());
    }

    private void finalizaFormulario() {
        preencheAluno();
        if (alunoCadastrado.getNome().isEmpty() && alunoCadastrado.getTelefoneFixo().isEmpty() &&
                alunoCadastrado.getEmail().isEmpty() && alunoCadastrado.getTelefoneCelular().isEmpty()) {
            Toast.makeText(this, "Preencha os campos!", Toast.LENGTH_LONG).show();
        } else {
            if (alunoCadastrado.temIdValido()) {
                alunoDAO.editarAluno(alunoCadastrado);
            } else {
                alunoDAO.salvarAluno(alunoCadastrado);
            }
            finish();
        }
    }

    private void inicializacaoDosCampos() {
        etNome = findViewById(R.id.cadastro_activity_etNome);
        etTelefoneFixo = findViewById(R.id.cadastro_activity_etTelefoneFixo);
        etTelefoneCelular = findViewById(R.id.cadastro_activity_etTelefoneCelular);
        etEmail = findViewById(R.id.cadastro_activity_etEmail);
    }

    private void preencheAluno() {
        String nome = etNome.getText().toString();
        String telefoneFixo = etTelefoneFixo.getText().toString();
        String telefoneCelular = etTelefoneCelular.getText().toString();
        String email = etEmail.getText().toString();

        alunoCadastrado.setNome(nome);
        alunoCadastrado.setTelefoneFixo(telefoneFixo);
        alunoCadastrado.setTelefoneCelular(telefoneCelular);
        alunoCadastrado.setEmail(email);
    }
}