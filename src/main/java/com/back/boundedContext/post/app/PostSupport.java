package com.back.boundedContext.post.app;

import com.back.boundedContext.post.domain.Post;
import com.back.boundedContext.post.domain.PostMember;
import com.back.boundedContext.post.out.PostMemberRepository;
import com.back.boundedContext.post.out.PostRepository;
import com.back.global.exception.DomainException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostSupport {

  private final PostRepository postRepository;
  private final PostMemberRepository postMemberRepository;

  public long count() {
    return postRepository.count();
  }

  public Post findByPostId(int postId) {
    return postRepository.findById(postId)
      .orElseThrow(() -> new DomainException("409-2", "존재하지 않는 post 입니다."));
  }

  public PostMember findMemberByUsername(String username) {
    return postMemberRepository.findByUsername(username)
      .orElseThrow(() -> new DomainException("409-2", "존재하지 않는 회원입니다."));
  }
}
