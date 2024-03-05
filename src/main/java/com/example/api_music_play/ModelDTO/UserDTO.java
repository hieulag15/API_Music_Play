package com.example.api_music_play.ModelDTO;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private long id;

    private String phone;

    private String first_name;

    private String last_name;

    private String email;

    private String password;

    private String role;
}
