package br.com.tassicompany.agendadealunos.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

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
                .addMigrations(new Migration(1, 2) {
                    @Override
                    public void migrate(@NonNull SupportSQLiteDatabase database) {
                        database.execSQL("ALTER TABLE aluno ADD COLUMN sobrenome TEXT");
                    }
                }, new Migration(2, 3) {
                    @Override
                    public void migrate(@NonNull SupportSQLiteDatabase database) {
                        //Para desfazer uma alteração anterior, não é possível utilizar o comando DROP, dessa forma, deve-se seguir os passos:
                        //1 - Criar uma nova tabela
                        database.execSQL("CREATE TABLE IF NOT EXISTS `AlunoNovo`" +
                                "(`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                                "`nome` TEXT," +
                                "`telefone` TEXT, " +
                                "`email` TEXT)");
                        //2 - Copiar dados da tabela antiga para a nova
                        database.execSQL("INSERT INTO AlunoNovo (id, nome, telefone, email)" +
                                " SELECT id, nome, telefone, email FROM Aluno");
                        //3 - Remover a tabela antiga
                        database.execSQL("DROP TABLE Aluno");
                        //4 - Renomear a tabela nova com o nome da tabela antiga
                        database.execSQL("ALTER TABLE AlunoNovo RENAME TO Aluno");
                    }
                }, new Migration(3,4) {
                    @Override
                    public void migrate(@NonNull SupportSQLiteDatabase database) {
                        database.execSQL("ALTER TABLE aluno ADD COLUMN momentoCadastro INTEGER");
                    }
                })
                .build();
    }

    public abstract AlunoDAO getRoomAlunoDAO();
}
