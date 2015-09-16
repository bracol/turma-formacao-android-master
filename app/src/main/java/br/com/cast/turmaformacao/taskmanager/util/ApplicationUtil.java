package br.com.cast.turmaformacao.taskmanager.util;

import android.content.Context;

public class ApplicationUtil {

    private static Context APPLICATION_CONTEXT;

    private ApplicationUtil() {
        super();
    }

    public static void setContext(Context context){
        APPLICATION_CONTEXT = context;
    }

    public static Context getContext(){
        return ApplicationUtil.APPLICATION_CONTEXT;
    }

}
