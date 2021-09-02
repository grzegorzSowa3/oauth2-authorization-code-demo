package pl.recompiled.oauth2authorizationcodedemo.facebook;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pl.recompiled.oauth2authorizationcodedemo.Friend;
import pl.recompiled.oauth2authorizationcodedemo.FriendsProvider;
import pl.recompiled.oauth2authorizationcodedemo.facebook.http.FacebookClient;
import pl.recompiled.oauth2authorizationcodedemo.facebook.http.FacebookUserFriendsResponse;

import java.util.HashSet;
import java.util.Set;

@Component
@Slf4j
@RequiredArgsConstructor
public class FacebookFriendsProvider implements FriendsProvider {

    private final FacebookClient facebookClient;

    @Override
    public Set<Friend> getFriends() {
        final FacebookUserFriendsResponse userFriendsResponse = facebookClient.getUserFriends();
        Set<Friend> friends = new HashSet<>();
        for (FacebookUserFriendsResponse.FacebookFriend friend : userFriendsResponse.getData()) {
            friends.add(new Friend(friend.getId(), friend.getName()));
        }
        return friends;
    }

}
