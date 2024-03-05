package com.example.api_music_play.ModelDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FavouriteDTO {
    private long id;

    private SongDTO song;

    private UserDTO user;
}
