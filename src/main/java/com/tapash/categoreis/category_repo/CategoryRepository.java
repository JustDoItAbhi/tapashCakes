package com.tapash.categoreis.category_repo;

import com.tapash.entity.CakeCategory;
import com.tapash.entity.CategoryStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface CategoryRepository extends JpaRepository<CakeCategory, UUID> {
    Optional<CakeCategory>findByCategoryName(String category);
    Optional<CakeCategory>findByStatus(CategoryStatus status);

}
