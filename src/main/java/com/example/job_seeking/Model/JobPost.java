package com.example.job_seeking.Model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class JobPost {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; 

    @NotEmpty(message = "Title job shuloud be not empty")
    @Size(min = 4, message = "Title job must be more than 4 character")
    @Column(columnDefinition = "varchar(25) not null")
    private String title; 

    @NotEmpty(message = "Description job shuloud be not empty")
    @Column(columnDefinition = "varchar(250) not null")
    private String description; 

    @NotEmpty(message = "Location job should be not empty")
    @Column(columnDefinition = "varchar(250) not null")
    private String location; 

    @NotNull(message = "Salary should be not null")
    @PositiveOrZero
    @Column(columnDefinition = "decimal(10, 2) not null")
    private double salary; 

    // @JsonFormat(pattern = "yyyy-MM-dd ")
    @Column(columnDefinition = "timestamp default current_timestamp")
    private LocalDateTime postDate; 

}
