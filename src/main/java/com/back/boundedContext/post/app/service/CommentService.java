package com.back.boundedContext.post.app.service;

import com.back.boundedContext.post.domain.entity.Comment;
import com.back.boundedContext.member.domain.entity.Member;
import com.back.boundedContext.post.domain.entity.Post;
import com.back.boundedContext.post.out.repository.CommentRepository;
import com.back.global.eventPublisher.EventPublisher;
import com.back.shared.dto.CommentDto;
import com.back.shared.event.CommentCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

  private final CommentRepository commentRepository;
  private final EventPublisher eventPublisher;

  public long count() {
    return commentRepository.count();
  }

  public Comment create(Member member, Post post, String content) {
    Comment comment = new Comment(member, post, content);
    comment = commentRepository.save(comment);
    eventPublisher.publish(new CommentCreatedEvent(new CommentDto(comment)));
    return comment;
  }
}
