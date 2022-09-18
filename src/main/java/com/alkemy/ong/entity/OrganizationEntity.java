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
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@SQLDelete(sql = "UPDATE organizations SET soft_delete = true WHERE id = ?")
@Where(clause = "soft_delete = false")
@Table(name = "organizations", indexes = @Index(name = "idx_organizations_name", columnList = "name"))
public class OrganizationEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String image;

    private String address;

    private Integer phone;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(columnDefinition = "text", nullable = false)
    private String welcomeText;

    @Column(columnDefinition = "text")
    private String aboutUsText;

    @CreatedDate
    @Column(columnDefinition = "timestamp")
    private Timestamp timestamp;

    @Column(name = "soft_delete")
    private boolean softDelete;

    @Column(name = "url_facebook")
    private String urlFacebook;

    @Column(name = "url_linkedin")
    private String urlLinkedin;

    @Column(name = "url_instagram")
    private String urlInstagram;

    @OneToMany(mappedBy = "organizationEntity")
    private List<SlideEntity> slides;
}
