package com.kaleidoscope.tradersystem.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kaleidoscope.tradersystem.model.ShareInfoResponse;
import com.kaleidoscope.tradersystem.model.Trade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;

@RestController
public class TraderService {

    final String url = "http://localhost:8000/trade";

    final String getUrl = "http://localhost:8000/shares";

    @Autowired
    BlotterService blotterService;

    @PostMapping(
            value = "/executeTrade",
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<Object> executeTrade(@RequestBody Trade trade) throws MalformedURLException, URISyntaxException {
            if(Objects.nonNull(trade.getPositionPercentage())) {
                //long numberOfShares = blotterService.getShares(trade.getTicker());

                RestTemplate restTemplate = new RestTemplate();
                ResponseEntity<Void> response = null;
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);

                HttpEntity<String> entity = new HttpEntity<String>(headers);
                String newgetUrl = getUrl + "/" + trade.getTicker();
//                ResponseEntity<ShareInfoResponse> result = restTemplate.getForEntity(newgetUrl, ShareInfoResponse.class);
//                restTemplate.getForEntity(newgetUrl, ShareInfoResponse.class);
                //RequestEntity<Void> requestEntity = RequestEntity .post(new URL(newgetUrl).toURI()) .contentType(MediaType.APPLICATION_JSON).build();
                ResponseEntity<ShareInfoResponse> result = restTemplate.exchange(newgetUrl, HttpMethod.GET, entity, ShareInfoResponse.class);

                long contractNumber = (long) (result.getBody().getQuantity() * trade.getPositionPercentage()/100.0);
                trade.setNumberOfContract(contractNumber);
            }

//        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<Void> response = null;
//        ResponseEntity result = restTemplate.postForEntity(url, trade, Trade.class);

        blotterService.executeTrade(trade);

        return ResponseEntity.status(200).build();
    }

}
