package br.com.cast.turmaformacao.taskmanager.controllers.activities;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import br.com.cast.turmaformacao.taskmanager.R;
import br.com.cast.turmaformacao.taskmanager.model.entidade.Calculator;
import br.com.cast.turmaformacao.taskmanager.model.entidade.Task;
import br.com.cast.turmaformacao.taskmanager.model.servicos.CalculatorBusinessServices;

/**
 * Created by Wanilton on 19/09/2015.
 */
public class CalculatorFormActivity extends AppCompatActivity {
    public static final String PARAM_TASK = "CALCULATOR";
    private EditText edtiTextNumberA;
    private EditText editTextNumberB;
    private Calculator calc;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator_form);

        initCalc();
        bindEditTextNumberA();
        bindEditTextNumberB();
    }

    private void initCalc() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.calc = (Calculator) getIntent().getExtras().getSerializable(PARAM_TASK);
        }
        this.calc = (this.calc == null ? new Calculator() : this.calc);
    }


    private void bindEditTextNumberA() {
        edtiTextNumberA = (EditText) findViewById(R.id.editTextNumA);
//        edtiTextNumberA.setText(calc.getNumA() == 0 ? "" : String.valueOf(calc.getNumA()));

    }

    private void bindEditTextNumberB() {
        editTextNumberB = (EditText) findViewById(R.id.editTextNumB);
        //editTextNumberB.setText(calc.getNumB() == 0 ? "" : String.valueOf(calc.getNumB()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_calculator_form, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_calc_OK:
                onMenuOK();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void onMenuOK() {
        binCalc();
        CalculatorBusinessServices.save(calc);
        this.finish();

    }

    private void binCalc() {
        calc.setNumA(Long.parseLong(edtiTextNumberA.getText().toString()));
        calc.setNumB(Long.parseLong(editTextNumberB.getText().toString()));
        calc.setTotal(calc.getNumA() + calc.getNumB());
    }


}
