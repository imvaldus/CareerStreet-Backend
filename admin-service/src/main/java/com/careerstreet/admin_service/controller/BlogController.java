package com.careerstreet.admin_service.controller;

import com.careerstreet.admin_service.dto.ApiResponse;
import com.careerstreet.admin_service.dto.BlogRequest;
import com.careerstreet.admin_service.dto.BlogResponse;
import com.careerstreet.admin_service.entity.Blog;
import com.careerstreet.admin_service.exception.GlobalCode;
import com.careerstreet.admin_service.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/blog/")
public class BlogController {
    private final BlogService blogService;

    @GetMapping("list/all")
    public ResponseEntity<ApiResponse<List<BlogResponse>>> getAllBlogs() {
        System.out.println("Lấy tất cả blog");
        List<BlogResponse> blogs = blogService.getAllBlogs();
        ApiResponse apiResponse = new ApiResponse<>(GlobalCode.SUCCESS, "Lấy danh sách Blog thành công", blogs);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("{blogId}")
    public ResponseEntity<ApiResponse<BlogResponse>> getBlogById(@PathVariable Long blogId) {
        System.out.println("Lấy blog by id: " + blogId);
        BlogResponse blogResponse = blogService.getBlogById(blogId);
        ApiResponse apiResponse = new ApiResponse<>(GlobalCode.SUCCESS, "Lấy thông tin Blog thành công", blogResponse);
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("create")
    public ResponseEntity<ApiResponse<BlogResponse>> createBlog(@RequestBody BlogRequest blogRequest) {
        System.out.println("Cbi tao blog");
        BlogResponse blogResponse = blogService.createBlog(blogRequest);
        ApiResponse apiResponse = new ApiResponse<>(GlobalCode.SUCCESS, "Tao Blog thanh cong", blogResponse);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @PutMapping("update/{blogId}")
    public ResponseEntity<ApiResponse<BlogResponse>> updateBlog(@RequestBody BlogRequest blogRequest, @PathVariable Long blogId) {
        System.out.println("Cbi cap nhat blog");
        BlogResponse blogResponse = blogService.updateBlog(blogRequest, blogId);
        ApiResponse apiResponse = new ApiResponse<>(GlobalCode.SUCCESS, "Cap nhat Blog thanh cong", blogResponse);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @DeleteMapping("{blogId}")
    public ResponseEntity<ApiResponse<Void>> deleteBlog(@PathVariable Long blogId) {
        System.out.println("Xóa blog: " + blogId);
        blogService.deleteBlog(blogId);
        ApiResponse apiResponse = new ApiResponse<>(GlobalCode.SUCCESS, "Xóa Blog thành công", null);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("admin/{adminId}")
    public ResponseEntity<ApiResponse<List<BlogResponse>>> getBlogsByAdminId(@PathVariable Long adminId) {
        System.out.println("Lấy blogs của admin: " + adminId);
        List<BlogResponse> blogs = blogService.getBlogsByAdminId(adminId);
        ApiResponse apiResponse = new ApiResponse<>(GlobalCode.SUCCESS, "Lấy danh sách Blog của admin thành công", blogs);
        return ResponseEntity.ok(apiResponse);
    }
}
