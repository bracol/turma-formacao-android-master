package br.com.cast.turmaformacao.taskmanager.controllers.adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.cast.turmaformacao.taskmanager.R;
import br.com.cast.turmaformacao.taskmanager.model.entidade.Task;

/**
 * Created by Administrador on 15/09/2015.
 */
public class TaskListAdapter extends BaseAdapter {

    private List<Task> taskList;
    private Activity context;

    public TaskListAdapter(Activity context, List<Task> taskList){
        this.context = context;
        this.taskList = taskList;
    }


    @Override
    public int getCount() {
        return taskList.size();
    }

    @Override
    public Task getItem(int position) {
        return taskList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Task task = getItem(position);
        View taskListItem = context.getLayoutInflater().inflate(R.layout.list_item_task, parent, false);

        TextView textViewId = (TextView) taskListItem.findViewById(R.id.textViewId);
        textViewId.setText(task.getId().toString());

        TextView textViewName = (TextView) taskListItem.findViewById(R.id.textViewName);
        textViewName.setText((task.getName().toString()));

        return taskListItem;
    }
}
