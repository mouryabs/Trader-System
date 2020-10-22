package com.kaleidoscope.tradersystem.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kaleidoscope.tradersystem.dao.PositionRepository;
import com.kaleidoscope.tradersystem.dao.TradeRepository;
import com.kaleidoscope.tradersystem.entity.PositionEntity;
import com.kaleidoscope.tradersystem.model.Position;
import com.kaleidoscope.tradersystem.model.Trade;
import com.kaleidoscope.tradersystem.model.ShareInfoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Component
@RestController
public class BlotterService {

    private TradeRepository tradeRepository;
    private PositionRepository positionRepository;

    public BlotterService(@Autowired TradeRepository tradeRepository,
                          @Autowired PositionRepository positionRepository) {
        this.tradeRepository = tradeRepository;
        this.positionRepository = positionRepository;
    }

    @GetMapping(
            value = "/shares/{share_name}",
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<Object> getShares(@PathVariable("share_name") String shareName) {
        if(!positionRepository.existsById(shareName)){
            PositionEntity newPos = new PositionEntity();
            newPos.setQuantity(0);
            newPos.setShareName(shareName);
            positionRepository.save(newPos);
        }

        PositionEntity positionEntity = positionRepository.getOne(shareName);

        ShareInfoResponse shareInfoResponse = ShareInfoResponse.builder()
                .quantity(positionEntity.getQuantity())
                .shareName(positionEntity.getShareName())
                .build();

        return ResponseEntity.status(200).body(shareInfoResponse);
    }

//    public long getShares(String shareName) {
//        if(!positionRepository.existsById(shareName)){
//            PositionEntity newPos = new PositionEntity();
//            newPos.setQuantity(0);
//            newPos.setShareName(shareName);
//            positionRepository.save(newPos);
//        }
//
//        PositionEntity positionEntity = positionRepository.getOne(shareName);
//        return positionEntity.getQuantity();
//    }


    @JsonProperty("trade")
    @PostMapping(
            value = "/trade",
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<Object> executeTrade(@RequestBody Trade trade) {
        if(positionRepository.existsById(trade.getTicker())){
            PositionEntity newPos = new PositionEntity();
            newPos.setQuantity(0);
            newPos.setShareName(trade.getTicker());
            positionRepository.save(newPos);
        }

        PositionEntity positionEntity = positionRepository.getOne(trade.getTicker());

        if (trade.getSide().equals("B")) {
            positionEntity.setQuantity(positionEntity.getQuantity() + trade.getNumberOfContract());
        } else {
            positionEntity.setQuantity(positionEntity.getQuantity() - trade.getNumberOfContract());
        }

        positionRepository.save(positionEntity);
        tradeRepository.save(trade);

        ShareInfoResponse shareInfoResponse = ShareInfoResponse.builder()
                .quantity(positionEntity.getQuantity())
                .shareName(positionEntity.getShareName())
                .build();

        return ResponseEntity.status(200).body(shareInfoResponse);
    }

}
