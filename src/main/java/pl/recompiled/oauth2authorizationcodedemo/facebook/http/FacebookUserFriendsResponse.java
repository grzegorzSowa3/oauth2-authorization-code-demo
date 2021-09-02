package pl.recompiled.oauth2authorizationcodedemo.facebook.http;

import lombok.Data;

import java.util.List;

@Data
public class FacebookUserFriendsResponse {

    private List<FacebookFriend> data;
    private Integer total_count;

    @Data
    public static class FacebookFriend {
        private String id;
        private String name;
    }
}
