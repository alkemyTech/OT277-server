package com.alkemy.ong.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@SQLDelete(sql = "UPDATE contacts SET deletedAt = true WHERE id = ?")
@Where(clause = "deletedAt = false")
@Table(name = "contacts", indexes = @Index(name = "idx_contacts_email", columnList = "email"))
public class ContactEntity {

        @Id
        @GeneratedValue(generator = "uuid")
        @GenericGenerator(name = "uuid", strategy = "uuid2")
        private String id;

        @Column(name = "name", nullable = false)
        private String name;

        @Column(name = "phone")
        private String phone;

        @Column(name = "email", nullable = false)
        private String email;

        @Column(name = "message")
        private String message;

        @CreatedDate
        @Column(columnDefinition = "timestamp")
        private Timestamp timestamps;

        @Column(name = "deletedAt")
        private boolean deletedAt;

    }

