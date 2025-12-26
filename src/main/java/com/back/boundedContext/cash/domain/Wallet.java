package com.back.boundedContext.cash.domain;

import com.back.global.jpa.Entity.BaseManualIdAndTime;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CASH_WALLET")
@NoArgsConstructor
@Getter
public class Wallet extends BaseManualIdAndTime {

  // TODO ManyToOne 으로 한 이유?
//  @ManyToOne(fetch = FetchType.LAZY)
//  private CashMember holder;
  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "holder_id", nullable = false, unique = true)
  private CashMember holder;

  public Wallet(CashMember holder) {
    super(holder.getId());
    this.holder = holder;
  }
}
