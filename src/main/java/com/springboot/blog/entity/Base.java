package com.springboot.blog.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Thendo
 * @date 2024/01/06
 */

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class Base implements Serializable {

    /**
     * Human-readable unique number for the instance
     */
    @NonNull
    private String reference;

    /**
     * Short description defining the instance.
     */
    @NonNull
    private String description;

    /**
     * Timestamp when the record was created in the database.
     */
    @NonNull
    private LocalDateTime createdAt;

    /**
     * Timestamp when the record was last updated in the database.
     */
    @NonNull
    private LocalDateTime updatedAt;
}
