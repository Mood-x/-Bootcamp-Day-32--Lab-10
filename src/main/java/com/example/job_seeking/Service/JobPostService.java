package com.example.job_seeking.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.job_seeking.Model.JobPost;
import com.example.job_seeking.Repository.JobPostRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JobPostService {

    private final JobPostRepository jobPostRepository; 

        public List<JobPost> getJobPosts(){
        return jobPostRepository.findAll();
    }

    public JobPost getJobPostById(Integer id){
        JobPost jobPost = jobPostRepository.findById(id).orElse(null); 

        if(jobPost != null){
            return jobPost; 
        }

        return null; 
    }

    public void addJobPost(JobPost jobPost){
        jobPostRepository.save(jobPost); 
    }

    public String updateJobPost(Integer id, JobPost jobPostUpdated){
        JobPost jobPost = jobPostRepository.findById(id).orElse(null); 

        if(jobPost == null){
            return "Job Post whth this ID " + id + " does not exsist"; 
        }

        jobPost.setTitle(jobPostUpdated.getTitle());
        jobPost.setDescription(jobPostUpdated.getDescription());
        jobPost.setLocation(jobPostUpdated.getLocation());
        jobPost.setSalary(jobPostUpdated.getSalary());
        jobPost.setPostDate(jobPostUpdated.getPostDate());

        jobPostRepository.save(jobPost); 
        return "Job Post whth this ID " + id + ", Updated successfully"; 
    }

    public String deleteJobPost(Integer id){
        JobPost jobPost = jobPostRepository.findById(id).orElse(null); 

        if(jobPost == null){
            return "Job Post whth this ID " + id + " does not exsist"; 
        }

        jobPostRepository.deleteById(id); 
        return "Job Post whth this ID " + id + ", Deleted successfully"; 
    }

}
