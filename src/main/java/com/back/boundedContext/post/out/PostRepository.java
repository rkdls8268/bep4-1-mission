package com.back.boundedContext.post.out;

import com.back.boundedContext.post.domain.Post;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {

  Optional<Post> findById(int id);

  List<Post> findByOrderByIdDesc();
}
