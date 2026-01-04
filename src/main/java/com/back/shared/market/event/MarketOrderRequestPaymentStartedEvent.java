package com.back.shared.market.event;


import com.back.shared.market.dto.OrderDto;
import com.back.standard.event.HaveEventName;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MarketOrderRequestPaymentStartedEvent implements HaveEventName {
  private OrderDto order;
  private long pgPaymentAmount;
}