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
import com.example.job_seeking.Model.User;
import com.example.job_seeking.Service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService; 

    @GetMapping
    public ResponseEntity getUsers(){
        if(userService.getUsers().isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("Not found users")); 
        }else {
            return ResponseEntity.status(200).body(userService.getUsers()); 
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getUserById(@PathVariable Integer id){
        User user = userService.getUserById(id); 
        if(user == null){
            return ResponseEntity.status(404).body(new ApiResponse("User with this ID " + id + " does not exsist"));
        }else {

            return ResponseEntity.status(200).body(user); 
        }
    }

    @PostMapping("/add")
    public ResponseEntity addUser(@Valid @RequestBody User user, Errors err){
        if(err.hasErrors()){
            String message = err.getFieldError().getDefaultMessage(); 
            return ResponseEntity.status(400).body(message); 
        }

        userService.addUser(user);
        return ResponseEntity.status(200).body(new ApiResponse("User added successfully")); 
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable Integer id, @Valid @RequestBody User user, Errors err){
        if(err.hasErrors()){
            String message = err.getFieldError().getDefaultMessage(); 
            return ResponseEntity.status(400).body(message); 
        }

        String result = userService.updateUser(id, user); 

        if(result.equals("User whth this ID " + id + " does not exsist")){
            return ResponseEntity.status(404).body(new ApiResponse(result)); 
        }else {
            return ResponseEntity.status(200).body(new ApiResponse(result)); 
        }
    }



    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id){
        String result = userService.deleteUser(id); 

        if(result.equals("User whth this ID " + id + " does not exsist")){
            return ResponseEntity.status(404).body(new ApiResponse(result)); 
        }else {
            return ResponseEntity.status(200).body(new ApiResponse(result)); 
        }
    }
}
