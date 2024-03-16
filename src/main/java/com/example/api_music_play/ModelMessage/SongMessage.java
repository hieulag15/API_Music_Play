package com.example.api_music_play.ModelMessage;

import com.example.api_music_play.ModelDTO.SongDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SongMessage {
    private SongDTO song;

    private List<SongDTO> songDTOS;

    private String message;
}
