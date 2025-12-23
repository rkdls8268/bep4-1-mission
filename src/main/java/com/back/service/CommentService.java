package com.back.service;

import com.back.entity.Comment;
import com.back.entity.Member;
import com.back.entity.Post;
import com.back.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

  private final CommentRepository commentRepository;

  public long count() {
    return commentRepository.count();
  }

  public Comment create(Member member, Post post, String content) {
    Comment comment = new Comment(member, post, content);
    // 댓글 생성 시 활동 점수 1점 증가
    member.increaseActivityScore(1);
    return commentRepository.save(comment);
  }
}
