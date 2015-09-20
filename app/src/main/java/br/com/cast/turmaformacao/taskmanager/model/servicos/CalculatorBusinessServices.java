package br.com.cast.turmaformacao.taskmanager.model.servicos;

import java.util.List;

import br.com.cast.turmaformacao.taskmanager.model.entidade.Calculator;
import br.com.cast.turmaformacao.taskmanager.model.persistence.Calculator.CalculatorRepository;

/**
 * Created by Wanilton on 19/09/2015.
 */
public class CalculatorBusinessServices {

    private CalculatorBusinessServices(){super();}

    public static List<Calculator> findAll(){
        return CalculatorRepository.getAll();
    }

    public static void save(Calculator calc){
        CalculatorRepository.save(calc);
    }

    public static void delete(Calculator selectedCalculator){
        CalculatorRepository.delete(selectedCalculator.getId());
    }

}
