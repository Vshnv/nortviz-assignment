package io.github.vshnv.nortviz.controller;

import io.github.vshnv.nortviz.entity.response.imgur.ImgurImageMeta;
import io.github.vshnv.nortviz.service.ImgurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

@Controller
@RequestMapping("/api/v1")
public class ImageManagementController {

    private final ImgurService imgurService;
    @Autowired
    public ImageManagementController(ImgurService imgurService) {
        this.imgurService = imgurService;
    }

    @PostMapping("/images/upload")
    @Async
    public @ResponseBody CompletableFuture<ResponseEntity<String>> uploadImage(@RequestPart("file") MultipartFile file) {
        try {
            final byte[] data = file.getBytes();
            return imgurService.uploadImage(data, file.getName())
                    .thenApply((meta) -> ResponseEntity.ok(meta.getId()))
                    .exceptionally((exception) -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
        } catch (IOException exception) {
            return CompletableFuture.completedFuture(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
        }
    }
}
