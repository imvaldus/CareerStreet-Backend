package com.careerstreet.admin_service.service.implement;

import com.careerstreet.admin_service.dto.BlogRequest;
import com.careerstreet.admin_service.dto.BlogResponse;
import com.careerstreet.admin_service.entity.Admin;
import com.careerstreet.admin_service.entity.Blog;
import com.careerstreet.admin_service.exception.EntityNotFoundException;
import com.careerstreet.admin_service.exception.GlobalCode;
import com.careerstreet.admin_service.repository.AdminRepository;
import com.careerstreet.admin_service.repository.BlogRepository;
import com.careerstreet.admin_service.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BlogServiceImpl implements BlogService {
    private final BlogRepository blogRepository;
    private final AdminRepository adminRepository;
    private final ModelMapper modelMapper;

    @Override
    public BlogResponse createBlog(BlogRequest blogRequest) {
        Admin admin = adminRepository.findById(blogRequest.getAdmin_id())
                .orElseThrow(() -> new EntityNotFoundException("Admin not found", GlobalCode.ERROR_ENTITY_NOT_FOUND));

        Blog blog = modelMapper.map(blogRequest, Blog.class);
        blog.setAdmin(admin);
        blog = blogRepository.save(blog);

        BlogResponse blogResponse = modelMapper.map(blog, BlogResponse.class);
        blogResponse.setAdmin_id(admin.getAdminId());
        return blogResponse;
    }

    @Override
    public BlogResponse updateBlog(BlogRequest blogRequest, Long blogId) {
        Blog blog = blogRepository.findById(blogId)
                .orElseThrow(() -> new EntityNotFoundException("Blog not found with id: " + blogId, GlobalCode.ERROR_ENTITY_NOT_FOUND));

        // Update fields from request
        blog.setTitle(blogRequest.getTitle());
        blog.setContent(blogRequest.getContent());
        blog.setAuthor(blogRequest.getAuthor());
        blog.setDate(blogRequest.getDate());
        blog.setOrigin(blogRequest.getOrigin());

        blog = blogRepository.save(blog);

        BlogResponse blogResponse = modelMapper.map(blog, BlogResponse.class);
        blogResponse.setAdmin_id(blog.getAdmin().getAdminId());
        return blogResponse;
    }

    @Override
    public BlogResponse getBlogById(Long blogId) {
        Blog blog = blogRepository.findById(blogId)
                .orElseThrow(() -> new EntityNotFoundException("Blog not found with id: " + blogId, GlobalCode.ERROR_ENTITY_NOT_FOUND));
        
        BlogResponse blogResponse = modelMapper.map(blog, BlogResponse.class);
        blogResponse.setAdmin_id(blog.getAdmin().getAdminId());
        return blogResponse;
    }

    @Override
    public List<BlogResponse> getAllBlogs() {
        List<Blog> blogs = blogRepository.findAll();
        return blogs.stream()
                .map(blog -> {
                    BlogResponse response = modelMapper.map(blog, BlogResponse.class);
                    response.setAdmin_id(blog.getAdmin().getAdminId());
                    return response;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<BlogResponse> getBlogsByAdminId(Long adminId) {
        Admin admin = adminRepository.findById(adminId)
                .orElseThrow(() -> new EntityNotFoundException("Admin not found with id: " + adminId, GlobalCode.ERROR_ENTITY_NOT_FOUND));
        
        return admin.getBlogs().stream()
                .map(blog -> {
                    BlogResponse response = modelMapper.map(blog, BlogResponse.class);
                    response.setAdmin_id(adminId);
                    return response;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void deleteBlog(Long blogId) {
        if (!blogRepository.existsById(blogId)) {
            throw new EntityNotFoundException("Blog not found with id: " + blogId, GlobalCode.ERROR_ENTITY_NOT_FOUND);
        }
        blogRepository.deleteById(blogId);
    }
}
