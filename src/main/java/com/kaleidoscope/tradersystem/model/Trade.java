package com.kaleidoscope.tradersystem.model;

import lombok.Data;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "trade")
@Data
public class Trade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String traderName;
    private Date date;
    private String ticker;
    private String side;
    private long numberOfContract;
    private double positionPercentage;

}
