package com.back.boundedContext.post.repository;

import com.back.boundedContext.post.entity.Post;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {

  Optional<Post> findById(int id);

}
