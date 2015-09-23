package br.com.cast.turmaformacao.taskmanager.model.http;


import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import br.com.cast.turmaformacao.taskmanager.model.entidade.Address;

/**
 * Created by Administrador on 23/09/2015.
 */
public final class AddresService {

    private static final String URL = "http://correiosapi.apphb.com/cep/";

    private AddresService(){
        super();
    }

    public static Address getAdressByZipCode(String zipCode){
        Address address = null;

        try {
            URL url = new URL(URL + zipCode);
            final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            int responseCode = conn.getResponseCode();

            Log.i("getAdressByZipCode", "Codigo de retorno de requisição de cep: " + responseCode);
            if(responseCode != HttpURLConnection.HTTP_OK){
                //throw new RuntimeException("Error code " + responseCode);
                //InputStream manipulador de arquivo e contem o json
                //tem que adicionar 3 dependencias do jackson tbm
                InputStream inputStream = conn.getInputStream();

                //lendo json
                //ObjectMapper é que da acesso a biblioteca do jackson
                ObjectMapper objectMapper = new ObjectMapper();
                address = objectMapper.readValue(inputStream, Address.class);

                conn.disconnect();
            }

        } catch (Exception e) {
            //faz aparecer mensagem no logcat do android
            Log.e(AddresService.class.getName(), e.getMessage());
        }


        return address;
    }
}
