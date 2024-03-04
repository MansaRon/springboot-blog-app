package com.springboot.blog.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Thendo
 * @date 2024/01/24
 */
@Getter
@Setter
@Entity
@Table(name = "category")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    /**
     * Unique ID for database referencing.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * The name of the category
     */
    @Column(name = "name")
    private String name;

    /**
     * The description of category
     */
    @Column(name = "description")
    private String description;

    /**
     * The mapping of posts on category
     */
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> posts;

    /**
     * Timestamp for date creation
     */
    @CreationTimestamp
    private LocalDateTime dateCreated;

    /**
     * Timestamp for date update
     */
    @CreationTimestamp
    private LocalDateTime dateUpdated;
}
