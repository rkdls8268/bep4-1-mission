package com.back.boundedContext.post.entity;

import com.back.boundedContext.member.entity.Member;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import com.back.global.jpa.Entity.BaseIdAndTime;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Comment extends BaseIdAndTime {

  @ManyToOne
  private Member member;
  @ManyToOne
  private Post post;
  private String content;

  public Comment(Member member, Post post, String content) {
    this.member = member;
    this.post = post;
    this.content = content;
  }
}
