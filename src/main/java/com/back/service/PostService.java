package com.back.service;

import com.back.entity.Member;
import com.back.entity.Post;
import com.back.exception.DomainException;
import com.back.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

  private final PostRepository postRepository;

  public long count() {
    return postRepository.count();
  }

  public Post create(Member member, String title, String content) {
    return postRepository.save(new Post(member, title, content));
  }

  public Post findByPostId(int postId) {
    return postRepository.findById(postId)
      .orElseThrow(() -> new DomainException("409-3", "존재하지 않는 post 입니다."));
  }
}
