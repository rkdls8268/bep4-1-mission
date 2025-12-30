package com.back.boundedContext.payout.app;

import com.back.shared.market.dto.OrderDto;
import com.back.shared.market.dto.OrderItemDto;
import com.back.shared.market.out.MarketApiClient;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PayoutAddPayoutCandidateItemsUseCase {

  private final MarketApiClient marketApiClient;

  public void addPayoutCandidateItems(OrderDto order) {
    List<OrderItemDto> orderItems = marketApiClient.getOrderItems(order.getId());

    orderItems.forEach(item -> {
      log.debug("orderItem.id : {}", item.getId());
    });  }
}
