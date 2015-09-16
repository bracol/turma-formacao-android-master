package br.com.cast.turmaformacao.taskmanager.controllers.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.cast.turmaformacao.taskmanager.R;

/**
 * Created by Administrador on 14/09/2015.
 */
public class ImcActivity extends AppCompatActivity {

    private EditText editTextAltura;
    private EditText editTextPeso;
    private Button buttonCalcularImc;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imc);

        bindEditTextAltura();
        bindEditTextPeso();
        bindButtonCalcular();

    }

    private void bindEditTextPeso() {
        editTextPeso = (EditText) findViewById(R.id.editTextPeso);
    }

    private void bindEditTextAltura() {
        editTextAltura = (EditText) findViewById(R.id.editTextAltura);
    }

    private void bindButtonCalcular(){
        buttonCalcularImc = (Button) findViewById(R.id.buttonCalcularImc);
        buttonCalcularImc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double altura = Double.parseDouble(editTextAltura.getText().toString());
                double peso = Double.parseDouble(editTextPeso.getText().toString());;
                double imc = (peso / (Math.pow(altura, 2)));
                String message = getResources().getString(R.string.msg_imc, imc);
                Toast.makeText(ImcActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
