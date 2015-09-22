package br.com.cast.turmaformacao.taskmanager.controllers.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import br.com.cast.turmaformacao.taskmanager.R;
import br.com.cast.turmaformacao.taskmanager.controllers.adapters.LabelListAdapter;
import br.com.cast.turmaformacao.taskmanager.model.entidade.Label;
import br.com.cast.turmaformacao.taskmanager.model.servicos.LabelBusinessServices;


/**
 * Created by Administrador on 17/09/2015.
 */
public class LabelListActivity extends AppCompatActivity {

    private ListView listViewLabelList;
    private Label selectLabel;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_label_list);

        bindLabelList();
    }

    private void bindLabelList() {
        listViewLabelList = (ListView) findViewById(R.id.listViewLabelList);
    }

    @Override
    protected void onResume() {
        updateLabelList();
        super.onResume();
    }

    private void updateLabelList() {
        List<Label> values = LabelBusinessServices.findAll();
        listViewLabelList.setAdapter(new LabelListAdapter(this, values));

        LabelListAdapter adapter = (LabelListAdapter) listViewLabelList.getAdapter();
        adapter.notifyDataSetInvalidated();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_label_form, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_add:
                onMenuAddClick();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    private void onMenuAddClick() {
        Intent goToLabelFormActivity = new Intent(LabelListActivity.this, LabelFormActivity.class);
        startActivity(goToLabelFormActivity);
    }


}
