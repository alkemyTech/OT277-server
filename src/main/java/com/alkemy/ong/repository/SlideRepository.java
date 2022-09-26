package com.alkemy.ong.repository;

import com.alkemy.ong.entity.SlideEntity;
import com.alkemy.ong.entity.TestimonialEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SlideRepository extends JpaRepository<SlideEntity, String> {

    @Query(value = "SELECT MAX(slide_order) FROM slides WHERE organization_id LIKE :organizationId", nativeQuery = true)
    Integer findNextMaxSlideOrder(@Param("organizationId") String organizationId);

    @Query(value = "SELECT slide_order FROM slides WHERE organization_id LIKE :organizationId", nativeQuery = true)
    List<Integer> findSlideOrder(@Param("organizationId") String organizationId);

    Optional<SlideEntity> findByIdAndSoftDeleteFalse(String id);

    @Query(value = "SELECT * FROM slides WHERE soft_delete = FALSE", nativeQuery = true)
    List<SlideEntity> getAllAndSoftDelteFalse();

}
