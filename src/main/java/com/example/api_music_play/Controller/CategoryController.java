package com.example.api_music_play.Controller;

import com.cloudinary.Cloudinary;
import com.example.api_music_play.Exception.RourceNotFoundException;
import com.example.api_music_play.Mapper.CategoryMapper;
import com.example.api_music_play.Model.Category;
import com.example.api_music_play.ModelDTO.CategoryDTO;
import com.example.api_music_play.ModelMessage.CategoryMessage;
import com.example.api_music_play.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping(value = "/update/{id}")
    public CategoryMessage update(@PathVariable long id, @RequestBody Category category){
        CategoryMessage categoryMessage = new CategoryMessage();
        Category categoryUpdate = categoryRepository.findById(id).orElseThrow(()-> new RourceNotFoundException("Category not exist with id: " + id));
        categoryUpdate.setName(category.getName());
        categoryUpdate.setDescription(category.getDescription());
        categoryRepository.save(categoryUpdate);
        categoryMessage.setCategory(categoryUpdate);
        categoryMessage.setMessage("Successfull");
        return categoryMessage;
    }

    @PostMapping(value = "/delete")
    public CategoryMessage delete(@RequestParam Long id){
        CategoryMessage categoryMessage = new CategoryMessage();
        Category category = categoryRepository.findById(id).orElseThrow(()-> new RourceNotFoundException("Category not exist with id: " + id));
        if (category != null) {
            categoryRepository.delete(category);
            categoryMessage.setMessage("Successfull");
            return categoryMessage;
        } else {
            categoryMessage.setMessage("Failde");
            return categoryMessage;
        }

    }
}
