package com.alkemy.ong.repository;

import com.alkemy.ong.entity.SlideEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SlideRepository extends JpaRepository<SlideEntity, String> {

    @Query(value = "SELECT MAX(slide_order) FROM slides", nativeQuery = true)
    Integer findNextMaxSlideOrder();
}
