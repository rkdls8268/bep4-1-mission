package com.back.boundedContext.market.domain;

import static jakarta.persistence.CascadeType.PERSIST;
import static jakarta.persistence.CascadeType.REMOVE;
import static jakarta.persistence.FetchType.LAZY;
import com.back.global.jpa.Entity.BaseIdAndTime;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "MARKET_ORDER")
@NoArgsConstructor
@Getter
public class Order extends BaseIdAndTime {
  @ManyToOne(fetch = LAZY)
  private MarketMember customer;
  private LocalDateTime requestPaymentDate;
  private LocalDateTime paymentDate;
  private LocalDateTime cancelDate;
  private LocalDateTime refundDate;
  private int price;
  private int salePrice;

  @OneToMany(mappedBy = "order", cascade = {PERSIST, REMOVE}, orphanRemoval = true)
  private List<OrderItem> items = new ArrayList<>();

  public Order(Cart cart) {
    this.customer = cart.getCustomer();

    cart.getItems().forEach(item -> {
      addItem(item.getProduct());
    });
  }

  public void addItem(Product product) {
    OrderItem orderItem = new OrderItem(
      this,
      product,
      product.getName(),
      product.getPrice(),
      product.getSalePrice()
    );

    items.add(orderItem);

    price += product.getPrice();
    salePrice += product.getSalePrice();
  }

  public void completePayment() {
    paymentDate = LocalDateTime.now();
  }

  public boolean isPaid() {
    return paymentDate != null;
  }
}
