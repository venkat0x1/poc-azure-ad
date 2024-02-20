package com.demo.azureadad.controller;

import com.demo.azureadad.dto.AzureADLoginResponse;
import com.demo.azureadad.dto.LoginRequest;
import com.demo.azureadad.dto.LoginResponse;
import com.demo.azureadad.dto.UserDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@RequestMapping("/api/azure")
@RestController
public class AzureADController {

    @Value("${app.azure.AD.tenet-id}")
    private String tenetId;

    @Value("${app.azure.AD.client-id}")
    private String clientId;

    @Value("${app.azure.AD.client-secret}")
    private String clientSecret;

    @Value("${app.azure.AD.token-uri}")
    private String tokenUri;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        // Prepare request body
        String requestBody = "client_id=" + clientId +
                "&client_secret=" + clientSecret +
                "&scope=user.read openid profile offline_access" +
                "&username=" + loginRequest.getUsername() +
                "&password=" + loginRequest.getPassword() +
                "&grant_type=password";

        // Prepare request headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        // Prepare HTTP entity
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        // Send POST request using RestTemplate
        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    tokenUri,
                    HttpMethod.POST,
                    requestEntity,
                    String.class
            );

            AzureADLoginResponse azureADLoginResponse = convertStringToObject(response.getBody(), AzureADLoginResponse.class);
            String idToken = azureADLoginResponse.getIdToken();
            UserDTO userDTO =  getUsernameFromToken(idToken);

            return new LoginResponse("Login successful!", userDTO);

        } catch (RestClientException e) {
            e.printStackTrace();
            return null;

        }

    }


    private <T> T convertStringToObject(String jsonValue, Class<T> className) {

        try {
            return objectMapper.readValue(jsonValue, className);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    private UserDTO getUsernameFromToken(String idToken) {

        String[] tokenParts = idToken.split("\\.");

        String encodedPayload = tokenParts[1];

        Base64.Decoder decoder = Base64.getDecoder();

        byte[] decodedBytes = decoder.decode(encodedPayload);

        String decodedPayload = new String(decodedBytes);

        try {
            JsonNode jsonNode = objectMapper.readTree(decodedPayload);

            UserDTO userDTO= new UserDTO();

            userDTO.setUsername(jsonNode.get("upn").asText());
            userDTO.setFirstName(jsonNode.get("given_name").asText());
            userDTO.setLastName(jsonNode.get("family_name").asText());
            userDTO.setGroups(convertToList(jsonNode.get("groups")));

            return userDTO;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<String> convertToList(JsonNode node) {
        List<String> list = new ArrayList<>();
        if (node.isArray()) {
            for (JsonNode jsonNode : node) {
                list.add(jsonNode.asText());
            }
        }
        return list;
    }

}
