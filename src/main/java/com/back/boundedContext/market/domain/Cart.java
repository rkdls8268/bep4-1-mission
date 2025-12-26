package com.back.boundedContext.market.domain;


import com.back.global.jpa.Entity.BaseManualIdAndTime;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "MARKET_CART")
@NoArgsConstructor
@Getter
public class Cart extends BaseManualIdAndTime {
  @ManyToOne(fetch = FetchType.LAZY)
  private MarketMember customer;

  @OneToMany(mappedBy = "cart", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
  private List<CartItem> items = new ArrayList<>();

  public Cart(MarketMember customer) {
    super(customer.getId());
    this.customer = customer;
  }

  public boolean hasItems() {
    return !this.getItems().isEmpty();
  }

  public void addItem(Product product) {
    CartItem cartItem = new CartItem(this, product);
    this.getItems().add(cartItem);
  }

  public void clearItems() {
    this.getItems().clear();
  }
}