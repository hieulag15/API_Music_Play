package com.example.api_music_play.ModelDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SongDTO {
    private long id;

    private String name;

    private String author;

    private String singer;

    private String link;

    private String image;

    private CategoryDTO category;
}
