package com.example.api_music_play.Controller;

import com.example.api_music_play.Exception.RourceNotFoundException;
import com.example.api_music_play.Mapper.FavouriteMapper;
import com.example.api_music_play.Model.Favourite;
import com.example.api_music_play.Model.Song;
import com.example.api_music_play.Model.User;
import com.example.api_music_play.ModelDTO.FavouriteDTO;
import com.example.api_music_play.ModelMessage.FavouriteMessage;
import com.example.api_music_play.Repository.FavouriteRepository;
import com.example.api_music_play.Repository.SongRepository;
import com.example.api_music_play.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/favourite")
public class FavouriteController {

    @Autowired
    FavouriteRepository favouriteRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    SongRepository songRepository;

    @Autowired
    FavouriteMapper favouriteMapper;

    @PostMapping(value = "/find")
    public FavouriteMessage findFavourite(@RequestParam Long songId, @RequestParam Long userId) {
        Favourite favourite;
        FavouriteDTO favouriteDTO = new FavouriteDTO();
        FavouriteMessage favouriteMessage = new FavouriteMessage();

        favourite = favouriteRepository.findFavourite(songId, userId);
        if (favourite != null) {
            favouriteDTO = favouriteMapper.getListFavourite(favourite);
            favouriteMessage.setFavouriteDTO(favouriteDTO);
            favouriteMessage.setMessage("Successfull");
        } else {
            favouriteMessage.setMessage("Failed");
        }
        return favouriteMessage;
    }

    @PostMapping(value = "/listByUser")
    public FavouriteMessage listByUser(@RequestParam Long userId) {
        List<Favourite> favourites;
        List<FavouriteDTO> favouriteDTOS;
        FavouriteMessage favouriteMessage = new FavouriteMessage();

        favourites = favouriteRepository.findByUser(userId);
        if (favourites != null) {
            favouriteDTOS = favouriteMapper.getListFavourite(favourites);
            favouriteMessage.setFavouriteDTOS(favouriteDTOS);
            favouriteMessage.setMessage("Successfull");
        } else {
            favouriteMessage.setMessage("Failed");
        }
        return favouriteMessage;
    }

    @PostMapping(value = "/add")
    public FavouriteMessage addFavourite(@RequestParam Long songId, @RequestParam Long userId) {
        Song song = songRepository.findById(songId).orElseThrow(()-> new RourceNotFoundException("Song not exist with id: " + songId));
        User user = userRepository.findById(userId).orElseThrow(()-> new RourceNotFoundException("User not exist with id: " + userId));
        FavouriteMessage favouriteMessage = new FavouriteMessage();
        FavouriteDTO favouriteDTO = new FavouriteDTO();
        Favourite favourite = new Favourite();
        favourite.setSong(song);
        favourite.setUser(user);

        if (song != null && user != null) {
            favouriteRepository.save(favourite);
            favouriteDTO = favouriteMapper.getListFavourite(favourite);
            favouriteMessage.setFavouriteDTO(favouriteDTO);
            favouriteMessage.setMessage("Successfull");
        } else {
            favouriteMessage.setMessage("Failed");
        }
        return favouriteMessage;
    }

    @PostMapping(value = "/delete")
    public FavouriteMessage deleteFavourite(@RequestParam Long songId, @RequestParam Long userId) {
        Favourite favourite;
        FavouriteDTO favouriteDTO = new FavouriteDTO();
        FavouriteMessage favouriteMessage = new FavouriteMessage();

        favourite = favouriteRepository.findFavourite(songId, userId);
        if(favourite != null) {
            favouriteRepository.delete(favourite);
            favouriteMessage.setMessage("Successfull");
        } else {
            favouriteMessage.setMessage("Failed");
        }
        return favouriteMessage;
    }
}
