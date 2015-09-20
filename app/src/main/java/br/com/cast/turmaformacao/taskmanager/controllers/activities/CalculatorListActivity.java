package br.com.cast.turmaformacao.taskmanager.controllers.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import br.com.cast.turmaformacao.taskmanager.R;
import br.com.cast.turmaformacao.taskmanager.controllers.adapters.CalculatorListAdapter;
import br.com.cast.turmaformacao.taskmanager.model.entidade.Calculator;
import br.com.cast.turmaformacao.taskmanager.model.servicos.CalculatorBusinessServices;

/**
 * Created by Wanilton on 19/09/2015.
 */
public class CalculatorListActivity extends AppCompatActivity {
    private ListView listViewCalculatorList;
    private Calculator selectedCalculator;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator_list);

        bindCalculatorList();
    }

    private void bindCalculatorList() {
        listViewCalculatorList = (ListView) findViewById(R.id.listViewCalculatorList);
    }

    @Override
    protected void onResume() {
        updateCalculatorList();
        super.onResume();
    }

    private void updateCalculatorList() {
        List<Calculator> values = CalculatorBusinessServices.findAll();
        listViewCalculatorList.setAdapter(new CalculatorListAdapter(values, this));

        CalculatorListAdapter adapter = (CalculatorListAdapter) listViewCalculatorList.getAdapter();
        adapter.notifyDataSetInvalidated();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_calculator_list, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_calc_add:
                onMenuAddClick();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void onMenuAddClick() {
        Intent gotoCalculatorForm = new Intent(CalculatorListActivity.this, CalculatorFormActivity.class);
        startActivity(gotoCalculatorForm);
    }
}
