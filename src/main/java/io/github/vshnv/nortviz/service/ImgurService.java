package io.github.vshnv.nortviz.service;

import io.github.vshnv.nortviz.entity.imgur.ImgurImageMeta;

import java.net.http.HttpClient;
import java.util.List;

public interface ImgurService {
    List<ImgurImageMeta> fetchUserImageMetas();
    ImgurImageMeta fetchImageMeta(final String id);
    boolean uploadImage(final byte[] imageData);
    byte[] fetchImage(final String id);
    void deleteImage(final String hash);
}
