package com.luhavis.config;

import com.luhavis.domain.Role;
import com.luhavis.domain.User;
import com.luhavis.domain.UserRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.LinkedHashMap;

@RequiredArgsConstructor
@Service
public class CustomOAuth2AuthorizedClientService implements OAuth2AuthorizedClientService {

    private final UserRepository userRepository;

    @Override
    public <T extends OAuth2AuthorizedClient> T loadAuthorizedClient(String clientRegistrationId, String principalName) {
        throw new NotImplementedException();
    }

    @Override
    public void saveAuthorizedClient(OAuth2AuthorizedClient authorizedClient, Authentication principal) {
        String providerType = authorizedClient.getClientRegistration().getRegistrationId();
        OAuth2AccessToken accessToken = authorizedClient.getAccessToken();

        OAuth2User oAuth2User = (OAuth2User) principal.getPrincipal();
        String id = String.valueOf(oAuth2User.getAttributes().get("id"));
        String name = (String) ((LinkedHashMap) ((LinkedHashMap) oAuth2User.getAttribute("kakao_account")).get("profile")).get("nickname");

        User user = new User(id, "", name, "", "", "", Role.MEMBER, providerType, "", "");
        userRepository.save(user);

    }

    @Override
    public void removeAuthorizedClient(String clientRegistrationId, String principalName) {
        throw new NotImplementedException();
    }
}
