package com.kaleidoscope.tradersystem.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Position {

    private String shareName;
    private int quantity;

}
