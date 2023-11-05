package io.github.vshnv.nortviz.entity.response.imgur;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ImgurImageMeta {
    private String id;
    @JsonProperty("deletehash")
    private String deleteHash;
    private String link;

    public ImgurImageMeta() {}
    public ImgurImageMeta(final String id, final String deleteHash, final String link) {
        this.id = id;
        this.deleteHash = deleteHash;
        this.link = link;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeleteHash() {
        return deleteHash;
    }

    public void setDeleteHash(String deleteHash) {
        this.deleteHash = deleteHash;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
