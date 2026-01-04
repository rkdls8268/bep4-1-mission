package com.back.shared.cash.event;

import com.back.shared.market.dto.OrderDto;
import com.back.standard.event.HaveEventName;
import com.back.standard.resultType.ResultType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CashOrderPaymentFailedEvent implements ResultType, HaveEventName {
  private final String resultCode;
  private final String msg;
  private final OrderDto order;
  private final long pgPaymentAmount;
  private final long shortfallAmount;
}