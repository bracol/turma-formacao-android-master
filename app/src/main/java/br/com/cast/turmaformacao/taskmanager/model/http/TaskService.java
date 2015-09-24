package br.com.cast.turmaformacao.taskmanager.model.http;

import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import br.com.cast.turmaformacao.taskmanager.model.entidade.Task;

/**
 * Created by Administrador on 24/09/2015.
 */
public class TaskService {
    private static final String URL = "http://10.11.21.193:3000/api/v1/task";

    private TaskService() {
        super();
    }

    public static List<Task> getTasks() {
        List<Task> tasks = new ArrayList<>();

        try {
            URL url = new URL(URL);
            final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            int responseCode = conn.getResponseCode();

            Log.i("getTasks", "Codigo de retorno de requisição de cep: " + responseCode);
            if (responseCode == HttpURLConnection.HTTP_OK) {
                //throw new RuntimeException("Error code " + responseCode);
                //InputStream manipulador de arquivo e contem o json
                //tem que adicionar 3 dependencias do jackson tbm
                InputStream inputStream = conn.getInputStream();

                //lendo json
                //ObjectMapper é que da acesso a biblioteca do jackson
                ObjectMapper mapper = new ObjectMapper();
                CollectionType collectionType = mapper.getTypeFactory().constructCollectionType(List.class, Task.class);
                tasks = mapper.readValue(inputStream, collectionType);
            }
            conn.disconnect();

        } catch (Exception e) {
            //faz aparecer mensagem no logcat do android
            Log.e(AddresService.class.getName(), e.getMessage());
        }
        return tasks;
    }
}
