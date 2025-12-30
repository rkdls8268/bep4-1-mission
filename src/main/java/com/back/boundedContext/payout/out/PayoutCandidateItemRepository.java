package com.back.boundedContext.payout.out;

import com.back.boundedContext.payout.domain.PayoutCandidateItem;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PayoutCandidateItemRepository extends JpaRepository<PayoutCandidateItem, Integer> {

  List<PayoutCandidateItem> findByPayoutItemIsNullAndPaymentDateBeforeOrderByPayeeAscIdAsc(LocalDateTime paymentDate, Pageable pageable);
}
