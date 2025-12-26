package com.back.boundedContext.cash.app;

import com.back.boundedContext.cash.domain.CashMember;
import com.back.boundedContext.cash.domain.Wallet;
import com.back.shared.cash.dto.CashMemberDto;
import com.back.shared.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CashFacade {

  private final CashSupport cashSupport;
  private final CashCreateWalletUseCase cashCreateWalletUseCase;
  private final CashSyncMemberUseCase cashSyncMemberUseCase;


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
}
