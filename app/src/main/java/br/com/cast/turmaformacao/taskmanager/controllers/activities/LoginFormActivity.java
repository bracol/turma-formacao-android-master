package br.com.cast.turmaformacao.taskmanager.controllers.activities;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import br.com.cast.turmaformacao.taskmanager.R;
import br.com.cast.turmaformacao.taskmanager.model.entidade.Login;
import br.com.cast.turmaformacao.taskmanager.model.persistence.Login.LoginRepository;
import br.com.cast.turmaformacao.taskmanager.model.servicos.LoginBusinessServices;

import static br.com.cast.turmaformacao.taskmanager.R.layout.activity_login_form;

/**
 * Created by Wanilton on 22/09/2015.
 */
public class LoginFormActivity extends AppCompatActivity {

    private EditText editTextLogin;
    private EditText editTextPassword;
    private Login login;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_form);

        initLogin();
        bindEditTextLogin();
        bindEditTextPassword();


    }

    private void initLogin() {
        this.login = this.login == null ? new Login() : this.login;
    }

    private void bindEditTextLogin() {
        editTextLogin = (EditText) findViewById(R.id.editTextFormLogin);
    }

    private void bindEditTextPassword() {
        editTextPassword = (EditText) findViewById(R.id.editTextFormPassword);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_login_form, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case (R.id.menu_login_OK):
                onMenuOK();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void onMenuOK() {
        binLogin();
        LoginBusinessServices.save(login);
        /*
        List<Login> logins = LoginBusinessServices.findAll();
        String teste = "";
        for(Login l : logins){
            teste += "\n " + l.toString();
        }
        Toast.makeText(LoginFormActivity.this, teste, Toast.LENGTH_SHORT).show();
        */

        finish();
    }



    private void binLogin() {
        login.setLogin(editTextLogin.getText().toString());
        login.setPassword(editTextPassword.getText().toString());
    }
}
