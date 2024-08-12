package com.example.job_seeking.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.job_seeking.Model.JobApplication;
import com.example.job_seeking.Repository.JobApplicationReposioty;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JobApplicationService {

    private final JobApplicationReposioty jobApplicationReposioty; 
    private final UserService userService; 
    private final JobPostService jobPostService; 

    public List<JobApplication> getJobApplications(){
        return jobApplicationReposioty.findAll();
    }

    public JobApplication getJobApplicationById(Integer id){
        JobApplication jobApplication = jobApplicationReposioty.findById(id).orElse(null); 

        if(jobApplication != null){
            return jobApplication; 
        }

        return null; 
    }

    public String addJobApplication(JobApplication jobApplication){
        
        if(userService.getUserById(jobApplication.getUserId()) == null){
            return "User with this id does not exists"; 
        }

        if(jobPostService.getJobPostById(jobApplication.getJobPostId()) == null){
            return "Job Post with this id does not exists"; 
        }

        jobApplicationReposioty.save(jobApplication);
        return "Job Application added successfully"; 
    }


    public String deleteJobPost(Integer id){
        JobApplication jobApplication = jobApplicationReposioty.findById(id).orElse(null); 

        if(jobApplication == null){
            return "Job Application whth this ID " + id + " does not exsist"; 
        }

        jobApplicationReposioty.deleteById(id); 
        return "Job Application whth this ID " + id + ", Deleted successfully"; 
    }
}
