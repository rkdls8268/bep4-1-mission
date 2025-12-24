package com.back.shared.member.domain;

import static lombok.AccessLevel.PROTECTED;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter(value = PROTECTED)
@NoArgsConstructor
public abstract class ReplicaMember extends BaseMember {
  @Id
  private int id;
  private LocalDateTime createDate;
  private LocalDateTime modifyDate;

  public ReplicaMember(String username, String password, String nickname) {
    super(username, password, nickname);
  }
}