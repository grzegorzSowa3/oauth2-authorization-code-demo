package pl.recompiled.oauth2authorizationcodedemo;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.util.Assert;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;

public class OAuth2AuthenticationEntryPoint implements AuthenticationEntryPoint, InitializingBean {

    private static final Logger LOG = LoggerFactory.getLogger(OAuth2AuthenticationEntryPoint.class);
    private static final int STATE_RANDOM_STRING_LENGTH = 10;

    private OAuth2ServiceProperties oAuth2ServiceProperties = null;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException {

        String state = RandomStringUtils.randomAlphanumeric(STATE_RANDOM_STRING_LENGTH);
        request.getSession().setAttribute(oAuth2ServiceProperties.getStateParamName(), state);

        String url = UriComponentsBuilder.fromPath(oAuth2ServiceProperties.getUserAuthorisationUri())
                .queryParam(oAuth2ServiceProperties.getClientIdParamName(), oAuth2ServiceProperties.getClientId())
                .queryParam(oAuth2ServiceProperties.getRedirectUriParamName(), redirectUriUsing(request))
                .queryParam(oAuth2ServiceProperties.getResponseTypeParamName(), oAuth2ServiceProperties.getResponseType())
                .queryParam(oAuth2ServiceProperties.getStateParamName(), state).toString();

        LOG.debug("authorizationUrl : {}", url);

        response.sendRedirect(url);
    }

    private URI redirectUriUsing(HttpServletRequest request) {
        URI redirect;
        URI redirectUri = oAuth2ServiceProperties.getRedirectUri();
        if (!redirectUri.isAbsolute()) {
            redirect = UriComponentsBuilder.fromPath(redirectUri.toString())
                    .scheme(request.getScheme())
                    .host(request.getServerName())
                    .port(request.getServerPort())
                    .build().toUri();
        } else {
            redirect = redirectUri;
        }
        return redirect;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(oAuth2ServiceProperties, "oAuth2ServiceProperties must be set");
    }

}
