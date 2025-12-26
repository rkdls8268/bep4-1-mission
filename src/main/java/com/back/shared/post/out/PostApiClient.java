package com.back.shared.post.out;

import com.back.shared.post.dto.PostDto;
import java.util.List;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class PostApiClient {
  private static final RestClient restClient = RestClient.builder()
    .baseUrl("http://localhost:8080/post/api/v1")
    .build();

  public List<PostDto> getPosts() {
    return restClient.get()
      .uri("/posts")
      .retrieve()
      .body(new ParameterizedTypeReference<>() {
      });
  }

  public PostDto getPost(int id) {
    return restClient.get()
      .uri("/posts/%d".formatted(id))
      .retrieve()
      .body(new ParameterizedTypeReference<>() {
      });
  }
}
