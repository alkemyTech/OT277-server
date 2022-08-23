package com.alkemy.ong.repository;

import com.alkemy.ong.entity.NewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewRepository extends JpaRepository<NewEntity,String> {
}

