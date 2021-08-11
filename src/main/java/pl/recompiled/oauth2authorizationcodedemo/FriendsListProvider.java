package pl.recompiled.oauth2authorizationcodedemo;

import org.springframework.security.core.Authentication;

import java.util.Set;

public interface FriendsListProvider {

    Set<Friend> getFriends(Authentication authentication);

}
