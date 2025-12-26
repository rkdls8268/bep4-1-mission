package com.back.boundedContext.cash.app;

import com.back.boundedContext.cash.domain.CashMember;
import com.back.boundedContext.cash.domain.Wallet;
import com.back.boundedContext.cash.out.CashMemberRepository;
import com.back.boundedContext.cash.out.WalletRepository;
import com.back.global.exception.DomainException;
import com.back.shared.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CashFacade {

  private final CashMemberRepository cashMemberRepository;
  private final WalletRepository walletRepository;

  @Transactional
  public CashMember syncMember(MemberDto memberDto) {
    CashMember cashMember = new CashMember(
      memberDto.getId(),
      memberDto.getCreateDate(),
      memberDto.getModifyDate(),
      memberDto.getUsername(),
      "",
      memberDto.getNickname(),
      memberDto.getActivityScore()
    );

    return cashMemberRepository.save(cashMember);
  }

  @Transactional
  public Wallet createWallet(CashMember holder) {
    Wallet wallet = new Wallet(holder);
    return walletRepository.save(wallet);
  }

  @Transactional(readOnly = true)
  public CashMember findByUserName(String username) {
    return cashMemberRepository.findByUsername(username)
      .orElseThrow(() -> new DomainException("409-2", "존재하지 않는 holder 입니다."));
  }

  @Transactional(readOnly = true)
  public Wallet findByHolder(CashMember holder) {
    return walletRepository.findByHolderId(holder.getId())
      .orElseThrow(() -> new DomainException("409-2", "존재하지 않는 wallet 입니다."));
  }
}
