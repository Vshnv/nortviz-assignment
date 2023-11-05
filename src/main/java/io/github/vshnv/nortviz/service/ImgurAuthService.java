package io.github.vshnv.nortviz.service;

import io.github.vshnv.nortviz.entity.response.imgur.ImgurAuthRefreshResponse;

import java.util.concurrent.CompletableFuture;

public interface ImgurAuthService {
    ImgurAuthRefreshResponse authenticate();
}
