package pl.recompiled.oauth2authorizationcodedemo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserNotAuthorizedException extends RuntimeException {

    private final String client;

}

