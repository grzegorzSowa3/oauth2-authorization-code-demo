package pl.recompiled.oauth2authorizationcodedemo.facebook;

import lombok.Data;

import java.util.Set;

@Data
public class FacebookUserFriendsResponse {

    private Set<FacebookFriend> data;
    private Integer total_count;

}
