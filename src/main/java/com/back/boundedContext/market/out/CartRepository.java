package com.back.boundedContext.market.out;

import com.back.boundedContext.market.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Integer> {

}
