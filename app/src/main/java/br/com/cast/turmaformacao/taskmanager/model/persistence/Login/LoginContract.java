package br.com.cast.turmaformacao.taskmanager.model.persistence.Login;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import br.com.cast.turmaformacao.taskmanager.model.entidade.Label;
import br.com.cast.turmaformacao.taskmanager.model.entidade.Login;
import br.com.cast.turmaformacao.taskmanager.model.entidade.Task;

/**
 * Created by Administrador on 21/09/2015.
 */
public class LoginContract {

    public static final String TABLE = "signin";
    public static final String ID = "id";
    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";

    public static final String[] COLUMNS = {ID, LOGIN, PASSWORD};

    private LoginContract() {
        super();
    }

    public static String getCreateTableScript() {
        final StringBuilder create = new StringBuilder();

        create.append(" CREATE TABLE " + TABLE);
        create.append("( ");
        create.append(ID + " INTEGER PRIMARY KEY, ");
        create.append(LOGIN + " TEXT NOT NULL, ");
        create.append(PASSWORD + " TEXT ");
        create.append(" ); ");

        return create.toString();
    }

    public static ContentValues getContentValues(Login login) {
        ContentValues values = new ContentValues();
        values.put(LoginContract.ID, login.getId());
        values.put(LoginContract.LOGIN, login.getLogin());
        values.put(LoginContract.PASSWORD, login.getPassword());

        return values;
    }

    public static Login getTask(Cursor cursor) {
        if (!cursor.isBeforeFirst() || cursor.moveToNext()) {
            Login login = new Login();
            /* get column index pega o indice de acordo com o nome da coluna passado */
            login.setId(cursor.getLong(cursor.getColumnIndex(LoginContract.ID)));
            login.setLogin(cursor.getString(cursor.getColumnIndex(LoginContract.LOGIN)));
            login.setPassword(cursor.getString(cursor.getColumnIndex(LoginContract.PASSWORD)));

            return login;
        }
        return null;
    }


    public static List<Login> getTasks(Cursor cursor) {
        ArrayList<Login> logins = new ArrayList<>();
        while (cursor.moveToNext()) {
            /* get colum index pega o indice de acordo com o nome da coluna passado */
            logins.add(getTask(cursor));

        }
        return logins;
    }
}
