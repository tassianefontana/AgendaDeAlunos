package br.com.tassicompany.agendadealunos;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Toast.makeText(this, "Hello World!", Toast.LENGTH_SHORT).show();
    }
}
