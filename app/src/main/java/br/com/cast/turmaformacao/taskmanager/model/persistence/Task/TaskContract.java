package br.com.cast.turmaformacao.taskmanager.model.persistence.Task;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import br.com.cast.turmaformacao.taskmanager.model.entidade.Label;
import br.com.cast.turmaformacao.taskmanager.model.entidade.Login;
import br.com.cast.turmaformacao.taskmanager.model.entidade.Task;

/**
 * Created by Administrador on 16/09/2015.
 */
public final class TaskContract {

    public static final String TABLE = "task";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String DESCRIPTION = "description";
    public static final String LABEL_ID = "label_id";
    public static final String LOGIN_ID = "usuario_id";

    public static final String[] COLUMNS = {ID, NAME, DESCRIPTION, LABEL_ID};

    private TaskContract() {
        super();
    }

    public static String getCreateTableScript() {
        final StringBuilder create = new StringBuilder();

        create.append(" CREATE TABLE " + TABLE);
        create.append("( ");
        create.append(ID + " INTEGER PRIMARY KEY, ");
        create.append(NAME + " TEXT NOT NULL, ");
        create.append(DESCRIPTION + " TEXT, ");
        create.append(LABEL_ID + " INTEGER NOT NULL, ");
        //create.append(LOGIN_ID + " INTEGER ");
        create.append(" ); ");

        return create.toString();
    }

    public static ContentValues getContentValues(Task task) {
        ContentValues values = new ContentValues();
        values.put(TaskContract.ID, task.getId());
        values.put(TaskContract.NAME, task.getName());
        values.put(TaskContract.DESCRIPTION, task.getDescription());
        values.put(TaskContract.LABEL_ID, task.getLabel().getId());
        //values.put(TaskContract.LOGIN_ID, task.getUsuario().getId());

        return values;
    }

    public static Task getTask(Cursor cursor) {
        if (!cursor.isBeforeFirst() || cursor.moveToNext()) {
            Task task = new Task();
            /* get column index pega o indice de acordo com o nome da coluna passado */
            task.setId(cursor.getLong(cursor.getColumnIndex(TaskContract.ID)));
            task.setName(cursor.getString(cursor.getColumnIndex(TaskContract.NAME)));
            task.setDescription(cursor.getString(cursor.getColumnIndex(TaskContract.DESCRIPTION)));

            Label label = new Label();
            label.setId(cursor.getLong(cursor.getColumnIndex(TaskContract.LABEL_ID)));
            task.setLabel(label);
            /*
            Login usuario = new Login();
            usuario.setId(cursor.getLong(cursor.getColumnIndex((TaskContract.LOGIN_ID))));
            task.setUsuario(usuario);*/
            return task;
        }
        return null;
    }


    public static List<Task> getTasks(Cursor cursor) {
        ArrayList<Task> tasks = new ArrayList<>();
        while (cursor.moveToNext()) {
            /* get colum index pega o indice de acordo com o nome da coluna passado */
            tasks.add(getTask(cursor));

        }
        return tasks;
    }


}
