package com.back.boundedContext.post.out.repository;

import com.back.boundedContext.post.domain.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
