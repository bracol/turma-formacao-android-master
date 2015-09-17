package br.com.cast.turmaformacao.taskmanager.model.servicos;

import java.util.List;

import br.com.cast.turmaformacao.taskmanager.model.entidade.Label;
import br.com.cast.turmaformacao.taskmanager.model.persistence.LabelRepository;


/**
 * Created by Administrador on 17/09/2015.
 */
public class LabelBusinessServices {

    private LabelBusinessServices() {
        super();
    }

    public static List<Label> findAll() {
        return LabelRepository.getAll();

    }

    public static void save(Label label) {
        LabelRepository.save(label);
    }


    public static void delete(Label selectLabel) {
        LabelRepository.delete(selectLabel.getId());
    }


}
