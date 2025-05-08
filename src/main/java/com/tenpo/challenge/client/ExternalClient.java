package com.tenpo.challenge.client;

import com.tenpo.challenge.clases.ExternalResponse;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class ExternalClient {
    private final RestClient restClient;

    public ExternalClient(RestClient.Builder webClientBuilder) {
        this.restClient = webClientBuilder.baseUrl("http://34.66.60.164:8080/").build();
    }

    @Cacheable("numberC")
    public ExternalResponse callNumber() {
        try {
            return this.restClient.get().uri("random").retrieve().body(ExternalResponse.class);
        } catch (Exception e) {
            return new ExternalResponse(0);
        }
    }
}
