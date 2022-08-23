package com.alkemy.ong.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "slides")
public class SlideEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String imageUrl;

    @Column(columnDefinition = "text")
    private String text;

    @Column(name = "slideOrder")
    private String slideOrder;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "organizationId",insertable = false,updatable = false)
    private OrganizationEntity organizationEntity;

    @Column(name = "organizationId",nullable = false)
    private String organizationId;

}
