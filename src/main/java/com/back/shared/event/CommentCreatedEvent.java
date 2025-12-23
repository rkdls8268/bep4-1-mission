package com.back.shared.event;

import com.back.shared.dto.CommentDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentCreatedEvent {

  private final CommentDto comment;

}
