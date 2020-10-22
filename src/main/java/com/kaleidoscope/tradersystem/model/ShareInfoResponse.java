package com.kaleidoscope.tradersystem.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ShareInfoResponse {

    public ShareInfoResponse() {}

    public ShareInfoResponse(String shareName, long quantity) {
        this.shareName = shareName;
        this.quantity = quantity;
    }

    @JsonProperty
    private String shareName;

    @JsonProperty
    private long quantity;

}
