package com.test.latam.clients;

import com.test.latam.domain.entities.Poema;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.Random;

@Component
public class RandomPoemsClient {

    public String obtenerPoema(){
        String host = "https://www.poemist.com";
        String api = "/api/v1";
        String path = "/randompoems";

        WebClient poemasAzar = WebClient.create(host + api + path);
        Poema[] respuesta = poemasAzar.get().exchange().retry(10).block().bodyToMono(Poema[].class).block();
        int azar = respuesta.length > 0 ? new Random().nextInt(respuesta.length) : 0;
        String poema = azar > 0 ? respuesta[azar].getContent() : "";
        return poema;
    }


}
