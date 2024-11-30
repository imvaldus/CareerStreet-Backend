package com.careerstreet.admin_service.repository;

import com.careerstreet.admin_service.entity.Admin;
import com.careerstreet.admin_service.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Long> {
    List<Blog> findByAdmin(Admin admin);
}
