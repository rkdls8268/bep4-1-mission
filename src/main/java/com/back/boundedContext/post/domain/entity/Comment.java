package com.back.boundedContext.post.domain.entity;

import com.back.boundedContext.member.domain.entity.Member;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import com.back.global.jpa.Entity.BaseIdAndTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Comment extends BaseIdAndTime {

  @ManyToOne(fetch = FetchType.LAZY)
  private Member member;
  @ManyToOne(fetch = FetchType.LAZY)
  private Post post;
  private String content;

  public Comment(Member member, Post post, String content) {
    this.member = member;
    this.post = post;
    this.content = content;
  }
}
