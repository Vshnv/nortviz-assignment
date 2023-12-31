package io.github.vshnv.nortviz.entity.response.imgur;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ImgurAuthRefreshResponse {
    @JsonProperty("access_token")
    private String accessToken;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
