package com.back.boundedContext.market.domain;


import com.back.global.jpa.Entity.BaseManualIdAndTime;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "MARKET_CART")
@NoArgsConstructor
@Getter
public class Cart extends BaseManualIdAndTime {
  @ManyToOne(fetch = FetchType.LAZY)
  private MarketMember customer;

  public Cart(MarketMember customer) {
    super(customer.getId());
    this.customer = customer;
  }
}