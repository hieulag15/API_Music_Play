package com.example.api_music_play.ModelMessage;

import com.example.api_music_play.ModelDTO.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserMessage {

    private UserDTO userDTO;

    private String message;

    private List<UserDTO> userDTOS;
}
