package com.back.service;

import com.back.entity.Member;
import com.back.entity.Post;
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
}
