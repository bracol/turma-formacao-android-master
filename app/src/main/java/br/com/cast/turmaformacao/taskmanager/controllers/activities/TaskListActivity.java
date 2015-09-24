package br.com.cast.turmaformacao.taskmanager.controllers.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.cast.turmaformacao.taskmanager.R;
import br.com.cast.turmaformacao.taskmanager.controllers.adapters.TaskListAdapter;
import br.com.cast.turmaformacao.taskmanager.model.entidade.Task;
import br.com.cast.turmaformacao.taskmanager.model.http.TaskService;
import br.com.cast.turmaformacao.taskmanager.model.servicos.TaskBusinessServices;

/**
 * Created by Administrador on 15/09/2015.
 */
public class TaskListActivity extends AppCompatActivity {

    private ListView listViewTaskList;
    private Task selectTask;
    private List<Task> values;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);

        bindTaskList();
    }

    private void bindTaskList() {
        values = TaskBusinessServices.findAll();
        listViewTaskList = (ListView) findViewById(R.id.listViewTaskList);
        registerForContextMenu(listViewTaskList);
        listViewTaskList.setAdapter(new TaskListAdapter(this, values));
        listViewTaskList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                TaskListAdapter adapter = (TaskListAdapter) listViewTaskList.getAdapter();
                selectTask = adapter.getItem(position);
                //return - indica se eu quero consumir ou não o evento
                return false;
            }
        });
    }


    @Override
    protected void onResume() {
        updateTaskList();
        super.onResume();
    }

    private void updateTaskList() {
        values = TaskBusinessServices.findAll();
        //if(values.size() > 0) {
            TaskListAdapter adapter = (TaskListAdapter) listViewTaskList.getAdapter();
            adapter.setItens(values);
            adapter.notifyDataSetChanged();
        //}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_task_list, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_add:
                onMenuAddClick();
                break;
            case R.id.menuTask_atualizar:
                onMenuAttClick();
                break;


        }
        return super.onOptionsItemSelected(item);
    }

    private void onMenuAttClick() {
        new GetTask().execute();
    }

    private void onMenuAddClick() {
        Intent goToTaskFormActivity = new Intent(TaskListActivity.this, TaskFormActivity.class);
        startActivity(goToTaskFormActivity);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_excluir:
                onMenuDeleteClick();
                break;
            case R.id.menu_editar:
                onMenuEditClick();
                break;
        }
        return super.onContextItemSelected(item);
    }

    private void onMenuEditClick() {
        Intent goToTaskForm = new Intent(TaskListActivity.this, TaskFormActivity.class);
        goToTaskForm.putExtra(TaskFormActivity.PARAM_TASK, selectTask);
        startActivity(goToTaskForm);
    }

    private void onMenuDeleteClick() {
        new AlertDialog.Builder(TaskListActivity.this)
                .setTitle(R.string.lbl_confirm)
                .setMessage(R.string.msg_confirm_delete)
                .setPositiveButton(R.string.lbl_yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        TaskBusinessServices.delete(selectTask);
                        selectTask = null;
                        String message = getString(R.string.msg_delete_succesful);
                        updateTaskList();
                        Toast.makeText(TaskListActivity.this, message, Toast.LENGTH_LONG).show();
                    }
                })
                .setNeutralButton(R.string.lbl_no, null)
                .create()
                .show();
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_context_task_list, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }


    private class GetTask extends AsyncTask<Void, Void, List<Task>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected List<Task> doInBackground(Void... params) {
            return TaskService.getTasks();
        }

        @Override
        protected void onPostExecute(List<Task> tasks) {
            super.onPostExecute(tasks);
            for(Task t : tasks) {
                TaskBusinessServices.save(t);
            }
            //listViewTaskList.setAdapter(new TaskListAdapter(TaskListActivity.this, values));
            //TaskListAdapter adapter = (TaskListAdapter) listViewTaskList.getAdapter();
            //adapter.notifyDataSetChanged();
        }
    }
}
