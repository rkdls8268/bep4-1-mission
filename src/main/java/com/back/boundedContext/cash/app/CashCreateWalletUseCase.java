package com.back.boundedContext.cash.app;

import com.back.boundedContext.cash.domain.CashMember;
import com.back.boundedContext.cash.domain.Wallet;
import com.back.boundedContext.cash.out.CashMemberRepository;
import com.back.boundedContext.cash.out.WalletRepository;
import com.back.shared.cash.dto.CashMemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CashCreateWalletUseCase {

  private final WalletRepository walletRepository;
  private final CashMemberRepository cashMemberRepository;

  public Wallet createWallet(CashMemberDto cashMemberDto) {
    /**
     * getReferenceById vs findById
     * findById
     *  sql 바로 실행
     * getReferenceById
     *  지연 로딩을 통해 데이터 조회. 즉, 엔티티를 실제로 사용할 때까지 db 조회 지연
     *  실제 엔티티 객체가 필요한 시점에서는 프록시 객체가 아닌 실제 엔티티를 반환한다. 처음에는 proxy 객체로 가지고 있음
     */
    CashMember holder = cashMemberRepository.getReferenceById(cashMemberDto.getId());
    Wallet wallet = new Wallet(holder);
    return walletRepository.save(wallet);
  }
}
