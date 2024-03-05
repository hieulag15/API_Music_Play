package com.example.api_music_play.Repository;

import com.example.api_music_play.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
