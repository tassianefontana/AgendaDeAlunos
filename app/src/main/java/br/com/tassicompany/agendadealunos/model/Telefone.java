package br.com.tassicompany.agendadealunos.model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = Aluno.class, parentColumns = "id", childColumns = "alunoId", onUpdate = ForeignKey.CASCADE, onDelete = ForeignKey.CASCADE))
public class Telefone {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String numero;
    private TipoTelefone tipo;
    private int alunoId;

    public Telefone(String telefone, TipoTelefone tipo) {
        this.numero = telefone;
        this.tipo = tipo;
    }


    public Telefone() {
    }

    public int getAlunoId() {
        return alunoId;
    }

    public void setAlunoId(int alunoId) {
        this.alunoId = alunoId;
    }
    //    @ColumnInfo(name = "aluno_id") //Forma como atributo será gravado no banco de dados

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public TipoTelefone getTipo() {
        return tipo;
    }

    public void setTipo(TipoTelefone tipo) {
        this.tipo = tipo;
    }
}
