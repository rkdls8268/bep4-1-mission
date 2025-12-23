package com.back.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jpa.Entity.BaseIdAndTime;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Member extends BaseIdAndTime {
  @Column(unique = true)
  private String username;
  private String password;
  private String nickname;

  public Member(String username, String password, String nickname) {
    this.username = username;
    this.password = password;
    this.nickname = nickname;
  }
}
