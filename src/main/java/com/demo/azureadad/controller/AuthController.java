//package com.demo.azureadad.controller;
//
//import com.demo.azureadad.dto.LoginRequest;
//import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.security.oauth2.core.user.OAuth2User;
//import org.springframework.web.bind.annotation.*;
//import java.util.Map;
//
//
//import java.util.Collections;
//import java.util.Map;
//
//@RestController
//public class AuthController {
//
//    @GetMapping("/health-check")
//    public String checkingHealth(){
//        return "healthy";
//    }
//
//    @GetMapping("/user")
//    public Map<String, Object> user(@AuthenticationPrincipal OAuth2User principal) {
//        return Collections.singletonMap("name", principal.getAttribute("name"));
//    }
////    @GetMapping("/user")
////    public Map<String, Object> user(@AuthenticationPrincipal OAuth2User principal) {
////        return principal.getAttributes();
////    }
//
//}
