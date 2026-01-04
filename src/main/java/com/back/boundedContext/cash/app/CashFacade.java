package com.back.boundedContext.cash.app;

import com.back.boundedContext.cash.domain.CashMember;
import com.back.boundedContext.cash.domain.Wallet;
import com.back.shared.cash.dto.CashMemberDto;
import com.back.shared.market.dto.OrderDto;
import com.back.shared.member.dto.MemberDto;
import com.back.shared.payout.dto.PayoutDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CashFacade {

  private final CashSupport cashSupport;
  private final CashCreateWalletUseCase cashCreateWalletUseCase;
  private final CashSyncMemberUseCase cashSyncMemberUseCase;
  private final CashOrderCompletePaymentUseCase cashOrderCompletePaymentUseCase;
  private final CashCompletePayoutUseCase cashCompletePayoutUseCase;

  @Transactional
  public CashMember syncMember(MemberDto memberDto) {
    return cashSyncMemberUseCase.syncMember(memberDto);
  }

  @Transactional
  public Wallet createWallet(CashMemberDto cashMemberDto) {
    return cashCreateWalletUseCase.createWallet(cashMemberDto);
  }

  @Transactional(readOnly = true)
  public CashMember findByUserName(String username) {
    return cashSupport.findByUserName(username);
  }

  @Transactional(readOnly = true)
  public Wallet findByHolder(CashMember holder) {
    return cashSupport.findByHolder(holder);
  }

  @Transactional
  public void completeOrderPayment(OrderDto order, long pgPaymentAmount) {
    cashOrderCompletePaymentUseCase.completeOrderPayment(order, pgPaymentAmount);
  }

  @Transactional(readOnly = true)
  public Wallet findWalletByHolderId(int holderId) {
    return cashSupport.findWalletByHolderId(holderId);
  }

  @Transactional
  public void completePayout(PayoutDto payoutDto) {
    cashCompletePayoutUseCase.completePayout(payoutDto);
  }
}
