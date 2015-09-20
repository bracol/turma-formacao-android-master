package br.com.cast.turmaformacao.taskmanager.model.persistence.Calculator;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import br.com.cast.turmaformacao.taskmanager.model.entidade.Calculator;

/**
 * Created by Wanilton on 19/09/2015.
 */
public class CalculatorContract {
    public static final String TABLE = "calculator";
    public static final String ID = "calculator_id";
    public static final String NUMA = "numa";
    public static final String NUMB = "numb";
    public static final String TOTAL = "total";

    public static final String[] COLUMNS = {ID, NUMA, NUMB, TOTAL};

    private CalculatorContract(){
        super();
    }

    public static String getCreateTableScript(){
        StringBuilder create = new StringBuilder();
        create.append(" CREATE TABLE " + TABLE);
        create.append(" ( ");
        create.append(ID + " INTEGER PRIMARY KEY, ");
        create.append(NUMA + " REAL, ");
        create.append(NUMB + " REAL, ");
        create.append(TOTAL + " REAL ");
        create.append(" ); ");

        return create.toString();
    }

    public static ContentValues getContentValues(Calculator calc){
        ContentValues contentValues = new ContentValues();
        contentValues.put(CalculatorContract.ID, calc.getId());
        contentValues.put(CalculatorContract.NUMA, calc.getNumA());
        contentValues.put(CalculatorContract.NUMB, calc.getNumB());
        contentValues.put(CalculatorContract.TOTAL, calc.getTotal());
        return contentValues;
    }

    public static Calculator getCalculator(Cursor cursor){
        if(!cursor.isBeforeFirst() || cursor.moveToNext()){
            Calculator calc = new Calculator();
            calc.setId(cursor.getLong(cursor.getColumnIndex(CalculatorContract.ID)));
            calc.setNumA(cursor.getLong(cursor.getColumnIndex(CalculatorContract.NUMA)));
            calc.setNumB(cursor.getLong(cursor.getColumnIndex(CalculatorContract.NUMB)));
            calc.setTotal(cursor.getLong(cursor.getColumnIndex(CalculatorContract.TOTAL)));
            return calc;
        }
        return null;
    }

    public static List<Calculator> getCalculators(Cursor cursor) {
        List<Calculator> calculators = new ArrayList<>();
        while (cursor.moveToNext()) {
            calculators.add(getCalculator(cursor));
        }
        return calculators;
    }




}
