package br.com.tassicompany.agendadealunos.view.activity;

import static br.com.tassicompany.agendadealunos.view.activity.ConstantesActivities.CHAVE_ALUNO;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import br.com.tassicompany.agendadealunos.R;
import br.com.tassicompany.agendadealunos.asynctask.BuscaTodosTelefonesDoAlunoTask;
import br.com.tassicompany.agendadealunos.asynctask.EditaAlunoTask;
import br.com.tassicompany.agendadealunos.asynctask.SalvaAlunoTask;
import br.com.tassicompany.agendadealunos.database.AgendaDatabase;
import br.com.tassicompany.agendadealunos.database.dao.AlunoDAO;
import br.com.tassicompany.agendadealunos.database.dao.TelefoneDAO;
import br.com.tassicompany.agendadealunos.model.Aluno;
import br.com.tassicompany.agendadealunos.model.Telefone;
import br.com.tassicompany.agendadealunos.model.TipoTelefone;

public class FormularioAlunoActivity extends AppCompatActivity {

    private static final String TITULO_APPBAR_EDITA_ALUNO = "Editar Aluno";
    private static final String TITULO_APPBAR_NOVO_ALUNO = "Novo Aluno";
    private EditText etNome;
    private EditText etEmail;
    private EditText etTelefoneFixo;
    private EditText etTelefoneCelular;
    private AlunoDAO alunoDAO;
    private Aluno alunoCadastrado;
    private TelefoneDAO telefoneDAO;
    private List<Telefone> telefonesAluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        AgendaDatabase database = AgendaDatabase.getInstance(this);
        alunoDAO = database.getAlunoDAO();
        telefoneDAO = database.getTelefoneDAO();
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
        etEmail.setText(alunoCadastrado.getEmail());
        preencheCamposTelefone();
    }

    private void preencheCamposTelefone() {
        new BuscaTodosTelefonesDoAlunoTask(telefoneDAO, alunoCadastrado, telefones -> {
            this.telefonesAluno = telefones;

            for (Telefone telefone : telefonesAluno) {
                if (telefone.getTipo() == TipoTelefone.FIXO) {
                    etTelefoneFixo.setText(telefone.getNumero());
                } else {
                    etTelefoneCelular.setText(telefone.getNumero());
                }
            }
        }).execute();
    }

    private void finalizaFormulario() {
        preencheAluno();

        Telefone telefoneFixo = criaTelefone(etTelefoneFixo, TipoTelefone.FIXO);
        Telefone telefoneCelular = criaTelefone(etTelefoneCelular, TipoTelefone.CELULAR);
        if (alunoCadastrado.getNome().isEmpty()) {
            Toast.makeText(this.getApplicationContext(), "Informe o nome do aluno", Toast.LENGTH_LONG).show();
        } else if ((telefoneFixo.getNumero().isEmpty()) && (telefoneCelular.getNumero().isEmpty())) {
            Toast.makeText(this.getApplicationContext(), "Informe pelo menos um telefone", Toast.LENGTH_LONG).show();
        } else {
            if (alunoCadastrado.temIdValido()) {
                editaAluno(telefoneFixo, telefoneCelular);
            } else {
                salvaAluno(telefoneFixo, telefoneCelular);
            }
        }
    }

    private Telefone criaTelefone(EditText etTelefone, TipoTelefone tipo) {
        String telefoneFixo = etTelefone.getText().toString();
        return new Telefone(telefoneFixo, tipo);
    }

    private void salvaAluno(Telefone telefoneFixo, Telefone telefoneCelular) {
        new SalvaAlunoTask(alunoDAO, alunoCadastrado, telefoneFixo, telefoneCelular, telefoneDAO, this::finish).execute();

    }

    private void editaAluno(Telefone telefoneFixo, Telefone telefoneCelular) {
        new EditaAlunoTask(alunoDAO, alunoCadastrado, telefoneFixo, telefoneCelular,
                telefoneDAO, telefonesAluno, this::finish).execute();
    }

    private void inicializacaoDosCampos() {
        etNome = findViewById(R.id.cadastro_activity_etNome);
        etEmail = findViewById(R.id.cadastro_activity_etEmail);
        etTelefoneFixo = findViewById(R.id.cadastro_activity_etTelefoneFixo);
        etTelefoneCelular = findViewById(R.id.cadastro_activity_etTelefoneCelular);
    }

    private void preencheAluno() {
        String nome = etNome.getText().toString();
        String email = etEmail.getText().toString();

        alunoCadastrado.setNome(nome);
        alunoCadastrado.setEmail(email);
    }
}