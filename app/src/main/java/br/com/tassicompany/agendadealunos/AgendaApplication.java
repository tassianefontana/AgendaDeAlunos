package br.com.tassicompany.agendadealunos;

import android.app.Application;

import br.com.tassicompany.agendadealunos.dao.AlunoDAO;
import br.com.tassicompany.agendadealunos.model.Aluno;

public class AgendaApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        criaAlunosTeste();
    }

    private void criaAlunosTeste() {
        AlunoDAO alunoDAO = new AlunoDAO();

        alunoDAO.salvarAluno(new Aluno("Tassiane", "41 99999999",
                "tassiane.fontana@gmail.com"));
        alunoDAO.salvarAluno(new Aluno("Carol", "41 99999999",
                "exemploemail@gmail.com"));
    }
}
