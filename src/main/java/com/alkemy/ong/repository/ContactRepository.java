package com.alkemy.ong.repository;

import com.alkemy.ong.entity.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<ContactEntity, String> {


}
