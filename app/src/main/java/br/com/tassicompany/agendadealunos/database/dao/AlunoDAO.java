package br.com.tassicompany.agendadealunos.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import br.com.tassicompany.agendadealunos.model.Aluno;

@Dao
public interface AlunoDAO {
    @Insert
    Long salvarAluno(Aluno aluno);

    @Query("SELECT * FROM aluno")
    List<Aluno> getAlunos();

    @Delete
    void remove(Aluno aluno);

    @Update
    void editarAluno(Aluno alunoCadastrado);
}
