package com.back.boundedContext.market.in;

import com.back.boundedContext.post.app.PostFacade;
import com.back.shared.post.dto.PostDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/post/api/v1")
@RequiredArgsConstructor
public class ApiV1PostController {

  private final PostFacade postFacade;

  @GetMapping("/posts")
  @Transactional(readOnly = true)
  public List<PostDto> getPosts() {
    return postFacade.findByOrderByIdDesc()
      .stream()
      .map(PostDto::new)
      .toList();
  }

  @GetMapping("/posts/{id}")
  @Transactional(readOnly = true)
  public PostDto getPost(
    @PathVariable int id
  ) {
    return new PostDto(postFacade.findByPostId(id));
  }
}
