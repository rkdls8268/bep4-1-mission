package com.back.boundedContext.cash.out;

import com.back.boundedContext.cash.domain.CashMember;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CashMemberRepository extends JpaRepository<CashMember, Integer> {

  Optional<CashMember> findByUsername(String username);
}
