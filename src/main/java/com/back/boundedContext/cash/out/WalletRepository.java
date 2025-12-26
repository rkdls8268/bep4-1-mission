package com.back.boundedContext.cash.out;

import com.back.boundedContext.cash.domain.Wallet;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Integer> {

  Optional<Wallet> findByHolderId(Integer holderId);
}
