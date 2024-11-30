package com.careerstreet.admin_service.service;

import com.careerstreet.admin_service.dto.BlogRequest;
import com.careerstreet.admin_service.dto.BlogResponse;

import java.util.List;

public interface BlogService {

    BlogResponse createBlog(BlogRequest blogRequest);

    BlogResponse updateBlog(BlogRequest blogRequest, Long blogId);

    BlogResponse getBlogById(Long blogId);

    List<BlogResponse> getAllBlogs();

    List<BlogResponse> getBlogsByAdminId(Long adminId);

    void deleteBlog(Long blogId);
}
