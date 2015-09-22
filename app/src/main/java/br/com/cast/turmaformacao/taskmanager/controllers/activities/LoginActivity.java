package br.com.cast.turmaformacao.taskmanager.controllers.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.cast.turmaformacao.taskmanager.R;
import br.com.cast.turmaformacao.taskmanager.model.entidade.Login;
import br.com.cast.turmaformacao.taskmanager.model.persistence.Login.LoginRepository;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextLogin;
    private EditText editTextPassword;
    private Button buttonLogin;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        bindEditTextLogin();
        bindEditTextPassword();
        bindButtonLogin();
    }

    private void bindButtonLogin() {
        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(verificaLogin()) {
                    Intent redirectToTaskList = new Intent(LoginActivity.this, TaskListActivity.class);
                    startActivity(redirectToTaskList);
                }
                else{
                    Toast.makeText(LoginActivity.this, "Login ou senha incorretos", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_login_activity, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case (R.id.menu_login_add):
                onMenuAdd();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void onMenuAdd() {
        Intent goToLoginForm = new Intent(LoginActivity.this, LoginFormActivity.class);
        startActivity(goToLoginForm);
    }

    private void bindEditTextPassword() {
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
    }

    private void bindEditTextLogin() {
        editTextLogin = (EditText) findViewById(R.id.editTextLogin);
        //editTextLogin.setText("Teste");
    }

    public boolean verificaLogin(){
        Login loginVerifica = null;
        if(editTextLogin.getText() != null && editTextLogin.getText().toString().trim() != "" && editTextPassword.getText() != null && editTextPassword.getText().toString().trim() != ""){
            loginVerifica = LoginRepository.getByLoginPassword(editTextLogin.getText().toString(), editTextPassword.getText().toString());
        }
        if(loginVerifica == null)
            return false;
        else
            return true;
    }

}
