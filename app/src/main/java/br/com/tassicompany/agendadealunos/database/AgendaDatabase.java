package br.com.tassicompany.agendadealunos.database;

import static br.com.tassicompany.agendadealunos.database.AgendaMigrations.TODAS_MIGRATIONS;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import br.com.tassicompany.agendadealunos.database.converter.ConversorCalendar;
import br.com.tassicompany.agendadealunos.database.converter.ConversorTipoTelefone;
import br.com.tassicompany.agendadealunos.database.dao.AlunoDAO;
import br.com.tassicompany.agendadealunos.database.dao.TelefoneDAO;
import br.com.tassicompany.agendadealunos.model.Aluno;
import br.com.tassicompany.agendadealunos.model.Telefone;

@Database(entities = {Aluno.class, Telefone.class}, version = 6, exportSchema = false)
@TypeConverters( {ConversorCalendar.class, ConversorTipoTelefone.class})
public abstract class
AgendaDatabase extends RoomDatabase {
    private static final String NOME_BANCO_DE_DADOS ="agenda.db";
    public abstract AlunoDAO getAlunoDAO();
    public abstract TelefoneDAO getTelefoneDAO();

    public static AgendaDatabase getInstance(Context context) {
        return Room.databaseBuilder(context, AgendaDatabase.class, NOME_BANCO_DE_DADOS)
                .allowMainThreadQueries()
                //          .fallbackToDestructiveMigration() //Limpa os dados da versão anterior do banco de dados.
                .addMigrations(TODAS_MIGRATIONS)
                .build();
    }

}
