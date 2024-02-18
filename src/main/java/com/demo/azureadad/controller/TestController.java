//package com.demo.azureadad.controller;
//
//import com.demo.azureadad.dto.AccessTokenResponse;
//import com.demo.azureadad.dto.LoginRequest;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.io.IOException;
//import java.net.URI;
//import java.net.http.HttpClient;
//import java.net.http.HttpRequest;
//import java.net.http.HttpResponse;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/api")
//public class TestController {
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    private String tenantId = "9cbb4e71-dd48-43a1-991f-379fe7fe47fb";
//
//    private String clientId = "e98de15e-5346-4051-8a86-37f0acc6ae7d";
//
//    private String clientSecret = "30u8Q~M0BOm8ZAc~G_YZS8x4Mlfz-27V_Z6YqaaO";
//
//    String tokenEndpoint = "https://login.microsoftonline.com/"+tenantId+"/oauth2/v2.0/token";
//
//
//    @PostMapping("/login")
//    public String login(@RequestBody LoginRequest loginRequest) throws IOException, InterruptedException {
//
//
//        String tokenRequestBody = "grant_type=client_credentials" +
//                "&client_id=" + clientId +
//                "&scope=https://graph.microsoft.com/.default" +
//                "&client_secret=" + clientSecret;
//
//        HttpRequest tokenRequest = HttpRequest.newBuilder()
//                .uri(URI.create(tokenEndpoint))
//                .header("Content-Type", "application/x-www-form-urlencoded")
//                .POST(HttpRequest.BodyPublishers.ofString(tokenRequestBody))
//                .build();
//
//
//        HttpResponse<String> tokenResponse = java.net.http.HttpClient.newHttpClient().send(tokenRequest, HttpResponse.BodyHandlers.ofString());
//
//        AccessTokenResponse accessTokenResponse = getObjectFromString(tokenResponse.body(), AccessTokenResponse.class);
//
//
//        String userEndpoint = "https://graph.microsoft.com/v1.0/me";
//
//        HttpRequest userRequest = HttpRequest.newBuilder()
//                .uri(URI.create(userEndpoint))
//                .header("Authorization", "Bearer " + accessTokenResponse.getAccessToken())
//                .GET()
//                .build();
//
//        HttpResponse<String> userResponse = HttpClient.newHttpClient().send(userRequest, HttpResponse.BodyHandlers.ofString());
//
//        String userJson = userResponse.body();
//
//        return userJson;
//
//    }
//
//
//    public <T> T  getObjectFromString(String jsonValue, Class<T> className) {
//        try {
//            return objectMapper.readValue(jsonValue, className);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//
//}
