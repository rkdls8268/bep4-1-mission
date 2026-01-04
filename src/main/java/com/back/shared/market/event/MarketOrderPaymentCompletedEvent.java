package com.back.shared.market.event;


import com.back.shared.market.dto.OrderDto;
import com.back.standard.event.HaveEventName;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MarketOrderPaymentCompletedEvent implements HaveEventName {
  private OrderDto order;
}
