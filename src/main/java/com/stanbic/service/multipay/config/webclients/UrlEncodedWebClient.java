package com.stanbic.service.multipay.config.webclients;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.netty.transport.ProxyProvider;

import java.time.Duration;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class UrlEncodedWebClient {


    @Value("${nibss.authorization.apikey}")
    private String nibssApikey;

    @Value("${http.proxy.host}")
    private String proxyHost;

    @Value("${http.proxy.port}")
    private String proxyPort;

    @Value("${nibbs.authorization.client-id}")
    private String clientId;
    @Value("${nibbs.authorization.client-secret}")
    private String clientSecret;

    @Value("${nibbs.authorization.grant-type}")
    private String grantType;

    public HashMap<String, Object> sendUrlEncodedRequest(String url, boolean useProxy) {
        log.info("URL encoded http call...");

        HttpClient httpClient;
        if (useProxy) {
            httpClient = HttpClient.create()
                    .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 15000)
                    .responseTimeout(Duration.ofSeconds(60))
                    .doOnConnected(conn ->
                            conn.addHandlerLast(new ReadTimeoutHandler(60, TimeUnit.SECONDS))
                                    .addHandlerLast(new WriteTimeoutHandler(60, TimeUnit.SECONDS)))
                    .proxy(proxy -> proxy.type(ProxyProvider.Proxy.HTTP)
                            .host(proxyHost)
                            .port(Integer.parseInt(proxyPort)));
        } else {
             httpClient = HttpClient.create()
                    .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 15000)
                    .responseTimeout(Duration.ofSeconds(60))
                    .doOnConnected(conn ->
                            conn.addHandlerLast(new ReadTimeoutHandler(60, TimeUnit.SECONDS))
                                    .addHandlerLast(new WriteTimeoutHandler(60, TimeUnit.SECONDS)));
        }

        WebClient client = WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();

        WebClient.RequestHeadersSpec<?> headersSpec = client.post()
                        .uri(url)
                .body(BodyInserters.fromFormData("client_id", clientId)
                .with("client_secret", clientSecret)
                .with("scope", clientId + "/.default")
                .with("grant_type", grantType));
        headersSpec.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE);
        headersSpec.header("apikey", nibssApikey);

        Mono<Object> messageResponse = headersSpec.exchangeToMono(response -> {
            if (response.statusCode().equals(HttpStatus.OK)) {
                return response.bodyToMono(Object.class);
            } else if (response.statusCode().is4xxClientError()) {
                return response.bodyToMono(Object.class);
            } else {
                return response.bodyToMono(Object.class);
            }
        });
        Object message = messageResponse.block();
        return (HashMap<String, Object>) message;
    }
}
