package io.github.vshnv.nortviz.entity.response.imgur;

import java.util.List;

public class ImgurResponseContainer<T> {
    private int status;
    private boolean success;
    private T data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static class SingleImage extends ImgurResponseContainer<ImgurImageMeta> { }
    public static class ImageList extends ImgurResponseContainer<List<ImgurImageMeta>> { }
    public static class Boolean extends ImgurResponseContainer<java.lang.Boolean> { }


}