package model;

import base.entity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@SuperBuilder
@Entity

public class Admin extends BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false, unique = true)
    @Size(min = 5, max = 50, message = "Username must be between 5 and 50 characters")
    String username;

    @Column(nullable = false)
    @Size(min = 8, message = "Password must be at least 8 characters long")
    String password;

//    @ManyToMany
//    @JoinTable(
//            name = "admin_roles",
//            joinColumns = @JoinColumn(name = "admin_id"),
//            inverseJoinColumns = @JoinColumn(name = "role_id")
//    )
//     Set<Role> roles = new HashSet<>();

    public Admin(Long id, String username) {
        this.id = id;
        this.username = username;
    }
}
