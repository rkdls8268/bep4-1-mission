package com.back.global.initData;

import com.back.boundedContext.post.domain.Comment;
import com.back.boundedContext.member.domain.Member;
import com.back.boundedContext.post.domain.Post;
import com.back.boundedContext.post.app.CommentService;
import com.back.boundedContext.member.app.MemberFacade;
import com.back.boundedContext.post.app.PostService;
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
  private final PostService postService;
  private final CommentService commentService;

  // proxy 를 활용해서 생성한 것이라 생각하면 됨
  public DataInit(@Lazy DataInit self, MemberFacade memberFacade, PostService postService, CommentService commentService) {
    this.self = self;
    this.memberFacade = memberFacade;
    this.postService = postService;
    this.commentService = commentService;
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
    if (postService.count() > 0) return;

    Member user1Member = memberFacade.findByUsername("user1");
    Member user2Member = memberFacade.findByUsername("user2");
    Member user3Member = memberFacade.findByUsername("user3");

    Post post1 = postService.create(user1Member, "title1", "content1");
    Post post2 = postService.create(user1Member, "title2", "content2");
    Post post3 = postService.create(user1Member, "title3", "content3");
    Post post4 = postService.create(user2Member, "title4", "content4");
    Post post5 = postService.create(user2Member, "title5", "content5");
    Post post6 = postService.create(user3Member, "title6", "content6");
  }

  @Transactional
  public void makeBaseComments() {
    if (commentService.count() > 0) return;

    Member user1Member = memberFacade.findByUsername("user1");
    Member user2Member = memberFacade.findByUsername("user2");
    Member user3Member = memberFacade.findByUsername("user3");

    Post post1 = postService.findByPostId(1);
    Post post2 = postService.findByPostId(2);
    Post post3 = postService.findByPostId(3);
    Post post4 = postService.findByPostId(4);

    Comment comment1 = commentService.create(user1Member, post1, "댓글1");
    Comment comment2 = commentService.create(user2Member, post1, "댓글2");
    Comment comment3 = commentService.create(user3Member, post1, "댓글3");
    Comment comment4 = commentService.create(user2Member, post2, "댓글4");
    Comment comment5 = commentService.create(user2Member, post2, "댓글5");
    Comment comment6 = commentService.create(user3Member, post3, "댓글6");
    Comment comment7 = commentService.create(user1Member, post3, "댓글7");
    Comment comment8 = commentService.create(user1Member, post4, "댓글8");
  }
}