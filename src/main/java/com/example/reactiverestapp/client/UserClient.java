package com.example.reactiverestapp.client;

import com.example.reactiverestapp.entity.User;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class UserClient {
    private WebClient client = WebClient.create("http://localhost:8080");
    public Mono<User> getUser(String userId){
        return client.get()
                .uri("/users/{userId}", userId)
                .retrieve()
                .bodyToMono(User.class).log(" User fetched ");
    }

    public Flux<User> getAllUsers(){
        return client.get()
                .uri("/users")
                .exchangeToFlux(clientResponse -> clientResponse.bodyToFlux(User.class)).log("Users Fetched : ");
    }

    public Mono<User> createUser(User user){
        Mono<User> userMono = Mono.just(user);
        return client.post().uri("/users").contentType(MediaType.APPLICATION_JSON)
                .body(userMono,User.class).retrieve().bodyToMono(User.class).log("Created User : ");

    }


}