package br.com.tassicompany.agendadealunos;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<String> alunos = new ArrayList<>(Arrays.asList("Tassi", "Marcia", "Carol"));
        TextView aluno1 = findViewById(R.id.tvAluno1);
        TextView aluno2 = findViewById(R.id.tvAluno2);
        TextView aluno3 = findViewById(R.id.tvAluno3);

        aluno1.setText(alunos.get(0));
        aluno2.setText(alunos.get(1));
        aluno3.setText(alunos.get(2));



    }
}
