package com.back.boundedContext.cash.domain;

import static jakarta.persistence.CascadeType.PERSIST;
import static jakarta.persistence.CascadeType.REMOVE;
import com.back.global.jpa.Entity.BaseEntity;
import com.back.global.jpa.Entity.BaseManualIdAndTime;
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
@Table(name = "CASH_WALLET")
@NoArgsConstructor
@Getter
public class Wallet extends BaseManualIdAndTime {

  /**
   * CashMember : Wallet = 1 : 1 관계인데 @OneToOne 쓰지 않은 이유는
   * 비관적 락을 사용할 예정인데 @OneToOne의 경우 비관적 락 사용이 불가능함
   * +) OneToOne 의 경우 Lazy Loading 사용도 불가함
   * 그런 의미에서 전략적으로 일대일 관계여도 @ManyToOne 으로 사용한다고 함
   */
  @ManyToOne(fetch = FetchType.LAZY)
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
