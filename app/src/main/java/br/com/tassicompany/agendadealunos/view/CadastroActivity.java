package br.com.tassicompany.agendadealunos.view;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import br.com.tassicompany.agendadealunos.R;
import br.com.tassicompany.agendadealunos.dao.AlunoDAO;
import br.com.tassicompany.agendadealunos.model.Aluno;

public class CadastroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        setTitle("Novo Aluno");
        Button btnSalvar = findViewById(R.id.btn_salvar);
        final EditText etNome = findViewById(R.id.cadastro_activity_etNome);
        final EditText etTelefone = findViewById(R.id.cadastro_activity_etTelefone);
        final EditText etEmail = findViewById(R.id.cadastro_activity_etEmail);
        AlunoDAO alunoDAO = new AlunoDAO();

        btnSalvar.setOnClickListener(view -> {
            String nome = etNome.getText().toString();
            String telefone = etTelefone.getText().toString();
            String email = etEmail.getText().toString();

            Aluno aluno = new Aluno(nome, telefone, email);
            alunoDAO.salvarAluno(aluno);

            finish();
        });
    }
}