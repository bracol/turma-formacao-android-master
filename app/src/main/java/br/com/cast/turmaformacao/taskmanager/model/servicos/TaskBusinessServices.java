package br.com.cast.turmaformacao.taskmanager.model.servicos;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import br.com.cast.turmaformacao.taskmanager.model.entidade.Task;
import br.com.cast.turmaformacao.taskmanager.model.persistence.TaskRepository;

/**
 * Created by Administrador on 15/09/2015.
 */
public final class TaskBusinessServices {

    private TaskBusinessServices() {
        super();
    }

    public static List<Task> findAll() {
        return TaskRepository.getAll();

    }

    public static void save(Task task) {
        TaskRepository.save(task);
    }


    public static void delete(Task selectTask) {
        TaskRepository.delete(selectTask.getId());
    }
}
