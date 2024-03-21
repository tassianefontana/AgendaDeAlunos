package br.com.tassicompany.agendadealunos.asynctask;

import android.os.AsyncTask;

import java.util.List;

import br.com.tassicompany.agendadealunos.database.dao.TelefoneDAO;
import br.com.tassicompany.agendadealunos.model.Telefone;

public class BuscaPrimeiroTelefoneAlunoTask extends AsyncTask<Void, Void, Telefone> {

    private final TelefoneDAO telefoneDAO;
    private final int alunoId;
    private final PrimeiroTelefoneEncontradoListener listener;

    public BuscaPrimeiroTelefoneAlunoTask(TelefoneDAO telefoneDAO, int alunoId,
                                          PrimeiroTelefoneEncontradoListener listener) {
        this.telefoneDAO = telefoneDAO;
        this.alunoId = alunoId;
        this.listener = listener;
    }

    @Override
    protected Telefone doInBackground(Void... voids) {
        List<Telefone> telefones = telefoneDAO.buscaTelefonesAluno(alunoId);
        return !telefones.get(0).getNumero().isEmpty() ? telefones.get(0) : telefones.get(1);
    }

    @Override
    protected void onPostExecute(Telefone primeiroTelefone) {
        super.onPostExecute(primeiroTelefone);
        listener.quandoEncontrado(primeiroTelefone);
    }

    public interface PrimeiroTelefoneEncontradoListener {
        void quandoEncontrado(Telefone telefoneEncontrado);
    }
}
