package xyz.lannt.chart.presentation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import xyz.lannt.chart.application.property.BittrexMarketProperty;
import xyz.lannt.chart.presentation.dto.ExchangeSellDto;

@Controller
@RequestMapping("/account")
public class AccountController {

  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private BittrexMarketProperty property;

  @RequestMapping(value = "/profit", method = RequestMethod.GET)
  public String getProfit() {
    return "html/account/profit";
  }

  @MessageMapping("/account_profit")
  @SendTo("/topic/account_profit")
  public ResponseEntity<?> getProfit(String baseCurrency) throws Exception {
    return ResponseEntity.ok(restTemplate.getForObject(property.getUrl() + "/account/profit/" + baseCurrency, String.class));
  }

  @MessageMapping("/sell")
  @SendTo("/topic/sell")
  public ResponseEntity<?> sell(ExchangeSellDto dto) throws Exception {
    return ResponseEntity.ok(restTemplate.postForObject(property.getUrl() + "/exchange/bittrex/sell/", dto, String.class));
  }
}
