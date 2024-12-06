package com.careerstreet.job_service.client;

import com.careerstreet.job_service.dto.ApiResponse;
import com.careerstreet.job_service.dto.CandidateCvResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "candidate-service", url = "http://localhost:9005/api/candidate-cv")
public interface CandidateCv {
    @GetMapping ("get/{id}")
    ApiResponse<CandidateCvResponse> getCvById (@PathVariable Long id);

}
