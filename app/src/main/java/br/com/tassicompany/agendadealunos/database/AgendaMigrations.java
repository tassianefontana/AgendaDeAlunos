package br.com.tassicompany.agendadealunos.database;

import androidx.annotation.NonNull;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

class AgendaMigrations {

    private static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE aluno ADD COLUMN sobrenome TEXT");
        }
    };
    private static final Migration MIGRATION_2_3 = new Migration(2, 3) {
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
    };
    private static final Migration MIGRATION_3_4 = new Migration(3, 4) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE aluno ADD COLUMN momentoCadastro INTEGER");
        }
    };
    private static final Migration MIGRATION_4_5 = new Migration(4, 5) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
        database.execSQL("CREATE TABLE IF NOT EXISTS `AlunoNovo` (" +
                "`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                "`nome` TEXT, " +
                "`telefoneFixo` TEXT, " +
                "`telefoneCelular` TEXT, " +
                "`email` TEXT, " +
                "`momentoCadastro` INTEGER)");

            database.execSQL("INSERT INTO AlunoNovo (id, nome, telefoneFixo, email, momentoCadastro)" +
                    " SELECT id, nome, telefone, email, momentoCadastro FROM Aluno");

            database.execSQL("DROP TABLE Aluno");

            database.execSQL("ALTER TABLE AlunoNovo RENAME TO Aluno");
        }
    };
    static final Migration[] TODAS_MIGRATIONS = {MIGRATION_1_2,MIGRATION_2_3, MIGRATION_3_4, MIGRATION_4_5};
}
