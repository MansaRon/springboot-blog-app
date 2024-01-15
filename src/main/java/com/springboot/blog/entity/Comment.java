package com.springboot.blog.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * @author Thendo
 * @date 2024/01/06
 */
@Getter
@Setter
@Entity
@Table(name = "comments")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    /**
     * Unique ID for database referencing.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * The content of the comment
     */
    @Column(name = "body")
    private String body;

    /**
     * The email of the user commenting
     */
    @Column(name = "email")
    private String email;

    /**
     * The name of the person who is commenting
     */
    @Column(name = "name")
    private String name;

    /**
     * Post entity mapped to comment class
     */
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;
}
