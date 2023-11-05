package io.github.vshnv.nortviz.controller;

import io.github.vshnv.nortviz.entity.response.ImageId;
import io.github.vshnv.nortviz.entity.response.imgur.ImgurImageMeta;
import io.github.vshnv.nortviz.service.ImgurService;
import org.overviewproject.mime_types.GetBytesException;
import org.overviewproject.mime_types.MimeTypeDetector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.util.MimeType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api/v1/image")
public class ImageManagementController {

    private final ImgurService imgurService;
    @Autowired
    public ImageManagementController(ImgurService imgurService) {
        this.imgurService = imgurService;
    }

    @PostMapping("/upload")
    @Async
    public @ResponseBody CompletableFuture<ResponseEntity<ImageId>> uploadImage(@RequestPart("file") MultipartFile file) {
        try {
            final byte[] data = file.getBytes();
            return imgurService.uploadImage(data, file.getName())
                    .thenApply((meta) -> ResponseEntity.ok(new ImageId(meta.getId())))
                    .exceptionally((exception) -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
        } catch (IOException exception) {
            return CompletableFuture.completedFuture(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
        }
    }

    @GetMapping("/list")
    @Async
    public @ResponseBody CompletableFuture<ResponseEntity<List<ImageId>>> getImagesList() {
        return imgurService.fetchUserImageMetas()
                .thenApply((metas) ->
                        ResponseEntity.ok(
                                metas.stream()
                                        .map(meta -> new ImageId(meta.getId()))
                                        .collect(Collectors.toList())
                        )
                )
                .exceptionally((exception) -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    @GetMapping
    @Async
    public @ResponseBody CompletableFuture<ResponseEntity<byte[]>> viewImage(@RequestParam("id")String id) {
        return imgurService.fetchImage(id)
                .thenApply((bytes) -> ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(bytes))
                .exceptionally((exception) -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping
    @Async
    public @ResponseBody CompletableFuture<ResponseEntity<Object>> deleteImage(@RequestParam("id") String id) {
        return imgurService.deleteImage(id)
                .thenApply((result) -> ResponseEntity.status(result ? HttpStatus.OK : HttpStatus.NOT_FOUND).build())
                .exceptionally((exception) -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }
}
