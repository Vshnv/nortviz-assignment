package io.github.vshnv.nortviz.service;

import io.github.vshnv.nortviz.entity.response.imgur.ImgurImageMeta;
import io.github.vshnv.nortviz.entity.response.imgur.ImgurResponseContainer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class HttpImgurService implements ImgurService {
    private static final String BASE_URL = "https://api.imgur.com";
    private final RestTemplate restTemplate;

    public HttpImgurService(@Qualifier("imgur_authenticated") RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public CompletableFuture<List<ImgurImageMeta>> fetchUserImageMetas() {
        return CompletableFuture.supplyAsync(() -> {
            final ImgurResponseContainer.ImageList response = restTemplate.getForObject(BASE_URL + "/3/account/me/images", ImgurResponseContainer.ImageList.class);
            if (response == null || !response.isSuccess()) {
                throw new RuntimeException("Failed to upload image");
            }
            return response.getData();
        });
    }

    @Override
    public CompletableFuture<ImgurImageMeta> fetchImageMeta(String id) {
        return CompletableFuture.supplyAsync(() -> {
            final ImgurResponseContainer.SingleImage response = restTemplate.getForObject(BASE_URL + "/3/image/" + id, ImgurResponseContainer.SingleImage.class);
            if (response == null || !response.isSuccess()) {
                throw new RuntimeException("Failed to upload image");
            }
            return response.getData();
        });
    }

    @Override
    public CompletableFuture<ImgurImageMeta> uploadImage(byte[] imageData, String filename) {
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
        map.add("name", filename);
        map.add("filename", filename);
        final ByteArrayResource contentsAsResource = new ByteArrayResource(imageData) {
            @Override
            public String getFilename() {
                return filename; // Filename has to be returned in order to be able to post.
            }
        };
        map.add("image", contentsAsResource);
        return CompletableFuture.supplyAsync(() -> {
            final ImgurResponseContainer.SingleImage response = restTemplate.postForObject(BASE_URL + "/3/upload", map, ImgurResponseContainer.SingleImage.class);
            if (response == null || !response.isSuccess()) {
                throw new RuntimeException("Failed to upload image");
            }
            return response.getData();
        });
    }

    @Override
    public CompletableFuture<byte[]> fetchImage(String id) {
        return fetchImageMeta(id).thenApply((meta) -> {
            final String link = meta.getLink();
            return restTemplate.getForObject(link, byte[].class);
        });
    }

    @Override
    public CompletableFuture<Boolean> deleteImage(String id) {
        return CompletableFuture.supplyAsync(() -> {
            final ResponseEntity<ImgurResponseContainer.Boolean> responseEntity = restTemplate.exchange(BASE_URL + "/3/image/" + id, HttpMethod.DELETE,null, ImgurResponseContainer.Boolean.class);
            if (responseEntity.getStatusCode() != HttpStatus.OK) {
                throw new RuntimeException("Failed to delete image");
            }
            final ImgurResponseContainer.Boolean response = responseEntity.getBody();
            if (response == null || !response.isSuccess()) {
                throw new RuntimeException("Failed to upload image");
            }
            return response.getData();
        });
    }

}
