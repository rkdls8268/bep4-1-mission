package com.back.boundedContext.post.in;


import com.back.boundedContext.post.app.PostFacade;
import com.back.boundedContext.post.domain.Post;
import com.back.boundedContext.post.domain.PostMember;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
@Slf4j
// DataInit 실행되는 시점 -> 빌드 다 되고 초반에 실행
// 실행 초기에 사용할 데이터 미리 생성하는 단계
public class PostDataInit {
  private final PostDataInit self;
  private final PostFacade postFacade;

  // proxy 를 활용해서 생성한 것이라 생각하면 됨
  public PostDataInit(@Lazy PostDataInit self, PostFacade postFacade) {
    this.self = self;
    this.postFacade = postFacade;
  }

  @Bean
  public ApplicationRunner PostDataInitRunner() {
    return args -> {
      self.makeBasePosts();
      self.makeBaseComments();
    };
  }

  @Transactional
  public void makeBasePosts() {
    if (postFacade.count() > 0) return;

    PostMember user1Member = postFacade.findPostMemberByUsername("user1");
    PostMember user2Member = postFacade.findPostMemberByUsername("user2");
    PostMember user3Member = postFacade.findPostMemberByUsername("user3");

    postFacade.create(user1Member, "title1", "content1");
    postFacade.create(user1Member, "title2", "content2");
    postFacade.create(user1Member, "title3", "content3");
    postFacade.create(user2Member, "title4", "content4");
    postFacade.create(user2Member, "title5", "content5");
    postFacade.create(user3Member, "title6", "content6");
  }

  @Transactional
  public void makeBaseComments() {
    PostMember user1Member = postFacade.findPostMemberByUsername("user1");
    PostMember user2Member = postFacade.findPostMemberByUsername("user2");
    PostMember user3Member = postFacade.findPostMemberByUsername("user3");

    Post post1 = postFacade.findByPostId(1);
    Post post2 = postFacade.findByPostId(2);
    Post post3 = postFacade.findByPostId(3);
    Post post4 = postFacade.findByPostId(4);

    if (post1.hasComments()) return;

    post1.addComment(user1Member, "댓글1");
    post1.addComment(user2Member, "댓글2");
    post1.addComment(user3Member, "댓글3");
    post2.addComment(user2Member, "댓글4");
    post2.addComment(user2Member, "댓글5");
    post3.addComment(user3Member, "댓글6");
    post3.addComment(user1Member, "댓글7");
    post4.addComment(user1Member, "댓글8");
  }
}