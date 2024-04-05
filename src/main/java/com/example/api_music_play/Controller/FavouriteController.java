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
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/favourite")
public class FavouriteController {
    @Autowired
    FavouriteRepository favouriteReponsitory;

    @Autowired
    UserRepository userRepository;

    @Autowired
    SongRepository songRepository;

    @Autowired
    FavouriteMapper favouriteMapper;


    @PostMapping(value = "/find")
    public FavouriteMessage findFavorite(@RequestParam Long songId, @RequestParam Long userId){
        Favourite favourite;
        FavouriteDTO favouriteDTO = new FavouriteDTO();
        FavouriteMessage favouriteMessage = new FavouriteMessage();
        favourite = favouriteReponsitory.findFavorite(songId, userId);
        if(favourite != null){
            favouriteDTO = favouriteMapper.getListFavourite(favourite);
            favouriteMessage.setFavouriteDTO(favouriteDTO);
            favouriteMessage.setMessage("Successful");
        }
        else {
            favouriteMessage.setMessage("Failed");
        }
        return favouriteMessage;
    }
    @PostMapping(value = "/add")
    public FavouriteMessage addFavourite(@RequestParam Long songId, @RequestParam Long userId){
        Song song = songRepository.findById(songId).
                orElseThrow(()-> new RourceNotFoundException("Song not exist with id" + songId));
        User user = userRepository.findById(userId).
                orElseThrow(()-> new RourceNotFoundException("User not exist with id" + userId));
        Favourite favourite = new Favourite();
        favourite.setSong(song);
        favourite.setUser(user);
        FavouriteMessage favouriteMessage = new FavouriteMessage();
        try{
            if(song!= null && user!= null) {
                favouriteReponsitory.save(favourite);
                FavouriteDTO favouriteDTO = favouriteMapper.getListFavourite(favourite);
                favouriteMessage.setMessage("Successful");
                favouriteMessage.setFavouriteDTO(favouriteDTO);
            }
        }
        catch (Exception e)
        {
            favouriteMessage.setMessage("Failed");
        }
        return  favouriteMessage;
    }
    @PostMapping(value = "/delete")
    public FavouriteMessage deleteFavorite(@RequestParam Long songId, @RequestParam Long userId){
        Favourite favourite;
        FavouriteDTO favouriteDTO = new FavouriteDTO();
        FavouriteMessage favouriteMessage = new FavouriteMessage();
        favourite = favouriteReponsitory.findFavorite(songId, userId);
        if(favourite != null){
            favouriteReponsitory.delete(favourite);
            favouriteMessage.setMessage("Successful");
        }
        else {
            favouriteMessage.setMessage("Failed");
        }
        return favouriteMessage;
    }
    @PostMapping(value = "/listByUser")
    public FavouriteMessage listByUser(@RequestParam Long userId){
        System.out.println("userId");
        System.out.println("------------");
        List<Favourite> favourite;
        List<FavouriteDTO>  favouriteDTOs ;
        FavouriteMessage favouriteMessage = new FavouriteMessage();
        favourite = favouriteReponsitory.listByUser(userId);
        if(favourite != null){
            favouriteDTOs = favouriteMapper.getListFavourite(favourite);
            favouriteMessage.setFavouriteDTOS(favouriteDTOs);
            favouriteMessage.setMessage("Successful");
        }
        else {
            favouriteMessage.setMessage("Failed");
        }
        return favouriteMessage;
    }
}
