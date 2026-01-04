package com.back.boundedContext.member.in;

import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;
import com.back.boundedContext.member.app.MemberFacade;
import com.back.boundedContext.member.domain.Member;
import com.back.shared.post.event.CommentCreatedEvent;
import com.back.shared.post.event.PostCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * 발행된 event 핸들링하는 함수
 */
@Component
@RequiredArgsConstructor
public class MemberEventListener {
  private final MemberFacade memberFacade;

  @Transactional(propagation = REQUIRES_NEW)
  @KafkaListener(topics = "PostCreatedEvent", groupId = "MemberEventListener__handle__1")
  public void handle(PostCreatedEvent event) {
    Member member = memberFacade.findById(event.getPost().getMemberId());
    // 게시글 생성 시 활동점수 3점 증가
    member.increaseActivityScore(3);
  }

  @Transactional(propagation = REQUIRES_NEW)
  @KafkaListener(topics = "CommentCreatedEvent", groupId = "MemberEventListener__handle__2")
  public void handle(CommentCreatedEvent event) {
    Member member = memberFacade.findById(event.getComment().getMemberId());
    // 댓글 생성 시 활동 점수 1점 증가
    member.increaseActivityScore(1);
  }
}
