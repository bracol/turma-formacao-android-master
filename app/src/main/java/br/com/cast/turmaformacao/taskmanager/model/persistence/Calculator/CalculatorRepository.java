package br.com.cast.turmaformacao.taskmanager.model.persistence.Calculator;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import br.com.cast.turmaformacao.taskmanager.model.entidade.Calculator;
import br.com.cast.turmaformacao.taskmanager.model.persistence.DatabaseHelper;
import br.com.cast.turmaformacao.taskmanager.model.persistence.Task.TaskContract;

/**
 * Created by Wanilton on 19/09/2015.
 */
public class CalculatorRepository {

    private CalculatorRepository(){
        super();
    }

    public static void save(Calculator calc){
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getWritableDatabase();


        ContentValues contentValues = CalculatorContract.getContentValues(calc);
        if(calc.getId() == null){
            db.insert(CalculatorContract.TABLE, null, contentValues);
        } else {
            String where = calc.getId() + " = ? ";
            String[] params = {String.valueOf(calc.getId())};
            db.update(CalculatorContract.TABLE, contentValues, where, params);
        }

        db.close();
        databaseHelper.close();
    }

    public static void delete(long id){
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        String where = id + " = ? ";
        String [] params = {String.valueOf(id)};

        db.delete(CalculatorContract.TABLE, where, params);
        db.close();
        databaseHelper.close();
    }

    public static List<Calculator> getAll(){
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();


        Cursor cursor = db.query(CalculatorContract.TABLE, CalculatorContract.COLUMNS, null, null, null, null, CalculatorContract.ID);
        List<Calculator> values = CalculatorContract.getCalculators(cursor);

        db.close();
        databaseHelper.close();
        return values;

    }




}
