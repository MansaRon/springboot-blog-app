package com.springboot.blog.repository;

import com.springboot.blog.entity.OneTimePassword;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Thendo
 * @date 2024/01/21
 */
public interface OneTimePasswordRepository extends JpaRepository<OneTimePassword, Long> {
}
