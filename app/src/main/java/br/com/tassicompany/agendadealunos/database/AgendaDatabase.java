package br.com.tassicompany.agendadealunos.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import br.com.tassicompany.agendadealunos.database.dao.AlunoDAO;
import br.com.tassicompany.agendadealunos.model.Aluno;

@Database(entities = {Aluno.class}, version = 1, exportSchema = false)
public abstract class AgendaDatabase extends RoomDatabase {
    private static final String NOME_BANCO_DE_DADOS ="agenda.db";

    public static AgendaDatabase getInstance(Context context) {
        return Room.databaseBuilder(context, AgendaDatabase.class, NOME_BANCO_DE_DADOS)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration() //Limpa os dados da versão anterior do banco de dados.
                .build();
    }

    public abstract AlunoDAO getRoomAlunoDAO();
}
