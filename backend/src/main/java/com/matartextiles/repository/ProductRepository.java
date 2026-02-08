package com.matartextiles.repository;

import com.matartextiles.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findBySlug(String slug);

    Page<Product> findByActiveTrue(Pageable pageable);

    Page<Product> findByCategorySlugAndActiveTrue(String categorySlug, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.active = true AND " +
           "(LOWER(p.nameFr) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(p.nameEn) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(p.descriptionFr) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(p.descriptionEn) LIKE LOWER(CONCAT('%', :search, '%')))")
    Page<Product> searchProducts(@Param("search") String search, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.active = true AND p.category.slug = :categorySlug AND " +
           "(LOWER(p.nameFr) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(p.nameEn) LIKE LOWER(CONCAT('%', :search, '%')))")
    Page<Product> searchProductsByCategory(@Param("categorySlug") String categorySlug, @Param("search") String search, Pageable pageable);
}
