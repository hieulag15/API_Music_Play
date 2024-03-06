package com.example.api_music_play.Controller;

import com.cloudinary.Cloudinary;
import com.example.api_music_play.Mapper.CategoryMapper;
import com.example.api_music_play.ModelDTO.CategoryDTO;
import com.example.api_music_play.ModelMessage.CategoryMessage;
import com.example.api_music_play.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryMapper categoryMapper;

    private final Cloudinary cloudinary;

    @Autowired
    public CategoryController(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    @PostMapping(value = "/all")
    public List<CategoryDTO> getAllCategory(){
        return categoryMapper.getListCategory(categoryRepository.findAll());
    }
}
