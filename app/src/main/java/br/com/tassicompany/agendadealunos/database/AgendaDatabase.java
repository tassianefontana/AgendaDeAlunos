package br.com.tassicompany.agendadealunos.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import br.com.tassicompany.agendadealunos.database.dao.AlunoDAO;
import br.com.tassicompany.agendadealunos.model.Aluno;

@Database(entities = {Aluno.class}, version = 2, exportSchema = false)
public abstract class AgendaDatabase extends RoomDatabase {
    private static final String NOME_BANCO_DE_DADOS ="agenda.db";

    public static AgendaDatabase getInstance(Context context) {
        return Room.databaseBuilder(context, AgendaDatabase.class, NOME_BANCO_DE_DADOS)
                .allowMainThreadQueries()
      //          .fallbackToDestructiveMigration() //Limpa os dados da versão anterior do banco de dados.
                .addMigrations(new Migration(1, 2) {
                    @Override
                    public void migrate(@NonNull SupportSQLiteDatabase database) {
                        database.execSQL("ALTER TABLE aluno ADD COLUMN sobrenome TEXT");
                    }
                })
                .build();
    }

    public abstract AlunoDAO getRoomAlunoDAO();
}
