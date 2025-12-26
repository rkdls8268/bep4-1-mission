package com.back.boundedContext.market.domain;

import static jakarta.persistence.FetchType.LAZY;
import com.back.global.jpa.Entity.BaseIdAndTime;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "MARKET_CART_ITEM")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CartItem extends BaseIdAndTime {
  @ManyToOne(fetch = LAZY)
  private Cart cart;
  @ManyToOne(fetch = LAZY)
  private Product product;
}
