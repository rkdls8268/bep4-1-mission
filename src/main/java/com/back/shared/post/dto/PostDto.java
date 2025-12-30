package com.back.shared.post.dto;


import com.back.standard.modelType.CanGetModelTypeCode;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 기본 생성자가 존재하지 않아 Json -> java 객체 역직렬화 시 문제 발생
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PostDto implements CanGetModelTypeCode {
  private int id;
  private LocalDateTime createDate;
  private LocalDateTime modifyDate;
  private int memberId;
  private String userName;
  private String title;
  private String content;

  @Override
  public String getModelTypeCode() {
    return "Post";
  }
}