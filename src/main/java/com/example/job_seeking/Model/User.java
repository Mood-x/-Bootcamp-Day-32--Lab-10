package com.example.job_seeking.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Name should be not empty")
    @Size(min = 4, message = "Name length must be more than 4")
    @Pattern(regexp = "^[a-zA-Z_ ]*$")
    @Column(columnDefinition = "varchar(15) not null")
    private String name; 


    @NotEmpty(message = "Email should be not empty")
    @Email
    @Column(columnDefinition = "varchar(25) unique not null")
    private String email;

    @NotEmpty(message = "Password should be not empty")
    @Column(columnDefinition =  "varchar(25) not null")
    private String password;

    @NotNull
    @Positive
    @Min( value = 21, message = "Age must be more than 21")
    @Column(columnDefinition = "int not null")
    private int age; 

    @NotEmpty(message = "Role should be not empty")
    @Pattern(regexp = "^(JOB_SEEKER|EMPLOYER)$", message = "Role must be either 'JOB_SEEKER', 'EMPLOYER'")
    @Column(columnDefinition = "enum ('JOB_SEEKER', 'EMPLOYER') not null")
    private String role; 

}
