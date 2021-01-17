package com.luhavis.controller;

import com.luhavis.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@RestController
public class AuthController {

    private final AuthService authService;

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
    public String googleSign(@RequestBody Map<String, Integer> responseBody) {
//        JsonFactory jsonFactory = null;
//        HttpTransport transport = null;
//        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory);


        return "success";
    }

}
