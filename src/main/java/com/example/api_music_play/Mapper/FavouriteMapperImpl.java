package com.example.api_music_play.Mapper;

import com.example.api_music_play.Model.Category;
import com.example.api_music_play.Model.Favourite;
import com.example.api_music_play.Model.Song;
import com.example.api_music_play.Model.User;
import com.example.api_music_play.ModelDTO.CategoryDTO;
import com.example.api_music_play.ModelDTO.FavouriteDTO;
import com.example.api_music_play.ModelDTO.SongDTO;
import com.example.api_music_play.ModelDTO.UserDTO;
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
public class FavouriteMapperImpl implements FavouriteMapper{

    @Override
    public FavouriteDTO getListFavourite(Favourite favourite) {
        if (favourite == null) {
            return null;
        }

        FavouriteDTO favouriteDTO = new FavouriteDTO();

        favouriteDTO.setId(favourite.getId());
        favouriteDTO.setSong(songToSongDTO(favourite.getSong()));
        favouriteDTO.setUser(userToUserDTO(favourite.getUser()));
        return favouriteDTO;
    }

    @Override
    public List<FavouriteDTO> getListFavourite(List<Favourite> favourites) {
        if (favourites == null) {
            return null;
        }

        List<FavouriteDTO> favouriteDTOS = new ArrayList<FavouriteDTO>(favourites.size());
        for (Favourite favourite : favourites) {
            favouriteDTOS.add(getListFavourite(favourite));
        }
        return favouriteDTOS;
    }

    protected CategoryDTO categoryToCategoryDTO(Category category) {
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

    protected SongDTO songToSongDTO(Song song) {
        if (song == null) {
            return null;
        }

        SongDTO songDTO = new SongDTO();

        songDTO.setId(song.getId());
        songDTO.setName(song.getName());
        songDTO.setAuthor(song.getAuthor());
        songDTO.setSinger(song.getSinger());
        songDTO.setImage(song.getImage());
        songDTO.setLink(song.getLink());
        songDTO.setCategory(categoryToCategoryDTO(song.getCategory()));
        return songDTO;
    }

    protected UserDTO userToUserDTO(User user) {
        if (user == null) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setId(user.getId());
        userDTO.setFirst_name(user.getFirst_name());
        userDTO.setLast_name(user.getLast_name());
        userDTO.setPhone(user.getPhone());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setRole(user.getRole());
        return userDTO;
    }
}
