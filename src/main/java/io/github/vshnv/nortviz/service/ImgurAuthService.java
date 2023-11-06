package io.github.vshnv.nortviz.service;

import io.github.vshnv.nortviz.entity.response.imgur.ImgurAuthRefreshResponse;

import java.util.concurrent.CompletableFuture;

/**
 * Handles authentication for Imgur
 */
public interface ImgurAuthService {
    /**
     * Renews and fetches auth token from Imgur
     * @return Auth token response
     */
    ImgurAuthRefreshResponse authenticate();
}
