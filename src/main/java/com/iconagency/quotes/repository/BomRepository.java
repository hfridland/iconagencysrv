package com.iconagency.quotes.repository;

import com.iconagency.quotes.entity.BOM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface BomRepository extends JpaRepository<BOM, String> {
    @Query(value = "SELECT DISTINCT category FROM bom ORDER BY category", nativeQuery = true)
    Collection<String> findAllCategories();

    @Query(value = "SELECT * FROM bom WHERE category = ?1", nativeQuery = true)
    List<BOM> findAllByCategoryList(String category);

    @Query(value = "SELECT * FROM bom WHERE `part#` = ?1", nativeQuery = true)
    Optional<BOM> findBomByPart(String part);


}
