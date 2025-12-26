package com.back.boundedContext.post.app;

import com.back.boundedContext.post.domain.Post;
import com.back.boundedContext.post.domain.PostMember;
import com.back.global.rsData.RsData;
import com.back.shared.member.dto.MemberDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostFacade {

  private final PostSupport postSupport;
  private final PostCreateUseCase postCreateUseCase;
  private final PostSyncMemberUseCase postSyncMemberUseCase;

  @Transactional(readOnly = true)
  public long count() {
    return postSupport.count();
  }

  @Transactional(readOnly = true)
  public RsData<Post> create(PostMember member, String title, String content) {
    return postCreateUseCase.create(member, title, content);
  }

  @Transactional(readOnly = true)
  public Post findByPostId(int postId) {
    return postSupport.findByPostId(postId);
  }

  @Transactional
  public void syncMember(MemberDto member) {
    postSyncMemberUseCase.syncMember(member);
  }

  @Transactional(readOnly = true)
  public PostMember findMemberByUsername(String username) {
    return postSupport.findMemberByUsername(username);
  }

  @Transactional(readOnly = true)
  public List<Post> findByOrderByIdDesc() {
    return postSupport.findByOrderByIdDesc();
  }
}
