package br.com.cast.turmaformacao.taskmanager.model.servicos;

import java.util.List;

import br.com.cast.turmaformacao.taskmanager.controllers.activities.TaskListActivity;
import br.com.cast.turmaformacao.taskmanager.model.entidade.Task;
import br.com.cast.turmaformacao.taskmanager.model.persistence.Label.LabelRepository;
import br.com.cast.turmaformacao.taskmanager.model.persistence.Login.LoginRepository;
import br.com.cast.turmaformacao.taskmanager.model.persistence.Task.TaskContract;
import br.com.cast.turmaformacao.taskmanager.model.persistence.Task.TaskRepository;

/**
 * Created by Administrador on 15/09/2015.
 */
public final class TaskBusinessServices {

    private TaskBusinessServices() {
        super();
    }

    public static List<Task> findAll() {
        List<Task> all = TaskRepository.getAll();
            for (Task task : all) {
                task.setLabel(LabelRepository.getById(task.getLabel().getId()));
                //task.setUsuario(LoginRepository.getById(task.getUsuario().getId()));
            }
        return all;
    }

    public static void save(Task task) {
        if(task.getId() == TaskRepository.getIdByWebId(task.getWeb_id()) && task.getWeb_id() != null)
            task.setId(TaskRepository.getIdByWebId(task.getWeb_id()));

        TaskRepository.save(task);
    }


    public static void delete(Task selectTask) {
        TaskRepository.delete(selectTask.getId());
    }
}
