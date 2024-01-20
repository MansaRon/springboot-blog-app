package com.springboot.blog.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Set;

/**
 * @author Thendo
 * @date 2024/01/18
 */
@Getter
@Setter
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User {

    /**
     * Unique ID for database referencing.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * ID Name registered by user.
     */
    private String name;

    /**
     * Username to be used for login.
     */
    @Column(nullable = false, unique = true)
    private String username;

    /**
     * User to be used for login.
     */
    @Column(nullable = false, unique = true)
    private String email;

    /**
     * Password used for the user.
     */
    @Column(nullable = false)
    private String password;

    /**
     * Status for the user account.
     */
    @NotNull
    @Column(nullable = false)
    private AccountStatus status;

    /**
     * Roles set for the user.
     */
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id",
                    referencedColumnName = "id")
    )
    private Set<Role> roles;
}
