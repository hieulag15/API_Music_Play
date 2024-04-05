package com.example.api_music_play.Mapper;

import com.example.api_music_play.Model.Category;
import com.example.api_music_play.ModelDTO.CategoryDTO;
import org.springframework.stereotype.Component;

import javax.annotation.processing.Generated;
import java.util.ArrayList;
import java.util.List;

@Generated(
        value = "org.mapstruct.ap.MappingProcessor",
        date = "2024-03-27T13:00:00+0700",
        comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.10 (Oracle Corporation)"
)

@Component
public class CategoryMapperImpl implements CategoryMapper{
    @Override
    public CategoryDTO getListCategory(Category category) {
        if (category == null) {
            return null;
        }

        CategoryDTO categoryDTO = new CategoryDTO();

        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());
        categoryDTO.setImage(category.getImage());
        categoryDTO.setDescription(category.getDescription());
        return categoryDTO;
    }

    @Override
    public List<CategoryDTO> getListCategory(List<Category> categories) {
        if (categories == null) {
            return null;
        }

        List<CategoryDTO> categoryDTOS = new ArrayList<>(categories.size());
        for (Category category : categories) {
            categoryDTOS.add(getListCategory(category));
        }
        return categoryDTOS;
    }
}
