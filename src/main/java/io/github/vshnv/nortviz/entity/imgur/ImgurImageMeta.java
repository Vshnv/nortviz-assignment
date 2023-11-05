package io.github.vshnv.nortviz.entity.imgur;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ImgurImageMeta {
    private String id;
    @JsonProperty("deletehash")
    private String deleteHash;
    private String link;

}
