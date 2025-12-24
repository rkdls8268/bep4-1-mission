package com.back.global.initData;

import com.back.boundedContext.member.app.MemberFacade;
import com.back.boundedContext.member.domain.Member;
import com.back.boundedContext.post.app.PostFacade;
import com.back.boundedContext.post.domain.Post;
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
public class DataInit {
  private final DataInit self;
  private final MemberFacade memberFacade;
  private final PostFacade postFacade;

  // proxy 를 활용해서 생성한 것이라 생각하면 됨
  public DataInit(@Lazy DataInit self, MemberFacade memberFacade, PostFacade postFacade) {
    this.self = self;
    this.memberFacade = memberFacade;
    this.postFacade = postFacade;
  }

  @Bean
  public ApplicationRunner baseInitDataRunner() {
    return args -> {
      self.makeBaseMembers();
      self.makeBasePosts();
      self.makeBaseComments();
    };
  }

  @Transactional
  public void makeBaseMembers() {
    if (memberFacade.count() > 0) return;

    Member systemMember = memberFacade.join("system", "1234", "시스템");
    Member holdingMember = memberFacade.join("holding", "1234", "홀딩");
    Member adminMember = memberFacade.join("admin", "1234", "관리자");
    Member user1Member = memberFacade.join("user1", "1234", "유저1");
    Member user2Member = memberFacade.join("user2", "1234", "유저2");
    Member user3Member = memberFacade.join("user3", "1234", "유저3");
  }

  @Transactional
  public void makeBasePosts() {
    if (postFacade.count() > 0) return;

    Member user1Member = memberFacade.findByUsername("user1");
    Member user2Member = memberFacade.findByUsername("user2");
    Member user3Member = memberFacade.findByUsername("user3");

    Post post1 = postFacade.create(user1Member, "title1", "content1").getData();
    Post post2 = postFacade.create(user1Member, "title2", "content2").getData();
    Post post3 = postFacade.create(user1Member, "title3", "content3").getData();
    Post post4 = postFacade.create(user2Member, "title4", "content4").getData();
    Post post5 = postFacade.create(user2Member, "title5", "content5").getData();
    Post post6 = postFacade.create(user3Member, "title6", "content6").getData();
  }

  @Transactional
  public void makeBaseComments() {
    Member user1Member = memberFacade.findByUsername("user1");
    Member user2Member = memberFacade.findByUsername("user2");
    Member user3Member = memberFacade.findByUsername("user3");

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