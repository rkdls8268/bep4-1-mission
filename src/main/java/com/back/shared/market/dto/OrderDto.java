package com.back.shared.market.dto;

import com.back.boundedContext.market.domain.Order;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class OrderDto {
  private int id;
  private LocalDateTime createDate;
  private LocalDateTime modifyDate;
  private int customerId;
  private String customerName;
  private int price;
  private int salePrice;
  private LocalDateTime requestPaymentDate;
  private LocalDateTime paymentDate;
  private LocalDateTime cancelDate;
  private LocalDateTime refundDate;

  public OrderDto(Order order) {
    this(
      order.getId(),
      order.getCreateDate(),
      order.getModifyDate(),
      order.getCustomer().getId(),
      order.getCustomer().getNickname(),
      order.getPrice(),
      order.getSalePrice(),
      order.getRequestPaymentDate(),
      order.getPaymentDate(),
      order.getCancelDate(),
      order.getRefundDate()
    );
  }
}