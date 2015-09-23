package br.com.cast.turmaformacao.taskmanager.controllers.activities;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import br.com.cast.turmaformacao.taskmanager.R;
import br.com.cast.turmaformacao.taskmanager.controllers.adapters.LabelListAdapter;
import br.com.cast.turmaformacao.taskmanager.model.entidade.Label;
import br.com.cast.turmaformacao.taskmanager.model.entidade.Task;
import br.com.cast.turmaformacao.taskmanager.model.servicos.LabelBusinessServices;
import br.com.cast.turmaformacao.taskmanager.model.servicos.TaskBusinessServices;
import br.com.cast.turmaformacao.taskmanager.util.FormHelper;

/**
 * Created by Administrador on 15/09/2015.
 */
public class TaskFormActivity extends AppCompatActivity {

    public static final String PARAM_TASK = "TASK";
    private EditText editTextName;
    private Button btnLabel;
    private EditText editTextDescription;
    private Spinner spinnerLabel;
    private FrameLayout frameLayout;
    private Task task;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_form);

        initTask();

        bindEditTextName();
        bindEditTextDescription();
        bindBtnLabel();
        bindSpinnerLabel();
        bindFrameLayout();
    }

    private void bindFrameLayout() {
        frameLayout = (FrameLayout) findViewById(R.id.frameLayoutFormTask);
    }

    private void bindBtnLabel() {
        btnLabel = (Button) findViewById(R.id.btn_go_label);
        btnLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToLabelForm = new Intent(TaskFormActivity.this, LabelFormActivity.class);
                startActivity(goToLabelForm);
            }
        });
    }

    @Override
    protected void onResume() {
        updateLabelList();
        super.onResume();
    }

    private void bindSpinnerLabel() {
        List<Label> labels = LabelBusinessServices.findAll();
        spinnerLabel = (Spinner) findViewById(R.id.spinnerLabel);
        spinnerLabel.setAdapter(new LabelListAdapter(TaskFormActivity.this, labels));
        spinnerLabel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                frameLayout.setBackgroundColor(android.graphics.Color.parseColor(((Label) spinnerLabel.getSelectedItem()).getColor().getHex()));
                int cor = android.graphics.Color.parseColor("#FFFFFF");
                spinnerLabel.setBackgroundColor(cor);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void updateLabelList() {
        List<Label> labels = LabelBusinessServices.findAll();
        LabelListAdapter adapter = (LabelListAdapter) spinnerLabel.getAdapter();
        adapter.setItens(labels);
        adapter.notifyDataSetChanged();
        if(task.getLabel() != null) {
            int posicaoLabel = labels.indexOf(task.getLabel());
            spinnerLabel.setSelection(posicaoLabel);
        }
        //int ae = 1;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        //menuinflater serve pra pegar o layout do meno correspondente e adicionar todos os itens
        getMenuInflater().inflate(R.menu.menu_task_form, menu);
        //criar o menu
        return super.onCreateOptionsMenu(menu);
    }


    public void initTask() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.task = getIntent().getExtras().getParcelable(PARAM_TASK);
        }
        this.task = this.task == null ? new Task() : this.task;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_task_OK:
                onMenuOk();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void onMenuOk() {
        String requiredMessage = TaskFormActivity.this.getString(R.string.msg_required);
        if (!FormHelper.validateRequired(requiredMessage, editTextName)) {
            binTask();
            //Toast.makeText(TaskFormActivity.this, task.getLabel().getId().toString(), Toast.LENGTH_LONG).show();
            //contexto de aplicao, não estão manipulando interface e sim arquivo
            TaskBusinessServices.save(task);
            //Toast.makeText(TaskFormActivity.this, R.string.msg_save_sucess, Toast.LENGTH_LONG).show();

            TaskFormActivity.this.finish();
        }
    }

    private void binTask() {
        task.setName(editTextName.getText().toString());
        task.setDescription(editTextDescription.getText().toString());
        task.setLabel((Label) spinnerLabel.getSelectedItem());
        Toast.makeText(TaskFormActivity.this, task.toString(), Toast.LENGTH_LONG).show();
    }

    private void bindEditTextDescription() {
        editTextDescription = (EditText) findViewById(R.id.editTextDescription);
        editTextDescription.setText(task.getDescription() == null ? "" : task.getDescription());
    }

    private void bindEditTextName() {
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextName.setText(task.getName() == null ? "" : task.getName());
    }

}
