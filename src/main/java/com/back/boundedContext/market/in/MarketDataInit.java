package com.back.boundedContext.market.in;

import com.back.boundedContext.market.app.MarketFacade;
import com.back.shared.post.out.PostApiClient;
import jakarta.transaction.Transactional;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;

@Configuration
public class MarketDataInit {
  private final MarketDataInit self;
  private final MarketFacade marketFacade;
  private final PostApiClient postApiClient;

  // proxy 를 활용해서 생성한 것이라 생각하면 됨
  public MarketDataInit(
    @Lazy MarketDataInit self,
    MarketFacade marketFacade,
    PostApiClient postApiClient
  ) {
    this.self = self;
    this.marketFacade = marketFacade;
    this.postApiClient = postApiClient;
  }

  @Bean
  @Order(3)
  public ApplicationRunner MarketDataInitRunner() {
    return args -> {
      self.makeBaseMarkets();
    };
  }

  @Transactional
  public void makeBaseMarkets() {
    postApiClient.getPosts()
      .forEach(it -> System.out.println("post.getId() : " + it.getId()));
  }
}
