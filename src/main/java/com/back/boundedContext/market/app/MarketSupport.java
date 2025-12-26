package com.back.boundedContext.market.app;

import com.back.boundedContext.market.domain.Cart;
import com.back.boundedContext.market.domain.MarketMember;
import com.back.boundedContext.market.domain.Product;
import com.back.boundedContext.market.out.CartRepository;
import com.back.boundedContext.market.out.MarketMemberRepository;
import com.back.boundedContext.market.out.OrderRepository;
import com.back.boundedContext.market.out.ProductRepository;
import com.back.global.exception.DomainException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MarketSupport {

  private final MarketMemberRepository marketMemberRepository;
  private final ProductRepository productRepository;
  private final CartRepository cartRepository;
  private final OrderRepository orderRepository;

  public MarketMember findMemberByUsername(String username) {
    return marketMemberRepository.findByUsername(username)
      .orElseThrow(() -> new DomainException("409-2", "존재하지 않는 member입니다."));
  }

  public long countProducts() {
    return productRepository.count();
  }

  public long countOrders() {
    return orderRepository.count();
  }

  public Cart findCartByCustomer(MarketMember customer) {
    return cartRepository.findByCustomer(customer)
      .orElseThrow(() -> new DomainException("409-2", "존재하지 않는 cart입니다."));
  }

  public Product findProductById(int id) {
    return productRepository.findById(id)
      .orElseThrow(() -> new DomainException("409-2", "존재하지 않는 product입니다."));
  }
}
