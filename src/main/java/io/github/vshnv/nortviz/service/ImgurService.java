package io.github.vshnv.nortviz.service;

import io.github.vshnv.nortviz.entity.response.imgur.ImgurAuthRefreshResponse;
import io.github.vshnv.nortviz.entity.response.imgur.ImgurImageMeta;
import io.github.vshnv.nortviz.entity.response.imgur.ImgurResponseContainer;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public interface ImgurService {

    CompletableFuture<List<ImgurImageMeta>> fetchUserImageMetas();
    CompletableFuture<ImgurImageMeta> fetchImageMeta(final String id);
    CompletableFuture<ImgurImageMeta> uploadImage(final byte[] imageData, final String fileName);
    CompletableFuture<byte[]> fetchImage(final String id);
    CompletableFuture<Boolean> deleteImage(final String hash);
}
