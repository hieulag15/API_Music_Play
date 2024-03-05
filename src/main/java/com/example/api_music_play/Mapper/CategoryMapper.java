package com.example.api_music_play.Mapper;

import com.example.api_music_play.Model.Category;
import com.example.api_music_play.ModelDTO.CategoryDTO;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "srping")
@Component
public interface CategoryMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "image", target = "image")
    @Mapping(source = "description", target = "description")
    @BeanMapping(ignoreByDefault = true)
    @Named("CategoryDTO")
    CategoryDTO getListCategory(Category category);

    @IterableMapping(elementTargetType = CategoryDTO.class, qualifiedByName = "CategoryDTO")
    List<CategoryDTO> getListCategory(List<Category> categories);

}
