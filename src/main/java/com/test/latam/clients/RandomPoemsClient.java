package com.test.latam.clients;

import com.test.latam.domain.entities.Poema;
import lombok.extern.java.Log;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Log
@Component
public class RandomPoemsClient {

    public String obtenerPoema(){
        String host = "https://www.poemist.com";
        String api = "/api/v1";
        String path = "/randompoems";

        WebClient poemasAzar = WebClient.create(host + api + path);
        Poema[] respuesta = poemasAzar.get().exchange().block().bodyToMono(Poema[].class).block();
        int azar = respuesta.length > 0 ? new Random().nextInt(respuesta.length) : 0;
        String poema = azar > 0 ? respuesta[azar].getContent() : "";
        return poema;
    }


}
