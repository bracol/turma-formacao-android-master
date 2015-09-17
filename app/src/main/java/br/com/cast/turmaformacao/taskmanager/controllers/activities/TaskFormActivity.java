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

import java.io.Serializable;

import br.com.cast.turmaformacao.taskmanager.R;
import br.com.cast.turmaformacao.taskmanager.model.entidade.Task;
import br.com.cast.turmaformacao.taskmanager.model.servicos.TaskBusinessServices;
import br.com.cast.turmaformacao.taskmanager.util.FormHelper;

/**
 * Created by Administrador on 15/09/2015.
 */
public class TaskFormActivity extends AppCompatActivity {

    public static final String PARAM_TASK = "TASK";
    private EditText editTextName;
    private EditText editTextDescription;
    private Button buttonSave;
    private Task task;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_form);

        initTask();

        bindEditTextName();
        bindEditTextDescription();
        bindEditButtonSalvar();
    }

    public void initTask() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.task = (Task) getIntent().getExtras().getParcelable(PARAM_TASK);
        }
        this.task = this.task == null ? new Task() : this.task;
    }


    private void bindEditButtonSalvar() {
        buttonSave = (Button) findViewById(R.id.button_salvar);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String requiredMessage = TaskFormActivity.this.getString(R.string.msg_required);
                if (!FormHelper.validateRequired(requiredMessage, editTextName)) {
                    binTask();
                    //contexto de aplicao, não estão manipulando interface e sim arquivo
                    TaskBusinessServices.save(task);
                    Toast.makeText(TaskFormActivity.this, R.string.msg_save_sucess, Toast.LENGTH_LONG).show();
                    TaskFormActivity.this.finish();
                }
            }

            private void binTask() {
                task.setName(editTextName.getText().toString());
                task.setDescription(editTextDescription.getText().toString());
            }
        });
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
