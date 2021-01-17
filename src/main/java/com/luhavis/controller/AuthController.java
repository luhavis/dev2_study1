package com.luhavis.controller;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.LowLevelHttpRequest;
import com.google.api.client.json.JsonFactory;

import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.testing.http.MockHttpTransport;
import com.luhavis.properties.AuthProperties;
import com.luhavis.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class AuthController {

    private final AuthService authService;
    private final AuthProperties authProperties;

    @PostMapping("/auth/callback")
    public String callbackPost(ModelMap modelMap) {

        return "success";
    }

    @GetMapping("/auth/callback")
    public String callbackGet(@RequestParam Map<String, String> params) {
        try {

            Map<String, String> res = authService.getNaverAccessToken(params.get("state"), params.get("code"));

            Map<String, String> res2 = authService.getNaverUserInfo(res.get("access_token"));


            return "success";
        } catch (Exception e) {
            System.out.println(e);
            return "failed";
        }
    }


    @PostMapping("/auth/googlesign")
    public String googleSign(@RequestBody Map<String, String> params) throws GeneralSecurityException, IOException {
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new MockHttpTransport(), new JacksonFactory())
                .setAudience(Collections.singleton(authProperties.getGoogle().get("clientId")))
                .build();

        GoogleIdToken idToken = verifier.verify(params.get("id_token"));
        if (idToken != null) {
          GoogleIdToken.Payload payload = idToken.getPayload();

          // Print user identifier
          String userId = payload.getSubject();
          System.out.println("User ID: " + userId);

          // Get profile information from payload
          String email = payload.getEmail();
          boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
          String name = (String) payload.get("name");
          String pictureUrl = (String) payload.get("picture");
          String locale = (String) payload.get("locale");
          String familyName = (String) payload.get("family_name");
          String givenName = (String) payload.get("given_name");

          // Use or store profile information
          // ...

        } else {
          System.out.println("Invalid ID token.");
        }



        return "success";
    }

}
