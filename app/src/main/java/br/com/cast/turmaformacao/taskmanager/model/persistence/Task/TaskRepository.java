package br.com.cast.turmaformacao.taskmanager.model.persistence.Task;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import br.com.cast.turmaformacao.taskmanager.model.entidade.Label;
import br.com.cast.turmaformacao.taskmanager.model.entidade.Task;
import br.com.cast.turmaformacao.taskmanager.model.persistence.DatabaseHelper;
import br.com.cast.turmaformacao.taskmanager.model.persistence.Label.LabelContract;

public class TaskRepository {

    private TaskRepository() {
        super();
    }

    public static void save(Task task) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        ContentValues values = TaskContract.getContentValues(task);
        if (task.getId() == null) {
            db.insert(TaskContract.TABLE, null, values);
        } else {
            String where = TaskContract.ID + " = ? ";
            String[] params = {task.getId().toString()};
            db.update(TaskContract.TABLE, values, where, params);
        }

        db.close();
        databaseHelper.close();
    }

    public static void delete(long id) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        String where = TaskContract.ID + " = ? ";
        String[] params = {String.valueOf(id)};
        db.delete(TaskContract.TABLE, where, params);

        db.close();
        databaseHelper.close();
    }

    public static List<Task> getAll() {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        /* ResultSet do Android */
        Cursor cursor = db.query(TaskContract.TABLE, TaskContract.COLUMNS, null, null, null, null, TaskContract.ID);

        List<Task> values = TaskContract.getTasks(cursor);

        db.close();
        databaseHelper.close();

        return values;
    }

    public static Long getIdByWebId(Long webId){
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        String where = TaskContract.WEB_ID + " = ?";
        String[] params = {String.valueOf(webId)};
        String[] colums = {TaskContract.ID};
        Cursor cursor = db.query(TaskContract.TABLE, colums, where, params, null, null, null);
        Task task = TaskContract.getTask(cursor);

        if (task.getId() > 0)
            return task.getId();
        else
            return (long)0;
    }


}
