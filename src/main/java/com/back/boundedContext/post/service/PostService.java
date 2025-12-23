package com.back.boundedContext.post.service;

import com.back.boundedContext.member.entity.Member;
import com.back.boundedContext.post.entity.Post;
import com.back.global.eventPublisher.EventPublisher;
import com.back.global.exception.DomainException;
import com.back.boundedContext.post.repository.PostRepository;
import com.back.shared.dto.PostDto;
import com.back.shared.event.PostCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

  private final PostRepository postRepository;
  private final EventPublisher eventPublisher;

  public long count() {
    return postRepository.count();
  }

  public Post create(Member member, String title, String content) {
    Post post = new Post(member, title, content);
    post = postRepository.save(post);
    eventPublisher.publish(new PostCreatedEvent(new PostDto(post)));
    return post;
  }

  public Post findByPostId(int postId) {
    return postRepository.findById(postId)
      .orElseThrow(() -> new DomainException("409-3", "존재하지 않는 post 입니다."));
  }
}
