package com.example.api_music_play.Mapper;

import com.example.api_music_play.Model.User;
import com.example.api_music_play.ModelDTO.UserDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface UserMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "phone", target = "phone")
    @Mapping(source = "first_name", target = "first_name")
    @Mapping(source = "last_name", target = "last_name")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "passsword", target = "password")
    @Mapping(source = "role", target = "role")
    @BeanMapping(ignoreByDefault = true)
    @Named("UserDTO")
    UserDTO getListUser(User user);

    List<UserDTO> getListUser(List<User> users);
}
