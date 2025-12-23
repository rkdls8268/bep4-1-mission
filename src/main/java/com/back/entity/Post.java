package com.back.entity;

import static jakarta.persistence.FetchType.LAZY;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jpa.Entity.BaseIdAndTime;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Post extends BaseIdAndTime {
  @ManyToOne(fetch = LAZY)
  private Member member;
  private String title;
  @Column(columnDefinition = "LONGTEXT")
  private String content;

  public Post(Member member, String title, String content) {
    this.member = member;
    this.title = title;
    this.content = content;
  }
}
