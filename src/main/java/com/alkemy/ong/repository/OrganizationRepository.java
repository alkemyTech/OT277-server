package com.alkemy.ong.repository;

import com.alkemy.ong.entity.OrganizationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface OrganizationRepository extends JpaRepository<OrganizationEntity, String> {
    @Query(value = "SELECT * FROM organizations WHERE email LIKE :email", nativeQuery = true)
    Optional<OrganizationEntity> findByEmail(@Param("email")String email);
}
