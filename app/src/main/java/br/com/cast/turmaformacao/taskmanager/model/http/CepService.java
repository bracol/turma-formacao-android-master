package br.com.cast.turmaformacao.taskmanager.model.http;


import android.util.Log;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import br.com.cast.turmaformacao.taskmanager.model.entidade.Address;

/**
 * Created by Administrador on 23/09/2015.
 */
public final class CepService {

    private static final String URL = "http://correiosapi.apphb.com/cep/";

    private CepService(){
        super();
    }

    public Address getAdress(String zipCode){
        Address address = null;

        try {
            java.net.URL url = new URL(URL + zipCode);
            final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            int responseCode = conn.getResponseCode();

            Log.i("getAddressByCep", "Codigo de retorno de requisição de cep: " + responseCode);
            if(responseCode != HttpURLConnection.HTTP_OK){
                //throw new RuntimeException("Error code " + responseCode);
                //InputStream manipulador de arquivo e contem o json
                //tem que adicionar 3 dependencias do jackson tbm
                InputStream inputStream = conn.getInputStream();
            }

        } catch (Exception e) {
            //faz aparecer mensagem no logcat do android
            Log.e(CepService.class.getName(), e.getMessage());
        }


        return address;
    }
}
