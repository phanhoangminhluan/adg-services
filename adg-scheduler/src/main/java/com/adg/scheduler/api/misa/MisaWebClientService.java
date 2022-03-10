package com.adg.scheduler.api.misa;

import com.adg.scheduler.common.enums.MisaEndpoints;
import com.merlin.asset.core.utils.MapUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.time.Duration;
import java.util.Map;
import java.util.function.Function;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2022.03.09 11:25
 */
@Component
public class MisaWebClientService {

    @Value("${misa.client-id}")
    private String clientId;

    @Value("${misa.client-secret}")
    private String clientSecret;

    @Value("${misa.base-url}")
    private String baseUrl;

    @Value("${misa.api-timeout}")
    private int apiTimeOut;

    private WebClient webClient;
    private WebClient authWebClient;

    public MisaWebClientService() {

    }

    @PostConstruct
    public void refreshBearerToken() {
        HttpClient httpClient = HttpClient
                .create()
                .responseTimeout(Duration.ofSeconds(apiTimeOut));

        if (this.authWebClient == null) {
            this.authWebClient = WebClient.builder()
                    .clientConnector(new ReactorClientHttpConnector(httpClient))
                    .baseUrl(baseUrl)
                    .build();
        }

        String bearerToken = retrieveBearerToken();

        this.webClient = WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .baseUrl(baseUrl)
                .defaultHeader("Authorization", bearerToken)
                .build();
    }

    private String retrieveBearerToken() {
        Mono<Map<String, Object>> result = this.authWebClient
                .post()
                .uri(MisaEndpoints.ACCOUNT.uri)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(MapUtils.ImmutableMap()
                        .put("client_id", clientId)
                        .put("client_secret", clientSecret)
                        .build()), Map.class)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<>() {});
        Map<String, Object> response = result.block();

        return MapUtils.getString(response, "data");
    }

    public Map<String, Object> get(Function<UriBuilder, URI> uriFunction) {
        Mono<Map<String, Object>> result = this.authWebClient
                .get()
                .uri(uriFunction)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<>() {});
        return result.block();
    }

    public Map<String, Object> post(Function<UriBuilder, URI> uriFunction, Map<String, Object> body) {
        Mono<Map<String, Object>> result = this.authWebClient
                .post()
                .uri(uriFunction)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(body), Map.class)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<>() {});
        return result.block();
    }

    public Map<String, Object> put(Function<UriBuilder, URI> uriFunction, Map<String, Object> body) {

        Mono<Map<String, Object>> result = this.authWebClient
                .put()
                .uri(uriFunction)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(body), Map.class)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<>() {});
        return result.block();
    }

}
