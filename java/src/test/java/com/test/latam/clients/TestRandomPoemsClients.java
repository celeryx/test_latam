package com.test.latam.clients;

import com.test.latam.constants.Constants;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.Silent.class)
@ContextConfiguration(classes = {RandomPoemsClient.class})
@SpringBootTest(classes = RandomPoemsClient.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestRandomPoemsClients {

    @InjectMocks
    RandomPoemsClient rpc;
    @Mock
    private WebClient webClientMock = WebClient.create();
    @Mock
    Environment env;
    @Mock
    WebClient.RequestHeadersUriSpec requestHeadersUriSpec;
    @Mock
    Mono mono;



    @Test
    public void test(){
        when(env.getProperty(Constants.API_POEMAS)).thenReturn("https://www.poemist.com/api/v1/randompoems");
        when(webClientMock.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.exchange()).thenReturn(mono);
        Assert.assertNotNull(rpc.obtenerPoema(env));
    }
}
