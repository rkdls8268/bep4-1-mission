package com.back.initData;

import com.back.entity.Member;
import com.back.service.MemberService;
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
  private final MemberService memberService;

  // proxy 를 활용해서 생성한 것이라 생각하면 됨
  public DataInit(@Lazy DataInit self, MemberService memberService) {
    this.self = self;
    this.memberService = memberService;
  }

  @Bean
  public ApplicationRunner baseInitDataRunner() {
    return args -> {
      self.makeBaseMembers();
    };
  }

  @Transactional
  public void makeBaseMembers() {
    if (memberService.count() > 0) return;

    Member systemMember = memberService.join("system", "1234", "시스템");
    Member holdingMember = memberService.join("holding", "1234", "홀딩");
    Member adminMember = memberService.join("admin", "1234", "관리자");
    Member user1Member = memberService.join("user1", "1234", "유저1");
    Member user2Member = memberService.join("user2", "1234", "유저2");
    Member user3Member = memberService.join("user3", "1234", "유저3");
  }
}