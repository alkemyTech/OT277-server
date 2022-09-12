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
@SQLDelete(sql = "UPDATE members SET soft_delete = true WHERE id = ?")
@Where(clause = "soft_delete = false")
@Table(name = "members", indexes = @Index(name = "idx_members_name", columnList = "name"))
public class MemberEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name="uuid", strategy = "uuid2")
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(name = "facebook_url")
    private String facebookUrl;

    @Column(name ="instagram_url")
    private String instagramUrl;

    @Column(name = "linkedin_url")
    private String linkedinUrl;

    @Column(nullable = false)
    private String image;

    private String description;

    @Column(columnDefinition = "timestamp")
    @CreatedDate
    private Timestamp timestamp;

    @Column(name = "soft_delete")
    private boolean softDelete;


}
