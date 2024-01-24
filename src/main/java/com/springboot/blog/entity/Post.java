package com.springboot.blog.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Thendo
 * @date 2024/01/06
 */
@Getter
@Setter
@Entity
@Table(name = "posts")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    /**
     * Unique ID for database referencing.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * Name of the post
     */
    @Column(name = "title", nullable = false)
    private String title;

    /**
     * A description of the post
     */
    @Column(name = "description")
    private String description;

    /**
     * The content/details of the post
     */
    @Column(name = "content")
    private String content;

    /**
     * Comments list
     */
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Comment> comments = new HashSet<>();

    /**
     * Category of post
     */
    @JoinColumn(name = "category_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;
}
