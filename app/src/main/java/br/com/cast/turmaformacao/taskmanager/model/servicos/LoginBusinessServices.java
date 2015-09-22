package br.com.cast.turmaformacao.taskmanager.model.servicos;

import java.util.List;

import br.com.cast.turmaformacao.taskmanager.model.entidade.Login;
import br.com.cast.turmaformacao.taskmanager.model.persistence.Login.LoginRepository;

/**
 * Created by Wanilton on 22/09/2015.
 */
public class LoginBusinessServices {

    private LoginBusinessServices(){super();}

    public static List<Login> findAll(){
        return LoginRepository.getAll();
    }

    public static void save(Login login){
        LoginRepository.save(login);
    }

    public static void delete(Login selectedLogin){
        LoginRepository.delete(selectedLogin.getId());
    }
}
