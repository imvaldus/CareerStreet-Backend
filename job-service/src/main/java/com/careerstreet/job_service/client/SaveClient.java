package com.careerstreet.job_service.client;


import com.careerstreet.job_service.dto.ApiResponse;
import com.careerstreet.job_service.dto.SaveResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "save-service", url = "http://localhost:9000/api/save")
public interface SaveClient
{
    @GetMapping("{candidateId}")
    ApiResponse<List<SaveResponse>> getSavedJobs(@PathVariable Long jobId);

//    Lấy danh sách save theo jobId
    @GetMapping("/job/{jobId}")
    ApiResponse<List<SaveResponse>> getSavesByJobId(@PathVariable Long jobId);

}
