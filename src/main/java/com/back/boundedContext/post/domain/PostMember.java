package com.back.boundedContext.post.domain;

import com.back.shared.member.domain.ReplicaMember;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "POST_MEMBER")
@NoArgsConstructor
@Getter
public class PostMember extends ReplicaMember {
  public PostMember(
    int id, LocalDateTime createDate, LocalDateTime modifyDate,
    String username, String password, String nickname, int activityScore
  ) {
    super(id, createDate, modifyDate, username, password, nickname, activityScore);
  }
}
