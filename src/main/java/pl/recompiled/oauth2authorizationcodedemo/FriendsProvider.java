package pl.recompiled.oauth2authorizationcodedemo;

import pl.recompiled.oauth2authorizationcodedemo.facebook.FacebookFriend;

import java.util.Set;

public interface FriendsProvider {

    Set<FacebookFriend> getFriends();

}
