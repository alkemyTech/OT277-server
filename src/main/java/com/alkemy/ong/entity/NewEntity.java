package com.alkemy.ong.entity;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.security.Timestamp;

@Entity
@Getter
@Setter
@SQLDelete(sql = "UPDATE news SET softDelete= true WHERE id=?")
@Where(clause = "softDelete=false")
@Table(name = "news")
public class NewEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, columnDefinition = "text")
    private String content;

    @Column(nullable = false)
    private String image;


    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "categoryId",nullable = false)
    private CategoryEntity categoryEntity;

    @Column(columnDefinition = "timestamp")
    private Timestamp timestamps;

    @Column(nullable = false)
    private String type;

    private boolean softDelete = Boolean.FALSE;

}