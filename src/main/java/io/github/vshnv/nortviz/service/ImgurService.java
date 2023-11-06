package io.github.vshnv.nortviz.service;

import io.github.vshnv.nortviz.entity.response.imgur.ImgurAuthRefreshResponse;
import io.github.vshnv.nortviz.entity.response.imgur.ImgurImageMeta;
import io.github.vshnv.nortviz.entity.response.imgur.ImgurResponseContainer;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * Exposes required Imgur APIs
 */
public interface ImgurService {

    /**
     * Fetches all of user's image's metas related to configured account
     * @return Future with result of image metas
     */
    CompletableFuture<List<ImgurImageMeta>> fetchUserImageMetas();
    /**
     * Fetches image meta for image with given id
     * @param id id of ImageMeta (ImageMeta#getId())
     * @return Future with result of image meta
     */
    CompletableFuture<ImgurImageMeta> fetchImageMeta(final String id);
    /**
     * Uploads given byte array to imgur as image
     * @param imageData Image to be uploaded as a byte array
     * @param fileName fileName for multipart upload (Can be random)
     * @return Future with result of image meta
     */
    CompletableFuture<ImgurImageMeta> uploadImage(final byte[] imageData, final String fileName);
    /**
     * Fetches image as byte array for image with given id
     * @param id id of ImageMeta (ImageMeta#getId())
     * @return Future with byte array for image with given id
     */
    CompletableFuture<byte[]> fetchImage(final String id);

    /**
     * Deletes image with given id
     * @param id id of ImageMeta (ImageMeta#getId())
     * @return Future with result of delete operation
     */
    CompletableFuture<Boolean> deleteImage(final String id);
}
