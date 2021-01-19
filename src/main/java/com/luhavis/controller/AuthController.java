package com.luhavis.controller;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.auth.oauth2.GooglePublicKeysManager;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.LowLevelHttpRequest;
import com.google.api.client.json.JsonFactory;

import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.testing.http.MockHttpTransport;
import com.luhavis.domain.CustomUserDetails;
import com.luhavis.domain.User;
import com.luhavis.domain.UserRepository;
import com.luhavis.properties.AuthProperties;
import com.luhavis.service.AuthService;
import com.luhavis.service.CustomUserDetailsService;
import com.luhavis.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.jaas.SecurityContextLoginModule;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.AuthProvider;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class AuthController {

    private final AuthService authService;
    private final AuthProperties authProperties;

    private final UserService userService;
    private final CustomUserDetailsService customUserDetailsService;
    private final AuthenticationProvider authenticationProvider;

    @PostMapping("/auth/callback")
    public String callbackPost(ModelMap modelMap) {

        return "success";
    }

    @GetMapping("/auth/callback")
    public ModelAndView callbackGet(HttpServletRequest request, @RequestParam Map<String, String> params) {
        try {

            Map<String, String> res = authService.getNaverAccessToken(params.get("state"), params.get("code"));

            Map<String, Map> res2 = authService.getNaverUserInfo(res.get("access_token"));



            User user = userService.findByUserEmail(res2.get("response").get("email").toString());

            if (user == null) {

                ModelMap model = new ModelMap();
                res2.get("response").put("userType", "NAVER");
                model.addAllAttributes(res2.get("response"));
                return new ModelAndView("redirect:/signup", model);
            } else {
                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUserId(), user.getUserPw());

                Authentication authentication = this.authenticationProvider.authenticate(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);
                return new ModelAndView("redirect:/");
            }


        } catch (Exception e) {
            System.out.println(e);
            return new ModelAndView("error");
        }
    }

    /** Google Sign-in
     * @see <a href="https://developers.google.com/identity/sign-in/web/backend-auth">Google Sign-in Docs<a>
     * */
    @PostMapping("/auth/googlesign")
    public ModelAndView googleSignIn(@RequestBody Map<String, String> params) throws GeneralSecurityException, IOException {
        GooglePublicKeysManager publicKeysManager = new GooglePublicKeysManager.Builder(GoogleNetHttpTransport.newTrustedTransport(), new GsonFactory())
                .setPublicCertsEncodedUrl("https://www.googleapis.com/oauth2/v3/certs")
                .build();
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(GoogleNetHttpTransport.newTrustedTransport(), new GsonFactory())
                .setAudience(Collections.singletonList(authProperties.getGoogle().get("clientId")))
                .build();
        GoogleIdToken idToken = GoogleIdToken.parse(verifier.getJsonFactory(), params.get("id_token"));
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

            User user = userService.findByUserEmail(email);

            if (user == null) {

                ModelMap model = new ModelMap();
                model.addAttribute("userType", "GOOGLE");
                model.addAttribute("email", email);
                model.addAttribute("nickname", name);

                return new ModelAndView("redirect:/signup", model);
            } else {
                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUserId(), user.getUserPw());

                Authentication authentication = this.authenticationProvider.authenticate(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);
                return new ModelAndView("redirect:/");
            }

        } else {
          System.out.println("Invalid ID token.");
        }



        return new ModelAndView("error");
    }

}
