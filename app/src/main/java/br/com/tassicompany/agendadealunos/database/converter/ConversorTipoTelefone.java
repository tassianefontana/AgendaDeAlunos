package br.com.tassicompany.agendadealunos.database.converter;

import androidx.room.TypeConverter;

import br.com.tassicompany.agendadealunos.model.TipoTelefone;

public class ConversorTipoTelefone {
    @TypeConverter
    public String telefoneToString(TipoTelefone tipo){
        return tipo.name();
    }

    @TypeConverter
    public TipoTelefone toTipoTelefone(String valor){
        if(valor != null){
            return TipoTelefone.valueOf(valor);
        }
        return TipoTelefone.FIXO;
    }




}
