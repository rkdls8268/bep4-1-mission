package com.back.boundedContext.cash.app;

import com.back.boundedContext.cash.domain.CashMember;
import com.back.boundedContext.cash.domain.Wallet;
import com.back.boundedContext.cash.out.CashMemberRepository;
import com.back.boundedContext.cash.out.WalletRepository;
import com.back.global.exception.DomainException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CashSupport {
  private final CashMemberRepository cashMemberRepository;
  private final WalletRepository walletRepository;

  public CashMember findByUserName(String username) {
    return cashMemberRepository.findByUsername(username)
      .orElseThrow(() -> new DomainException("409-2", "존재하지 않는 holder 입니다."));
  }

  public Wallet findByHolder(CashMember holder) {
    return walletRepository.findByHolderId(holder.getId())
      .orElseThrow(() -> new DomainException("409-2", "존재하지 않는 wallet 입니다."));
  }
}
