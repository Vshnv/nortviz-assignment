package io.github.vshnv.nortviz.service;

import io.github.vshnv.nortviz.entity.request.imgur.ImgurAuthRefreshRequest;
import io.github.vshnv.nortviz.entity.response.imgur.ImgurAuthRefreshResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HttpImgurAuthService implements ImgurAuthService{
    private static final String BASE_URL = "https://api.imgur.com";
    private final RestTemplate restTemplate;
    private final String refreshToken;
    private final String clientId;
    private final String clientSecret;

    public HttpImgurAuthService(@Qualifier("plain") RestTemplate restTemplate, @Value("${imgur.refresh_token}") String refreshToken, @Value("${imgur.client_id}")String clientId, @Value("${imgur.client_secret}") String clientSecret) {
        this.restTemplate = restTemplate;
        this.refreshToken = refreshToken;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    @Override
    public ImgurAuthRefreshResponse authenticate() {
        final ImgurAuthRefreshRequest request = new ImgurAuthRefreshRequest(refreshToken, clientId, clientSecret, "refresh_token");
        try {
            return restTemplate.postForObject(BASE_URL + "/oauth2/token", request, ImgurAuthRefreshResponse.class);
        } catch (Exception exception) {
            return null;
        }
    }
}
