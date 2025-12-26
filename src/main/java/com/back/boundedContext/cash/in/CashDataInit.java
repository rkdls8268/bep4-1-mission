package com.back.boundedContext.cash.in;

import com.back.boundedContext.cash.app.CashFacade;
import com.back.boundedContext.cash.domain.CashLog.EventType;
import com.back.boundedContext.cash.domain.CashMember;
import com.back.boundedContext.cash.domain.Wallet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.transaction.annotation.Transactional;

@Configuration
@Slf4j
public class CashDataInit {
  private final CashDataInit self;
  private final CashFacade cashFacade;

  public CashDataInit(@Lazy CashDataInit self, CashFacade cashFacade) {
    this.self = self;
    this.cashFacade = cashFacade;
  }

  @Bean
  @Order(2)
  public ApplicationRunner CashDataInitRunner() {
    return args -> {
      self.makeBaseCredits();
    };
  }

  @Transactional
  public void makeBaseCredits() {
    CashMember user1 = cashFacade.findByUserName("user1");
    CashMember user2 = cashFacade.findByUserName("user2");

    Wallet wallet1 = cashFacade.findByHolder(user1);
    if (wallet1.hasBalance()) return;
    wallet1.credit(150000, EventType.충전__무통장입금);
    wallet1.credit(100000, EventType.충전__무통장입금);
    wallet1.credit(50000, EventType.충전__무통장입금);

    Wallet wallet2 = cashFacade.findByHolder(user2);
    if (wallet2.hasBalance()) return;
    wallet2.credit(150000, EventType.충전__무통장입금);
  }
}
