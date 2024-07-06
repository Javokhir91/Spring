package org.exaple;

import lombok.Data;
import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.exaple.api.Book;
import java.util.UUID;



@Service
public class BookProvider {
    // цель: вызовать GET http:localhost:8180/api/book/random, получить ID и вернуть

    private final WebClient webClient;

    public BookProvider(ReactorLoadBalancerExchangeFilterFunction loadBalancerExchangeFilterFunction) {
        webClient = WebClient.builder()
                .filter(loadBalancerExchangeFilterFunction)
                .build();
    }

    public Book getRandomBook() {
         Book randomBook = webClient.get()
                .uri( "http://book-service/api/book/random")
                .retrieve()
                .bodyToMono(Book.class)
                .block();

        assert randomBook != null;
        return randomBook;
    }


//    @Data
//    private static class Book {
//        private UUID id;
//
//        public UUID getId() {
//            return id;
//        }
//    }
}