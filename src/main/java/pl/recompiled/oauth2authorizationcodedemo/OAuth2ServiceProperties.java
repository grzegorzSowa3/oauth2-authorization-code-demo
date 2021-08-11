package pl.recompiled.oauth2authorizationcodedemo;

import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

@Data
public class OAuth2ServiceProperties implements InitializingBean {
    private static final String DEFAULT_CLIENT_SECRET_PARAM_NAME = "client_secret";
    private static final String DEFAULT_CLIENT_ID_PARAM_NAME    = "client_id";
    private static final String DEFAULT_REDIRECT_URI_PARAM_NAME = "redirect_uri";
    private static final String DEFAULT_CODE_PARAM_NAME         = "code";
    private static final String DEFAULT_ACCESS_TOKEN_NAME       = "access_token";
    private static final String DEFAULT_GRANT_TYPE_PARAM_NAME   = "grant_type";
    private static final String DEFAULT_GRANT_TYPE              = "authorization_code";
    private static final String DEFAULT_RESPONSE_TYPE_PARAM_NAME = "response_type";

    private static final String DEFAULT_RESPONSE_TYPE           = "code";
    private static final String DEFAULT_STATE_PARAM_NAME        = "state";
    private static final String DEFAULT_USER_ID_NAME            = "id";

    private String userAuthorisationUri = null;
    private Map<String, String> additionalAuthParams = null;
    private URI redirectUri = null;
    private String accessTokenUri = null;
    private String clientId = null;
    private String clientSecret = null;
    private String userInfoUri = null;
    private Map<String, String> additionalInfoParams = null;

    private String accessTokenName = DEFAULT_ACCESS_TOKEN_NAME;
    private String clientSecretParamName = DEFAULT_CLIENT_SECRET_PARAM_NAME;
    private String clientIdParamName = DEFAULT_CLIENT_ID_PARAM_NAME;
    private String grantTypeParamName = DEFAULT_GRANT_TYPE_PARAM_NAME;
    private String grantType = DEFAULT_GRANT_TYPE;
    private String redirectUriParamName = DEFAULT_REDIRECT_URI_PARAM_NAME;
    private String responseTypeParamName = DEFAULT_RESPONSE_TYPE_PARAM_NAME;
    private String responseType = DEFAULT_RESPONSE_TYPE;
    private String stateParamName = DEFAULT_STATE_PARAM_NAME;
    private String codeParamName = DEFAULT_CODE_PARAM_NAME;
    private String userIdName = DEFAULT_USER_ID_NAME;

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(userAuthorisationUri, "The userAuthorisationUri must be set");
        Assert.notNull(redirectUri, "The redirectUri must be set");
        Assert.notNull(accessTokenUri, "The accessTokenUri must be set");
        Assert.notNull(clientId, "The clientId must be set");
        Assert.notNull(clientSecret, "The clientSecret must be set");
        Assert.notNull(userInfoUri, "The userInfoUri must be set");
    }

    public String getUserAuthorisationUri() {
        return userAuthorisationUri;
    }

    public void setUserAuthorisationUri(String userAuthorisationUri) {
        this.userAuthorisationUri = userAuthorisationUri;
    }

    public Map<String, String> getAdditionalAuthParams() {
        return additionalAuthParams;
    }

    public void setAdditionalAuthParams(Map<String, String> additionalAuthParams) {
        this.additionalAuthParams = additionalAuthParams;
    }

    public URI getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri) throws URISyntaxException {
        this.redirectUri = new URI(redirectUri);
    }
}
