package com.stanbic.service.multipay.config.webclients;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.netty.transport.ProxyProvider;

import java.time.Duration;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;


@Configuration
@Slf4j
public class JsonWebClient {


    @Value("${http.proxy.host}")
    private String proxyHost;

    @Value("${http.proxy.port}")
    private String proxyPort;


    public HashMap<String, Object> sendJSONRequest(HttpHeaders requestHeader, Object requestBody, String url, boolean useProxy) {
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
                .bodyValue(requestBody)
                .headers(h -> h.addAll(requestHeader));
        headersSpec.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

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
