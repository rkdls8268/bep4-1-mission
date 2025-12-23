package com.back.boundedContext.member.eventListener;

import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;
import com.back.boundedContext.member.entity.Member;
import com.back.boundedContext.member.service.MemberService;
import com.back.shared.event.CommentCreatedEvent;
import com.back.shared.event.PostCreatedEvent;
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
public class MemberEventListener {
  private final MemberService memberService;

  @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
  @Transactional(propagation = REQUIRES_NEW)
  public void handle(PostCreatedEvent event) {
    Member member = memberService.findById(event.getPost().getMemberId());
    // 게시글 생성 시 활동점수 3점 증가
    member.increaseActivityScore(3);
  }

  @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
  @Transactional(propagation = REQUIRES_NEW)
  public void handle(CommentCreatedEvent event) {
    Member member = memberService.findById(event.getComment().getMemberId());
    // 댓글 생성 시 활동 점수 1점 증가
    member.increaseActivityScore(1);
  }
}
