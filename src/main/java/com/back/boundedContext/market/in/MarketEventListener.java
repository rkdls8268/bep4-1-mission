package com.back.boundedContext.market.in;

import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;
import com.back.boundedContext.market.app.MarketFacade;
import com.back.shared.cash.event.CashOrderPaymentFailedEvent;
import com.back.shared.cash.event.CashOrderPaymentSucceededEvent;
import com.back.shared.market.event.MarketMemberCreatedEvent;
import com.back.shared.member.event.MemberJoinedEvent;
import com.back.shared.member.event.MemberModifiedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class MarketEventListener {

  private final MarketFacade marketFacade;

  @Transactional(propagation = REQUIRES_NEW)
  @KafkaListener(topics = "MemberJoinedEvent", groupId = "MarketEventListener__handle__1")
  public void handle(MemberJoinedEvent event) {
    marketFacade.syncMember(event.getMember());
  }

  @Transactional(propagation = REQUIRES_NEW)
  @KafkaListener(topics = "MemberModifiedEvent", groupId = "MarketEventListener__handle__2")
  public void handle(MemberModifiedEvent event) {
    marketFacade.syncMember(event.getMember());
  }

  @Transactional(propagation = REQUIRES_NEW)
  @KafkaListener(topics = "MarketMemberCreatedEvent", groupId = "MarketEventListener__handle__3")
  public void handle(MarketMemberCreatedEvent event) {
    marketFacade.createCart(event.getMember());
  }

  @Transactional(propagation = REQUIRES_NEW)
  @KafkaListener(topics = "CashOrderPaymentSucceededEvent", groupId = "MarketEventListener__handle__4")
  public void handle(CashOrderPaymentSucceededEvent event) {
    marketFacade.completePayment(event.getOrder().getId());
  }

  @Transactional(propagation = REQUIRES_NEW)
  @KafkaListener(topics = "CashOrderPaymentFailedEvent", groupId = "MarketEventListener__handle__5")
  public void handle(CashOrderPaymentFailedEvent event) {
    marketFacade.cancelRequestPayment(event.getOrder().getId());
  }
}
