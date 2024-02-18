//package com.demo.azureadad.controller;
//
//import com.demo.azureadad.dto.LoginRequest;
//import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.security.oauth2.core.user.OAuth2User;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import java.net.URI;
//import java.net.http.HttpClient;
//import java.net.http.HttpRequest;
//import java.net.http.HttpResponse;
//import java.net.http.HttpHeaders;
//import java.net.http.HttpRequest.BodyPublishers;
//import java.net.http.HttpResponse.BodyHandlers;
//import java.util.Map;
//
//
//import java.util.Collections;
//import java.util.Map;
//
//@RestController
//public class AuthController {
//
//
////    @Value("${azure.activedirectory.tenant-id}")
//    private String tenantId;
//
////    @Value("${azure.activedirectory.client-id}")
//    private String clientId;
//
////    @Value("${azure.activedirectory.client-secret}")
//    private String clientSecret;
//
//    @GetMapping("/health-check")
//    public String checkingHealth(){
//        return "healthy";
//    }
//
////    @GetMapping("/user")
////    public Map<String, Object> user(@AuthenticationPrincipal OAuth2User principal) {
////        return Collections.singletonMap("name", principal.getAttribute("name"));
////    }
//    @GetMapping("/user")
//    public Map<String, Object> user(@AuthenticationPrincipal OAuth2User principal) {
//        return principal.getAttributes();
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
//        // Authenticate user against Azure AD
//        String accessToken = authenticateUser(loginRequest.getUsername(), loginRequest.getPassword());
//
//        if (accessToken != null) {
//            // User authenticated successfully, print user object
//            System.out.println("User authenticated successfully. Access Token: " + accessToken);
//            return ResponseEntity.ok("User authenticated successfully.");
//        } else {
//            // Authentication failed
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed.");
//        }
//    }
//
//
//    private String authenticateUser(String username, String password) {
//        // Azure AD token endpoint URL
//        String tokenEndpoint = "https://login.microsoftonline.com/" + tenantId + "/oauth2/v2.0/token";
//
//        // Create an HttpClient instance
//        HttpClient httpClient = HttpClient.newBuilder()
//                .version(HttpClient.Version.HTTP_1_1)
//                .build();
//
//        // Build the request body with grant_type=password, client_id, scope, username, password, and client_secret
//        String requestBody = "grant_type=password" +
//                "&client_id=" + clientId +
//                "&scope=openid" +
//                "&username=" + username +
//                "&password=" + password +
//                "&client_secret=" + clientSecret;
//
//        // Create the HTTP request
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create(tokenEndpoint))
//                .header("Content-Type", "application/x-www-form-urlencoded")
//                .POST(BodyPublishers.ofString(requestBody))
//                .build();
//
//        try {
//            // Send the HTTP request and retrieve the response
//            HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());
//
//            // Check if the response is successful
//            if (response.statusCode() == 200) {
//                // Extract the access token from the response body
//                String responseBody = response.body();
//                // Parse the response body into a map
//                Map<String, String> responseMap = parseResponse(responseBody);
//                if (responseMap.containsKey("access_token")) {
//                    return responseMap.get("access_token");
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        // Return null if authentication fails
//        return null;
//    }
//
//    // Helper method to parse the response body into a map
//    private Map<String, String> parseResponse(String responseBody) {
//        // Implement logic to parse the response body into a map
//        // For example, you can split the response body by '&' and '=' to extract key-value pairs
//        // You may need to handle URL decoding and other edge cases
//        // This method should be customized based on the format of the response body
//        return null;
//    }
//
//
//}
