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
    return commentRepository.save(new Comment(member, post, content));
  }
}
