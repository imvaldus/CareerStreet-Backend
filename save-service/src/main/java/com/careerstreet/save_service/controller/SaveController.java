package com.careerstreet.save_service.controller;

import com.careerstreet.save_service.dto.ApiResponse;
import com.careerstreet.save_service.dto.JobResponse;
import com.careerstreet.save_service.dto.SaveRequest;
import com.careerstreet.save_service.dto.SaveResponse;
import com.careerstreet.save_service.exception.GlobalCode;
import com.careerstreet.save_service.service.ISaveService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/save")
public class SaveController {
    private final ISaveService saveService;

    public SaveController(ISaveService saveService) {
        this.saveService = saveService;
    }

    // DÙNG ĐỂ LƯU CÔNG VIỆC KHI NHẤN VÀO BUTTON SAVE
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<SaveResponse>> saveJob(@RequestBody SaveRequest saveRequest) {

        try {
            SaveResponse save = saveService.saveJob(saveRequest);
            ApiResponse apiResponse = new ApiResponse(GlobalCode.SUCCESS, "ok", save);
            return ResponseEntity.ok(apiResponse);
        } catch (IllegalArgumentException e) {
            ApiResponse<SaveResponse> apiResponse = new ApiResponse<>(GlobalCode.ERROR_ID_EXIST, e.getMessage(), null);
            return ResponseEntity.badRequest().body(apiResponse);
        }

    }

    // HIỂN THỊ DANH SÁCH CÔNG VIỆC CỦA ỨNG VIÊN ĐÃ LƯU
    @GetMapping("/{candidateId}")
    public ResponseEntity<ApiResponse<List<JobResponse>>> getSavedJobs(@PathVariable Long candidateId) {
        List<JobResponse> savedJobs = saveService.getSavedJobs(candidateId);
        ApiResponse<List<JobResponse>> apiResponse = new ApiResponse<>(GlobalCode.SUCCESS, "ok", savedJobs);
        return ResponseEntity.ok(apiResponse);
    }

    //    XÓA TRONG DANH SÁCH SAVE
    @DeleteMapping("/delete/{CandidateId}/{JobId}")
    public ResponseEntity<ApiResponse<Void>> DeleteSaveJob(
            @PathVariable Long CandidateId,
            @PathVariable Long JobId
    ) {
        try {
            saveService.DeleteSaveJob(CandidateId, JobId);
            ApiResponse<Void> apiResponse = new ApiResponse<>(
                    GlobalCode.SUCCESS, "Xóa thành công", null
            );
            return ResponseEntity.ok(apiResponse);

        } catch (Exception e) {
            ApiResponse<Void> apiResponse = new ApiResponse<>(
                    GlobalCode.ERROR_ID_EXIST, "Lỗi không xóa được", null
            );
            return ResponseEntity.badRequest().body(apiResponse);
        }
    }

    //
    @GetMapping("job/{jobId}")
    ResponseEntity<ApiResponse<List<SaveResponse>>> ListJobExpired(@PathVariable Long jobId) {
        List<SaveResponse> saves = saveService.GetListSaveBỵJobId(jobId);
        ApiResponse<List<SaveResponse>> apiResponse = new ApiResponse<>(GlobalCode.SUCCESS, "Ok", saves);
        return ResponseEntity.ok(apiResponse);
    }

}

