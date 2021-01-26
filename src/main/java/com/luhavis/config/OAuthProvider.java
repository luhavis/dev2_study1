package com.luhavis.config;

import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;

public enum OAuthProvider {
    KAKAO {
        public ClientRegistration.Builder getBuilder() {
            return getBuilder("kakao", ClientAuthenticationMethod.POST)
                    .scope("profile", "talk_message")
                    .authorizationUri("https://kauth.kakao.com/oauth/authorize")
                    .tokenUri("https://kauth.kakao.com/oauth/token")
                    .userInfoUri("https://kapi.kakao.com/v2/user/me")
                    .clientId("a3ad9f43adea2e4aad36e375e84e4d84")
                    .clientSecret("t6txcdXeuCgArKsvkQ63Ma6OhRZ55X8I")
                    .userNameAttributeName("id")
                    .redirectUri("http://localhost:8081/oauth2/callback")
                    .clientName("kakao");
        }
    };



    protected final ClientRegistration.Builder getBuilder(String registrationId,
                                                          ClientAuthenticationMethod method) {

        ClientRegistration.Builder builder = ClientRegistration.withRegistrationId(registrationId);
        builder.clientAuthenticationMethod(method);
        builder.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE);

//        builder.redirectUri(OAuthProvider.DEFAULT_LOGIN_REDIRECT_URL);

        return builder;
    }

    public abstract ClientRegistration.Builder getBuilder();
}
