package io.github.vshnv.nortviz.entity.request.imgur;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ImgurAuthRefreshRequest {
    @JsonProperty("refresh_token")
    private final String refreshToken;
    @JsonProperty("client_id")
    private final String clientId;
    @JsonProperty("client_secret")
    private final String clientSecret;
    @JsonProperty("grant_type")
    private final String grantType;

    public ImgurAuthRefreshRequest(String refreshToken, String clientId, String clientSecret, String grantType) {
        this.refreshToken = refreshToken;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.grantType = grantType;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public String getClientId() {
        return clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public String getGrantType() {
        return grantType;
    }
}
