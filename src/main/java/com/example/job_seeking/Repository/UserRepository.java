package com.example.job_seeking.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.job_seeking.Model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

}
