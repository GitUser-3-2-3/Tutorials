package com.parth.booknetwork.role;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.parth.booknetwork.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Role {

   @Id
   @GeneratedValue
   private Integer id;

   @Column(unique = true)
   private String name;

   @ManyToMany(mappedBy = "roles")
   @JsonManagedReference
   private Set<User> users;

   @CreatedDate
   @Column(nullable = false, updatable = false)
   private LocalDateTime createdDate;

   @LastModifiedDate
   @Column(insertable = false)
   private LocalDateTime lastModifiedDate;
}
