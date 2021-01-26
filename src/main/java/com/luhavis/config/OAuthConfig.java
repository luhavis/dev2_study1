package com.luhavis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;

import java.util.Collections;

@Configuration
public class OAuthConfig {

    @Bean
    public OAuth2AuthorizedClientService authorizedClientService(CustomOAuth2AuthorizedClientService customOAuth2AuthorizedClientService) {
        return customOAuth2AuthorizedClientService;
    }

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        final ClientRegistration clientRegistration = OAuthProvider.KAKAO
                .getBuilder()
                .build();

        return new InMemoryClientRegistrationRepository(Collections.singletonList(clientRegistration));
    }

}
