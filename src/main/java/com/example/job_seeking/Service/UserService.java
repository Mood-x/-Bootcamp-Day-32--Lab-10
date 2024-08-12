package com.example.job_seeking.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.job_seeking.Model.User;
import com.example.job_seeking.Repository.UserRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class UserService {
    private final  UserRepository userRepository; 

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public User getUserById(Integer id){
        User user = userRepository.findById(id).orElse(null); 

        if(user != null){
            return user; 
        }

        return null; 
    }

    public void addUser(User user){
        userRepository.save(user); 
    }

    public String updateUser(Integer id, User userUpdated){
        User user = userRepository.findById(id).orElse(null); 

        if(user == null){
            return "User whth this ID " + id + " does not exsist"; 
        }

        user.setName(userUpdated.getName());
        user.setEmail(userUpdated.getEmail());
        user.setPassword(userUpdated.getPassword());
        user.setAge(userUpdated.getAge());
        user.setRole(userUpdated.getRole());

        userRepository.save(user); 
        return "User whth this ID " + id + ", Updated successfully"; 
    }

    public String deleteUser(Integer id){
        User user = userRepository.findById(id).orElse(null); 

        if(user == null){
            return "User whth this ID " + id + " does not exsist"; 
        }

        userRepository.deleteById(id); 
        return "User whth this ID " + id + ", Deleted successfully"; 
    }

}
