package com.alkemy.ong.repository;

import com.alkemy.ong.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<CommentEntity, String> {
}
