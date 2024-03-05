package com.example.api_music_play.ModelDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
    private long id;

    private String name;

    private String image;

    private String description;
}
