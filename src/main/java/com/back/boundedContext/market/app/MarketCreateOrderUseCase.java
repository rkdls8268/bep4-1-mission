package com.back.boundedContext.market.app;

import com.back.boundedContext.market.domain.Cart;
import com.back.boundedContext.market.domain.Order;
import com.back.boundedContext.market.out.OrderRepository;
import com.back.global.exception.DomainException;
import com.back.global.rsData.RsData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MarketCreateOrderUseCase {

  private final OrderRepository orderRepository;

  public RsData<Order> createOrder(Cart cart) {
    Order order = new Order(cart);

    order = orderRepository.save(order);

    cart.clearItems();

    return new RsData<>(
      "201-1",
      "%d번 주문이 생성되었습니다.".formatted(order.getId()),
      order
    );
  }

  public Order findOrderById(int id) {
    return orderRepository.findById(id)
      .orElseThrow(() -> new DomainException("409-2", "존재하지 않는 Order입니다."));
  }
}
