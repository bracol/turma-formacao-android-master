package br.com.cast.turmaformacao.taskmanager;

import android.app.Application;

import br.com.cast.turmaformacao.taskmanager.util.ApplicationUtil;

public class TaskManagerApplication extends Application {
    //application manipula ciclo de vida da aplica��o

    public void onCreate(){
        super.onCreate();
        ApplicationUtil.setContext(getApplicationContext());
    }

}
