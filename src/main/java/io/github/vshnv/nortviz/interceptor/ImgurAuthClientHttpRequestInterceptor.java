package io.github.vshnv.nortviz.interceptor;

import io.github.vshnv.nortviz.service.ImgurAuthService;
import io.github.vshnv.nortviz.service.ImgurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Component
public class ImgurAuthClientHttpRequestInterceptor implements ClientHttpRequestInterceptor {
    private final ImgurAuthService imgurService;
    private String accessToken;

    @Autowired
    public ImgurAuthClientHttpRequestInterceptor(final ImgurAuthService imgurService) {
        this.imgurService = imgurService;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        if (accessToken == null) {
            accessToken = imgurService.authenticate().getAccessToken();
        }
        request.getHeaders().remove(HttpHeaders.AUTHORIZATION);
        request.getHeaders().set(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken);
        ClientHttpResponse response = execution.execute(request, body);
        if (response.getStatusCode() == HttpStatus.FORBIDDEN) {
            accessToken = null;
            response = execution.execute(request, body);
        }
        return response;
    }
}
