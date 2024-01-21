package com.springboot.blog.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Date;

/**
 * @author Thendo
 * @date 2024/01/21
 */
@Getter
@Setter
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "OTP")
public class OneTimePassword {

    /**
     * Unique ID for database referencing.
     */
    @Id
    @NonNull
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * OTP for user to confirm account.
     */
    @NonNull
    @Column(name = "otp", nullable = false)
    private Integer oneTimePasswordCode;

    /**
     * Expiry date for OTP
     */
    @NonNull
    @Column(name = "expiryDate", nullable = false)
    private Date expires;

    /**
     * 1-to-1 relationship with OneTimePassword
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
