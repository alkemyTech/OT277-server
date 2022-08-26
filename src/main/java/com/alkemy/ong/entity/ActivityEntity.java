package com.alkemy.ong.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "activities", indexes = @Index(name = "idx_activities_name", columnList = "activity"))
@Entity
public class ActivityEntity {


    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name="uuid", strategy = "uuid2")
    private String id;

    @Column(name = "activity", nullable = false)
    private String name;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false, columnDefinition = "timestamp")
    private Timestamp timestamp;

    @Column(name = "soft_delete")
    private boolean softDelete;






}
