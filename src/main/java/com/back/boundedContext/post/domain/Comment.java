package com.back.boundedContext.post.domain;

import com.back.global.jpa.Entity.BaseIdAndTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "POST_COMMENT")
@NoArgsConstructor
@Getter
public class Comment extends BaseIdAndTime {

  @ManyToOne(fetch = FetchType.LAZY)
  private PostMember member;
  @ManyToOne(fetch = FetchType.LAZY)
  private Post post;
  @Column(columnDefinition = "TEXT")
  private String content;

  public Comment(PostMember member, Post post, String content) {
    this.member = member;
    this.post = post;
    this.content = content;
  }
}
