package com.back.boundedContext.post.in;

import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;
import com.back.boundedContext.post.app.PostFacade;
import com.back.shared.member.event.MemberJoinedEvent;
import com.back.shared.member.event.MemberModifiedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * 발행된 event 핸들링하는 함수
 */
@Component
@RequiredArgsConstructor
public class PostEventListener {

  private final PostFacade postFacade;

  @Transactional(propagation = REQUIRES_NEW)
  @KafkaListener(topics = "MemberJoinedEvent", groupId = "PostEventListener__handle__1")
  public void handle(MemberJoinedEvent event) {
    // member -> postMember 동기화
    postFacade.syncMember(event.getMember());
  }

  @Transactional(propagation = REQUIRES_NEW)
  @KafkaListener(topics = "MemberModifiedEvent", groupId = "PostEventListener__handle__2")
  public void handle(MemberModifiedEvent event) {
    // member -> postMember 동기화
    postFacade.syncMember(event.getMember());
  }
}
