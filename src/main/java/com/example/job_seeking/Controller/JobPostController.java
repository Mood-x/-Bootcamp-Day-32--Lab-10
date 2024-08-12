package com.example.job_seeking.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.job_seeking.API.ApiResponse;
import com.example.job_seeking.Model.JobPost;
import com.example.job_seeking.Service.JobPostService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/jobPosts")
@RequiredArgsConstructor
public class JobPostController {

    private final JobPostService jobPostService; 

    @GetMapping
    public ResponseEntity getJobPosts(){
        if(jobPostService.getJobPosts().isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("Not found job posts")); 
        }else {
            return ResponseEntity.status(200).body(jobPostService.getJobPosts()); 
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getJobPostById(@PathVariable Integer id){
        JobPost jobPost = jobPostService.getJobPostById(id); 
        if(jobPost == null){
            return ResponseEntity.status(404).body(new ApiResponse("Job Post with this ID " + id + " does not exsist"));
        }else {
            return ResponseEntity.status(200).body(jobPost); 
        }
    }

    @PostMapping("/add")
    public ResponseEntity addJobPost(@Valid @RequestBody JobPost jobPost, Errors err){
        if(err.hasErrors()){
            String message = err.getFieldError().getDefaultMessage(); 
            return ResponseEntity.status(400).body(message); 
        }

        jobPostService.addJobPost(jobPost);
        return ResponseEntity.status(200).body(new ApiResponse("Job Post added successfully")); 
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateJobPost(@PathVariable Integer id, @Valid @RequestBody JobPost jobPost, Errors err){
        if(err.hasErrors()){
            String message = err.getFieldError().getDefaultMessage(); 
            return ResponseEntity.status(400).body(message); 
        }

        String result = jobPostService.updateJobPost(id, jobPost); 

        if(result.equals("Job Post whth this ID " + id + " does not exsist")){
            return ResponseEntity.status(404).body(new ApiResponse(result)); 
        }else {
            return ResponseEntity.status(200).body(new ApiResponse(result)); 
        }
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteJobPost(@PathVariable Integer id){
        String result = jobPostService.deleteJobPost(id); 

        if(result.equals("Job Post whth this ID " + id + " does not exsist")){
            return ResponseEntity.status(404).body(new ApiResponse(result)); 
        }else {
            return ResponseEntity.status(200).body(new ApiResponse(result)); 
        }
    }
}
