package com.back.boundedContext.member.out;

import com.back.boundedContext.member.domain.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer> {
  Optional<Member> findByUsername(String username);

  Optional<Member> findById(int id);
}
