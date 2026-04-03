package com.tapash.repository;

import com.tapash.entity.product.ProductCake;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface ProductCakeRepository extends JpaRepository<ProductCake , UUID> {
    Optional<ProductCake>findByCakeName(String name);
}
