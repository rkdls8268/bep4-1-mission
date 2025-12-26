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
@Table(name = "MARKET_PRODUCT")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Product extends BaseIdAndTime {

  /**
   * product : market = 1:1
   * 이 경우에는 Wallet과는 다르게 비관적 락을 사용하지 않을 예정이므로 @OneToOne 사용해도 됨
   */
  @ManyToOne(fetch = LAZY)
  private MarketMember seller;
  private String sourceTypeCode;
  private int sourceId;
  private String name;
  private String description;
  private int price;
  private int salePrice;
}