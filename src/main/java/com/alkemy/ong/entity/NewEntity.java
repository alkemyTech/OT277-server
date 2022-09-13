package com.alkemy.ong.entity;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@SQLDelete(sql = "UPDATE news SET soft_delete= true WHERE id=?")
@Where(clause = "soft_delete=false")
@Table(name = "news")
public class NewEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "text")
    private String content;

    @Column(nullable = false)
    private String image;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id",nullable = false)
    private CategoryEntity categoryEntity;

    @CreationTimestamp
    @Column(columnDefinition = "timestamp")
    private Timestamp timestamps;

    @Column(nullable = false)
    private String type;

    private boolean softDelete = Boolean.FALSE;

    @OneToMany(mappedBy = "newEntity", cascade = CascadeType.ALL)
    private Set<CommentEntity> comments = new HashSet<>();

}