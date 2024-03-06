package com.example.api_music_play.ModelMessage;

import com.example.api_music_play.Model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryMessage {

    private Category category;

    private String message;
}
