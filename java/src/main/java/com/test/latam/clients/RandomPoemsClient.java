package com.test.latam.clients;

import com.test.latam.constants.Constants;
import com.test.latam.domain.entities.Poema;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.Random;
import org.apache.commons.lang3.StringUtils;


@Component
public class RandomPoemsClient {

    public String obtenerPoema(final Environment env){

        WebClient poemasAzar = WebClient.create(env.getProperty(Constants.API_POEMAS));
        Poema[] respuesta = poemasAzar.get().exchange().retry(10).block().bodyToMono(Poema[].class).block();
        int azar = respuesta.length > 0 ? new Random().nextInt(respuesta.length) : -1;
        String poema = azar > -1 ? respuesta[azar].getContent() : StringUtils.EMPTY;

        return poema;
    }


}
