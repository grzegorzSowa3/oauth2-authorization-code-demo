package pl.recompiled.oauth2authorizationcodedemo.facebook;

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
import pl.recompiled.oauth2authorizationcodedemo.FriendsProvider;
import pl.recompiled.oauth2authorizationcodedemo.UserNotAuthorizedException;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class FacebookClient implements FriendsProvider {

    private final OAuth2AuthorizedClientService clientService;
    private final RestTemplate restTemplate = new RestTemplate();

    private final String FRIENDS_URL = "https://graph.facebook.com/me/friends";
    private final String CLIENT_ID = "facebook";

    @Override
    public Set<FacebookFriend> getFriends() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", String.format("Bearer %s", accessToken()));
        return restTemplate.exchange(
                FRIENDS_URL,
                HttpMethod.GET,
                new HttpEntity<Void>(headers),
                FacebookUserFriendsResponse.class
        ).getBody().getData();
    }

    private String accessToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        OAuth2AuthorizedClient client = clientService.loadAuthorizedClient(CLIENT_ID, authentication.getName());
        if (client == null) {
            throw new UserNotAuthorizedException(CLIENT_ID);
        }
        return client.getAccessToken().getTokenValue();
    }

}
