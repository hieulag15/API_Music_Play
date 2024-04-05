package com.example.api_music_play.Mapper;

import com.example.api_music_play.Model.User;
import com.example.api_music_play.ModelDTO.UserDTO;
import org.springframework.stereotype.Component;

import javax.annotation.processing.Generated;
import java.util.ArrayList;
import java.util.List;

@Generated(
        value = "org.mapstruct.ap.MappingProcessor",
        date = "2024-03-27T13:00:00+0700",
        comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper{

    @Override
    public UserDTO getListUser(User user) {
        if (user == null) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setId(user.getId());
        userDTO.setFirst_name(user.getFirst_name());
        userDTO.setLast_name(user.getLast_name());
        userDTO.setPhone(user.getPhone());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setRole(user.getRole());
        return userDTO;
    }

    @Override
    public List<UserDTO> getListUser(List<User> users) {
        if (users == null) {
            return null;
        }

        List<UserDTO> userDTOS = new ArrayList<UserDTO>(users.size());
        for (User user : users) {
            userDTOS.add(getListUser(user));
        }
        return userDTOS;
    }
}
