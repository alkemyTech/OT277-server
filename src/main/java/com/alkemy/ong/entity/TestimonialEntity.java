package com.alkemy.ong.entity;

import com.sun.istack.NotNull;
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
@SQLDelete(sql = "UPDATE testimonials SET delete = true WHERE id = ?")
@Where(clause = "delete = false")
@Table(name = "testimonials")
@EntityListeners(AuditingEntityListener.class)

public class TestimonialEntity {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @NotNull
    private String name;

    @Column(name = "image")
    private String image;

    @Column(name = "content")
    private String content;

    @CreatedDate
    @Column(columnDefinition = "timestamp")
    private Timestamp timestamp;

    private boolean softDelete;
}
