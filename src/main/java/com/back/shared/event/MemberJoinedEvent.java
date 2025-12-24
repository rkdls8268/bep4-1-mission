package com.back.shared.event;

import com.back.shared.dto.MemberDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberJoinedEvent {
  private final MemberDto member;
}
