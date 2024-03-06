package com.example.api_music_play.Controller;

import com.example.api_music_play.Exception.RourceNotFoundException;
import com.example.api_music_play.Mapper.UserMapper;
import com.example.api_music_play.Model.User;
import com.example.api_music_play.ModelDTO.UserDTO;
import com.example.api_music_play.ModelMessage.UserMessage;
import com.example.api_music_play.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @PostMapping(value = "/all")
    public UserMessage getAllUser(){
        List<UserDTO> userDTOS = userMapper.getListUser(userRepository.findAll());
        UserMessage userMessage = new UserMessage();
        userMessage.setUserDTOS(userDTOS);
        userMessage.setMessage("sucsesfull");
        return userMessage;
    }

    @GetMapping(value = "/getUserById/{id}")
    public UserMessage getUserById(@PathVariable long id){
        User user = userRepository.findById(id).orElseThrow(()-> new RourceNotFoundException("User not exist with id:" + id));
        UserDTO userDTO = userMapper.getListUser(user);
        UserMessage userMessage = new UserMessage();
        userMessage.setUserDTO(userDTO);
        return userMessage;
    }

    @PostMapping(value = "/register")
    public UserMessage register(@RequestBody User user){
        user.setRole("user");
        UserMessage userMessage = new UserMessage();
        try {
            UserDTO userDTO = userMapper.getListUser(userRepository.save(user));
            userMessage.setUserDTO(userDTO);
            userMessage.setMessage("You have successfull created a user account!");
            return userMessage;
        } catch (Exception e)
        {
            userMessage.setUserDTOS(null);
            userMessage.setMessage("You have failed to create a user account!");
            return userMessage;
        }
    }

    @PutMapping(value = "/update/{id}")
    public UserMessage updateUserById(@PathVariable long id, @RequestBody User user){
        User updateUser = userRepository.findById(id).orElseThrow(()-> new RourceNotFoundException("User not exist with id: " + id));
        updateUser.setFirst_name(user.getFirst_name());
        updateUser.setLast_name(user.getLast_name());
        updateUser.setPassword(user.getPassword());
        updateUser.setEmail(user.getEmail());
        userRepository.save(updateUser);
        UserDTO userDTO = userMapper.getListUser(updateUser);
        UserMessage userMessage = new UserMessage();
        userMessage.setUserDTO(userDTO);
        userMessage.setMessage("Successfull");
        return userMessage;
    }

    public UserMessage deleteUser(@RequestParam long id){
        UserMessage userMessage = new UserMessage();
        User user = userRepository.findById(id).orElseThrow(()-> new RourceNotFoundException("User not exist with id: " + id));
        if (user != null) {
            userRepository.delete(user);
            userMessage.setMessage("Successfull");
            return userMessage;
        }
        else {
            userMessage.setMessage("Failed");
            return userMessage;
        }
    }

}
