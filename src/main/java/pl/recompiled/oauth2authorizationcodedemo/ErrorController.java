package pl.recompiled.oauth2authorizationcodedemo;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorController {

    @ExceptionHandler(UserNotAuthorizedException.class)
    public String handleUserNotAuthorizedException(UserNotAuthorizedException exception) {
        return String.format("redirect:oauth2/authorization/%s", exception.getClient());
    }

}
