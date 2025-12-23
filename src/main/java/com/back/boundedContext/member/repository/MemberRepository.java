package com.back.boundedContext.member.repository;

import com.back.boundedContext.member.entity.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer> {
  Optional<Member> findByUsername(String username);

  Optional<Member> findById(int id);
}
