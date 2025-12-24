package com.back.boundedContext.post.in;

import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;
import com.back.boundedContext.post.app.PostFacade;
import com.back.shared.event.MemberJoinedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

/**
 * 발행된 event 핸들링하는 함수
 */
@Component
@RequiredArgsConstructor
public class PostEventListener {

  private final PostFacade postFacade;

  @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
  @Transactional(propagation = REQUIRES_NEW)
  public void handle(MemberJoinedEvent event) {
    // member -> postMember 동기화
    postFacade.syncMember(event.getMember());
  }
}
