package com.back.shared.post.event;


import com.back.shared.post.dto.PostDto;
import com.back.standard.event.HaveEventName;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostCreatedEvent implements HaveEventName {
  private final PostDto post;
}