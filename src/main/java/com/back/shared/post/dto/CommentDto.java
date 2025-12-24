package com.back.shared.post.dto;

import com.back.boundedContext.post.domain.Comment;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CommentDto {
  private final int id;
  private final LocalDateTime createDate;
  private final LocalDateTime modifyDate;
  private final int memberId;
  private final int postId;
  private final String userName;
  private final String content;

  public CommentDto(Comment comment) {
    this(
      comment.getId(),
      comment.getCreateDate(),
      comment.getModifyDate(),
      comment.getMember().getId(),
      comment.getPost().getId(),
      comment.getMember().getNickname(),
      comment.getContent()
    );
  }
}


