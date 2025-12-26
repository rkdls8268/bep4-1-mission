package com.back.boundedContext.market.app;

import com.back.boundedContext.market.domain.MarketMember;
import com.back.boundedContext.market.domain.Product;
import com.back.shared.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MarketFacade {

  private final MarketSupport marketSupport;
  private final MarketSyncMemberUseCase marketSyncMemberUseCase;
  private final MarketCreateProductUseCase marketCreateProductUseCase;

  @Transactional
  public MarketMember syncMember(MemberDto member) {
    return marketSyncMemberUseCase.syncMember(member);
  }

  @Transactional(readOnly = true)
  public MarketMember findMemberByUsername(String username) {
    return marketSupport.findMemberByUsername(username);
  }

  @Transactional
  public Product createProduct(
    MarketMember seller,
    String sourceTypeCode,
    int sourceId,
    String name,
    String description,
    int price,
    int salePrice
  ) {
    return marketCreateProductUseCase.createProduct(seller, sourceTypeCode, sourceId, name, description,
      price, salePrice);
  }

  @Transactional(readOnly = true)
  public long countProducts() {
    return marketSupport.countProducts();
  }
}
