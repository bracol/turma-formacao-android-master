package br.com.cast.turmaformacao.taskmanager.model.persistence.Login;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import br.com.cast.turmaformacao.taskmanager.model.entidade.Login;
import br.com.cast.turmaformacao.taskmanager.model.persistence.DatabaseHelper;

/**
 * Created by Administrador on 21/09/2015.
 */
public class LoginRepository {
    private LoginRepository() {
        super();
    }

    public static void save(Login login) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        ContentValues values = LoginContract.getContentValues(login);
        if (login.getId() == null) {
            db.insert(LoginContract.TABLE, null, values);
        } else {
            String where = LoginContract.ID + " = ? ";
            String[] params = {login.getId().toString()};
            db.update(LoginContract.TABLE, values, where, params);
        }

        db.close();
        databaseHelper.close();
    }

    public static void delete(long id) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        String where = LoginContract.ID + " = ? ";
        String[] params = {String.valueOf(id)};
        db.delete(LoginContract.TABLE, where, params);

        db.close();
        databaseHelper.close();
    }

    public static List<Login> getAll() {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        /* ResultSet do Android */
        Cursor cursor = db.query(LoginContract.TABLE, LoginContract.COLUMNS, null, null, null, null, LoginContract.ID);

        List<Login> values = LoginContract.getLogins(cursor);

        db.close();
        databaseHelper.close();

        return values;
    }

    public static Login getByLoginPassword(String $login, String $password) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        String where = LoginContract.LOGIN + " = ? AND " + LoginContract.PASSWORD + " = ? ";
        String[] params = {$login, $password};

        Cursor cursor = db.query(LoginContract.TABLE, LoginContract.COLUMNS, where, params, null, null, null, null);
        Login login = LoginContract.getLogin(cursor);

        db.close();
        databaseHelper.close();

        return login;
    }
}
