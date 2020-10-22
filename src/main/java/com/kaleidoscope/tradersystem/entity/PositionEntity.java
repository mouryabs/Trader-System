package com.kaleidoscope.tradersystem.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "position")
@Data
public class PositionEntity {

    @Id
    @Column(length = 50, unique = true, name = "share_name")
    private String shareName;

    @Column(name = "quantity")
    private long quantity;

}
