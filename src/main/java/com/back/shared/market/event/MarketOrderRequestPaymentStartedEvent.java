package com.back.shared.market.event;


import com.back.shared.market.dto.OrderDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MarketOrderRequestPaymentStartedEvent {
  private OrderDto order;
  private long pgPaymentAmount;
}