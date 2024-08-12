package com.example.job_seeking.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.job_seeking.Model.JobApplication;

@Repository
public interface JobApplicationReposioty extends JpaRepository<JobApplication, Integer>{

}
