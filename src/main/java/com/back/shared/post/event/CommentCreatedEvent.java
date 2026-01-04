package com.back.shared.post.event;

import com.back.shared.post.dto.CommentDto;
import com.back.standard.event.HaveEventName;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentCreatedEvent implements HaveEventName {

  private final CommentDto comment;

}
