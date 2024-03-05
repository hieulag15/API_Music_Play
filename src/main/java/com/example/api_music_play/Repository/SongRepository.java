package com.example.api_music_play.Repository;

import com.example.api_music_play.Model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SongRepository extends JpaRepository<Song, Long> {

    @Query("select s from Song s where s.category.id = :category_id")
    List<Song> findByCategory(@Param("category_id") Long category_id);

    @Query("select s from Song s where s.name like %:name%")
    List<Song> getByName(@Param("name") String name);
}
