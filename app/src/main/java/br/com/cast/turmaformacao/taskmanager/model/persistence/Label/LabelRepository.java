package br.com.cast.turmaformacao.taskmanager.model.persistence.Label;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import br.com.cast.turmaformacao.taskmanager.model.entidade.Label;
import br.com.cast.turmaformacao.taskmanager.model.persistence.DatabaseHelper;

public class LabelRepository {

    private LabelRepository(){
        super();
    }

    public static void save(Label label){
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        ContentValues values = LabelContract.getContentValues(label);
        if(label.getId() == null) {
            db.insert(LabelContract.TABLE, null, values);
        } else {
            String where = LabelContract.ID + " = ? ";
            String [] params =  {label.getId().toString()};
            db.update(LabelContract.TABLE, values, where, params);
        }

        db.close();
        databaseHelper.close();
    }

    public static void delete(long id){
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        String where = LabelContract.ID + " = ? ";
        String [] params =  {String.valueOf(id)};
        db.delete(LabelContract.TABLE, where, params);

        db.close();
        databaseHelper.close();
    }

    public static List<Label> getAll(){
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        /* ResultSet do Android */
        Cursor cursor = db.query(LabelContract.TABLE, LabelContract.COLUMNS, null, null, null, null, LabelContract.ID);

        List<Label> values = LabelContract.getLabels(cursor);

        db.close();
        databaseHelper.close();

        return values;
    }


}
