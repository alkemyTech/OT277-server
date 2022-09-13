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

@Entity(name = "comments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@SQLDelete(sql = "UPDATE comments SET soft_delete = true WHERE id = ?")
@Where(clause = "soft_delete = false")
public class CommentEntity {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(nullable = false)
    private String content;

    @ManyToOne(optional = false)
    private NewEntity newEntity;

    @ManyToOne(optional = false)
    private UserEntity userEntity;

    @Column(columnDefinition = "timestamp")
    @CreatedDate
    private Timestamp timestamp;

    @Column(name = "soft_delete")
    private boolean softDelete;
}
