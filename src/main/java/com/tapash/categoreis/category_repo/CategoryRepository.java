package com.tapash.categoreis.category_repo;

import com.tapash.entity.category.CakeCategory;
import com.tapash.entity.category.CategoryStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface CategoryRepository extends JpaRepository<CakeCategory, UUID> ,
        JpaSpecificationExecutor<CakeCategory> {
    Optional<CakeCategory>findByCategoryName(String category);
    Optional<CakeCategory>findByStatus(CategoryStatus status);

}
