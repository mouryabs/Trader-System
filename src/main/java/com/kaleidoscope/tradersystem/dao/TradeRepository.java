package com.kaleidoscope.tradersystem.dao;

import com.kaleidoscope.tradersystem.model.Trade;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface TradeRepository extends JpaRepository<Trade,Long> {
}