package com.back.shared.market.dto;

import com.back.standard.modelType.CanGetModelTypeCode;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class OrderDto implements CanGetModelTypeCode {
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

  @Override
  public String getModelTypeCode() {
    return "Order";
  }
}