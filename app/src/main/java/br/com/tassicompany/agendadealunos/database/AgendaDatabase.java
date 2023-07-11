package br.com.tassicompany.agendadealunos.database;

import static br.com.tassicompany.agendadealunos.database.AgendaMigrations.TODAS_MIGRATIONS;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import br.com.tassicompany.agendadealunos.database.converter.ConversorCalendar;
import br.com.tassicompany.agendadealunos.database.dao.AlunoDAO;
import br.com.tassicompany.agendadealunos.model.Aluno;

@Database(entities = {Aluno.class}, version = 4, exportSchema = false)
@TypeConverters( {ConversorCalendar.class})
public abstract class AgendaDatabase extends RoomDatabase {
    private static final String NOME_BANCO_DE_DADOS ="agenda.db";


    public static AgendaDatabase getInstance(Context context) {
        return Room.databaseBuilder(context, AgendaDatabase.class, NOME_BANCO_DE_DADOS)
                .allowMainThreadQueries()
                //          .fallbackToDestructiveMigration() //Limpa os dados da versão anterior do banco de dados.
                .addMigrations(TODAS_MIGRATIONS)
                .build();
    }

    public abstract AlunoDAO getRoomAlunoDAO();
}
