package com.example.job_seeking.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.job_seeking.Model.JobPost;

@Repository
public interface JobPostRepository extends JpaRepository<JobPost, Integer>{

}
