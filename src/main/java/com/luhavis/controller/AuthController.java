package com.luhavis.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.luhavis.controller.dto.UserSaveRequestDto;
import com.luhavis.service.UserService;
import com.luhavis.util.AuthUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Collections;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class AuthController {

    @PostMapping("/auth/callback")
    public String callbackPost(ModelMap modelMap) {

        return "success";
    }

    @GetMapping("/auth/callback")
    public String callbackGet(@RequestParam Map<String, String> params) {
        try {

            Map<String, String> res = AuthUtil.getNaverAccessToken(params.get("state"), params.get("code"));

            Map<String, String> res2 = AuthUtil.getNaverUserInfo(res.get("access_token"));


            return "success";
        } catch (Exception e) {
            System.out.println(e);
            return "failed";
        }
    }


    @PostMapping("/auth/googlesign")
    public String googleSign(@RequestBody Map<String, Integer> responseBody) {
//        JsonFactory jsonFactory = null;
//        HttpTransport transport = null;
//        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory);


        return "success";
    }

}
