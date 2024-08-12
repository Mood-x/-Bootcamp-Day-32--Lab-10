package com.example.job_seeking.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.job_seeking.API.ApiResponse;
import com.example.job_seeking.Model.JobApplication;
import com.example.job_seeking.Service.JobApplicationService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/jobApplications")
@RequiredArgsConstructor
public class JobApplicationController {

    private final JobApplicationService jobApplicationService; 

    @GetMapping
    public ResponseEntity getJobApplications(){
        if(jobApplicationService.getJobApplications().isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("Not found job application ")); 
        }else {
            return ResponseEntity.status(200).body(jobApplicationService.getJobApplications()); 
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getJobApplicationById(@PathVariable Integer id){
        JobApplication jobApplication = jobApplicationService.getJobApplicationById(id); 

        if(jobApplication == null){
            
            return ResponseEntity.status(404).body("Job Post with this ID " + id + " does not exsist"); 
        }else {
            return ResponseEntity.status(200).body(jobApplication);
        }
    }

    @PostMapping("/add")
    public ResponseEntity addJobApplication(@Valid @RequestBody JobApplication jobApplication, Errors err){
        if(err.hasErrors()){
            String message = err.getFieldError().getDefaultMessage(); 
            return ResponseEntity.status(400).body(message); 
        }

        String result = jobApplicationService.addJobApplication(jobApplication);
        
        if(result.equals("Job Application added successfully")){
            return ResponseEntity.status(200).body(new ApiResponse(result)); 
        }else {
            return ResponseEntity.status(404).body(new ApiResponse(result)); 
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteJobApplication(@PathVariable Integer id){
        String result = jobApplicationService.deleteJobPost(id); 

        if(result.equals("Job Application whth this ID " + id + " does not exsist")){
            return ResponseEntity.status(404).body(new ApiResponse(result)); 
        }else {
            return ResponseEntity.status(200).body(new ApiResponse(result)); 
        }
    }
}
