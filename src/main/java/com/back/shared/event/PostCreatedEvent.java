package com.back.shared.event;


import com.back.shared.dto.PostDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostCreatedEvent {
  private final PostDto post;
}