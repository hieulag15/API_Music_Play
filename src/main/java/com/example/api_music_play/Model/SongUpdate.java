package com.example.api_music_play.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SongUpdate {
    private String name;
    private String author;
    private String singer;
    private Category category;
}
