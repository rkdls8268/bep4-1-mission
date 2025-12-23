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
    Post post = new Post(member, title, content);
    // 게시글 생성 시 활동점수 3점 증가
    member.increaseActivityScore(3);
    return postRepository.save(post);
  }

  public Post findByPostId(int postId) {
    return postRepository.findById(postId)
      .orElseThrow(() -> new DomainException("409-3", "존재하지 않는 post 입니다."));
  }
}
