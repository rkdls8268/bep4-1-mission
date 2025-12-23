package com.back.boundedContext.post.app;

import com.back.boundedContext.member.domain.Member;
import com.back.boundedContext.post.domain.Post;
import com.back.boundedContext.post.out.PostRepository;
import com.back.global.exception.DomainException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostFacade {

  private final PostCreateUseCase postCreateUseCase;
  private final PostRepository postRepository;

  @Transactional(readOnly = true)
  public long count() {
    return postRepository.count();
  }

  @Transactional(readOnly = true)
  public Post create(Member member, String title, String content) {
    return postCreateUseCase.create(member, title, content);
  }

  @Transactional(readOnly = true)
  public Post findByPostId(int postId) {
    return postRepository.findById(postId)
      .orElseThrow(() -> new DomainException("409-3", "존재하지 않는 post 입니다."));
  }
}
