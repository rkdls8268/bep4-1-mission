package com.back.boundedContext.payout.out;

import com.back.boundedContext.payout.domain.Payout;
import com.back.boundedContext.payout.domain.PayoutMember;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PayoutRepository extends JpaRepository<Payout, Integer> {

  Optional<Payout> findByPayeeAndPayoutDateIsNull(PayoutMember payee);
}
