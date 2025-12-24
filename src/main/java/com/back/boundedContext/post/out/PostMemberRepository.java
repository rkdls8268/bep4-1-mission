package com.back.boundedContext.post.out;

import com.back.boundedContext.post.domain.PostMember;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostMemberRepository extends JpaRepository<PostMember, Integer> {

  Optional<PostMember> findByUsername(String username);
}
