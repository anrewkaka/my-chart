package xyz.lannt.presentation.dto;

import lombok.Data;

@Data
public class CurrencyPriceRegistrationDto {

  private String exchange;

  private String currency;

  private String price;
}
