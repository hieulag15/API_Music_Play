package com.example.api_music_play.Repository;

import com.example.api_music_play.Model.Favourite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FavouriteRepository extends JpaRepository<Favourite, Long> {

    @Query("select f from Favourite f where f.song.id = :songId and f.user.id = :userId")
    Favourite findFavourite(@Param("songId") Long songId, @Param("userId") Long userId);

    @Query("select f from Favourite f where f.user.id = :userId")
    List<Favourite> findByUser(@Param("userId") Long userId);
}
