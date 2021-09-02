package pl.recompiled.oauth2authorizationcodedemo.facebook.http;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.recompiled.oauth2authorizationcodedemo.UserNotAuthorizedException;

@Component
@RequiredArgsConstructor
public class FacebookClient {

    private final OAuth2AuthorizedClientService clientService;
    private final RestTemplate restTemplate = new RestTemplate();

    private final String FRIENDS_URL = "https://graph.facebook.com/me/friends";

    public FacebookUserFriendsResponse getUserFriends() {
        HttpHeaders headers = httpHeaders();
        return restTemplate.exchange(
                FRIENDS_URL,
                HttpMethod.GET,
                new HttpEntity<Void>(headers),
                FacebookUserFriendsResponse.class).getBody();
    }

    private HttpHeaders httpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", String.format("Bearer %s", accessToken()));
        return headers;
    }

    private String accessToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        OAuth2AuthorizedClient client = clientService.loadAuthorizedClient("facebook", authentication.getName());
        if (client == null) {
            throw new UserNotAuthorizedException("facebook");
        }
        return client.getAccessToken().getTokenValue();
    }
}
