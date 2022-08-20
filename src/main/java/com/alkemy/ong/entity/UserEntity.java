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
@SQLDelete(sql = "UPDATE organizations SET soft_delete = true WHERE id = ?")
@Where(clause = "soft_delete = false")
@Table(name = "users", indexes = @Index(name = "idx_users_email", columnList = "email"))
public class UserEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private String photo;

    @CreatedDate
    @Column(nullable = false, columnDefinition = "timestamp")
    private Timestamp timestamps;

    @Column(name = "soft_delete")
    private boolean softDelete;

//    @Column(name = "ROLE_ID", nullable = false)
//    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
//    private List<Role> roles;
}
