package br.com.tassicompany.agendadealunos.database.converter;

import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import java.util.Calendar;

public class ConversorCalendar {

    //Transforma no tipo compatível com o banco de dados.
    @TypeConverter
    public Long toLong(Calendar valor){
        if (valor != null){
            return valor.getTimeInMillis();
        }
        return null;
    }

    //Transforma no tipo não primitivo Calendar
    @TypeConverter
    public Calendar toCalendar (Long valor){
        Calendar momentoAtual = Calendar.getInstance();
        if(valor != null){
            momentoAtual.setTimeInMillis(valor);
        }
        return momentoAtual;
    }

}
