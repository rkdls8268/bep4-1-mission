package com.back.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jpa.Entity.BaseIdAndTime;
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
