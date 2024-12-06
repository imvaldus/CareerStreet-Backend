package com.careerstreet.save_service.service;

import com.careerstreet.save_service.dto.JobResponse;
import com.careerstreet.save_service.dto.SaveRequest;
import com.careerstreet.save_service.dto.SaveResponse;

import java.util.List;

public interface ISaveService {

    SaveResponse saveJob(SaveRequest saveJobRequestDTO);
    List<JobResponse> getSavedJobs(Long candidateId);

    // XÓA TRONG SAVE
    void DeleteSaveJob(Long CandidateId, Long JobId);

    List<SaveResponse> GetListSaveBỵJobId(Long jobId);
}
