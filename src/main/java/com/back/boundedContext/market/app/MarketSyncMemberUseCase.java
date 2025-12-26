package com.back.boundedContext.market.app;

import com.back.boundedContext.market.domain.MarketMember;
import com.back.boundedContext.market.out.MarketMemberRepository;
import com.back.global.eventPublisher.EventPublisher;
import com.back.shared.market.dto.MarketMemberDto;
import com.back.shared.market.event.MarketMemberCreatedEvent;
import com.back.shared.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MarketSyncMemberUseCase {
  private final MarketMemberRepository marketMemberRepository;
  private final EventPublisher eventPublisher;

  public MarketMember syncMember(MemberDto member) {
    boolean isNew = !marketMemberRepository.existsById(member.getId());

    MarketMember marketMember = new MarketMember(
      member.getId(),
      member.getCreateDate(),
      member.getModifyDate(),
      member.getUsername(),
      "",
      member.getNickname(),
      member.getActivityScore()
    );

    marketMember = marketMemberRepository.save(marketMember);

    if (isNew) {
      // 새로 생성된 경우에만 이벤트 발행
      eventPublisher.publish(new MarketMemberCreatedEvent(new MarketMemberDto(marketMember)));

    }

    return marketMember;
  }
}
