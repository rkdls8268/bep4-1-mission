package com.back.boundedContext.cash.domain;

import static jakarta.persistence.CascadeType.PERSIST;
import static jakarta.persistence.CascadeType.REMOVE;
import com.back.global.jpa.Entity.BaseEntity;
import com.back.global.jpa.Entity.BaseManualIdAndTime;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
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
  private long balance;

  @OneToMany(mappedBy = "wallet", cascade = {PERSIST, REMOVE}, orphanRemoval = true)
  private List<CashLog> cashLogs = new ArrayList<>();

  public Wallet(CashMember holder) {
    super(holder.getId());
    this.holder = holder;
  }

  public boolean hasBalance() {
    return balance > 0;
  }

  public void credit(long amount, CashLog.EventType eventType, String relTypeCode, int relId) {
    balance += amount;

    addCashLog(amount, eventType, relTypeCode, relId);
  }

  public void credit(long amount, CashLog.EventType eventType, BaseEntity rel) {
    credit(amount, eventType, rel.getModelTypeCode(), rel.getId());
  }

  public void credit(long amount, CashLog.EventType eventType) {
    credit(amount, eventType, holder);
  }

  public void debit(long amount, CashLog.EventType eventType, String relTypeCode, int relId) {
    balance -= amount;

    addCashLog(-amount, eventType, relTypeCode, relId);
  }

  public void debit(long amount, CashLog.EventType eventType, BaseEntity rel) {
    debit(amount, eventType, rel.getModelTypeCode(), rel.getId());
  }

  public void debit(long amount, CashLog.EventType eventType) {
    debit(amount, eventType, holder);
  }

  private CashLog addCashLog(long amount, CashLog.EventType eventType, String relTypeCode, int relId) {
    CashLog cashLog = new CashLog(
      eventType,
      relTypeCode,
      relId,
      holder,
      this,
      amount,
      balance
    );

    cashLogs.add(cashLog);

    return cashLog;
  }
}
