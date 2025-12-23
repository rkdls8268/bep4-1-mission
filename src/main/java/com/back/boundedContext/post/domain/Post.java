package com.back.boundedContext.post.domain;

import static jakarta.persistence.FetchType.LAZY;
import com.back.boundedContext.member.domain.Member;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import com.back.global.jpa.Entity.BaseIdAndTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Post extends BaseIdAndTime {
  @ManyToOne(fetch = LAZY)
  private Member member;
  private String title;
  @Column(columnDefinition = "LONGTEXT")
  private String content;
  @OneToMany(mappedBy = "post", cascade = {CascadeType.PERSIST,
    CascadeType.REMOVE}, orphanRemoval = true)
  private List<Comment> comments = new ArrayList<>();

  public Post(Member member, String title, String content) {
    this.member = member;
    this.title = title;
    this.content = content;
  }

  // entity 내부 메서드 케이스
  /**
   * 엔티티에 로직을 두는 경우: 하나의 aggregate root 안에서 끝나는 규칙 및 상태 전이인 경우 선호
   * 서비스에 로직을 두는 경우: 여러 aggregate 및 외부 시스템을 아우르거나 트랜잭션 관리가 필요한 케이스인 경우 선호.
   */
//  public Comment addComment(Member member, String conten) {
//    Comment comment = new Comment(member, this, content);
//    comments.add(comment);
//    return comment;
//  }
//
//  public boolean hasComments() {
//    return !comments.isEmpty();
//  }
}
