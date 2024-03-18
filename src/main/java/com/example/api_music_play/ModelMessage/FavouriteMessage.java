package com.example.api_music_play.ModelMessage;

import com.example.api_music_play.ModelDTO.FavouriteDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FavouriteMessage {
    private FavouriteDTO favouriteDTO;

    private List<FavouriteDTO> favouriteDTOS;

    private String message;
}
