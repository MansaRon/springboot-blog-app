package com.springboot.blog.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author Thendo
 * @date 2024/01/06
 */
@Data
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
}
