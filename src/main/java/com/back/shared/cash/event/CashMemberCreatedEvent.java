package com.back.shared.cash.event;

import com.back.shared.cash.dto.CashMemberDto;
import com.back.standard.event.HaveEventName;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CashMemberCreatedEvent implements HaveEventName {
  private final CashMemberDto cashMemberDto;
}
