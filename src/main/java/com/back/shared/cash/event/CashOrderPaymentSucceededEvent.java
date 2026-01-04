package com.back.shared.cash.event;

import com.back.shared.market.dto.OrderDto;
import com.back.standard.event.HaveEventName;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CashOrderPaymentSucceededEvent implements HaveEventName {
  private final OrderDto order;
  private final long pgPaymentAmount;
}