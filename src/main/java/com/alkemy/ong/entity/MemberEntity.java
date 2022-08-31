package com.alkemy.ong.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
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
@Table(name = "members", indexes = @Index(name = "idx_members_name", columnList = "name"))
public class MemberEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name="uuid", strategy = "uuid2")
    private String id;

    @Column(name = "member",nullable = false)
    private String name;

    @Column(name = "facebook_url", nullable = false)
    private String facebookUrl;

    @Column(name ="instagram_url", nullable = false)
    private String instagramUrl;

    @Column(name = "linkedin_url", nullable = false)
    private String linkedinUrl;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private String description;

    @Column(columnDefinition = "timestamp")
    @CreatedDate
    private Timestamp timestamp;

    @Column(name = "soft_delete")
    private boolean softDelete;


}
