package org.exaple;
import org.exaple.api.Reader;
import com.netflix.discovery.EurekaClient;
import lombok.Data;
import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.UUID;

@Service
public class ReaderProvider {

    private final WebClient webClient;

    public ReaderProvider(EurekaClient eurekaClient, ReactorLoadBalancerExchangeFilterFunction loadBalancerExchangeFilterFunction) {
        webClient = WebClient.builder()
                .filter(loadBalancerExchangeFilterFunction)
                .build();
    }

    public Reader getRandomReader() {
        Reader randomReader = webClient.get()
                .uri("http://reader-service/api/reader/random")
                .retrieve()
                .bodyToMono(Reader.class)
                .block();

        assert randomReader != null;
        return randomReader;
    }


//    @Data
//    private static class Reader {
//        private UUID id;
//
//        public UUID getId() {
//            return id;
//        }
//}


}