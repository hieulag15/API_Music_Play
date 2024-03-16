package com.example.api_music_play.Controller;

import com.cloudinary.Cloudinary;
import com.example.api_music_play.Exception.RourceNotFoundException;
import com.example.api_music_play.Mapper.SongMapper;
import com.example.api_music_play.Model.Song;
import com.example.api_music_play.Model.SongUpdate;
import com.example.api_music_play.ModelDTO.SongDTO;
import com.example.api_music_play.ModelMessage.SongMessage;
import com.example.api_music_play.Repository.CategoryRepository;
import com.example.api_music_play.Repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/song")
public class SongController {

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SongMapper songMapper;

    private final Cloudinary cloudinary;

    @Autowired
    public SongController(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    @PostMapping(value = "/all")
    public SongMessage getAllSong() {
        List<SongDTO> songDTOS = songMapper.getListSong(songRepository.findAll());
        SongMessage songMessage = new SongMessage();
        if (songDTOS.isEmpty()) {
            songMessage.setMessage("Fail");
            songMessage.setSong(null);
        } else {
            songMessage.setSongDTOS(songDTOS);
            songMessage.setMessage("Successfully");
        }
        return songMessage;
    }

    @PostMapping(value = "/getByName")
    public SongMessage getByName(@RequestParam String name) {
        List<Song> songs;
        songs = songRepository.getByName(name);
        SongMessage songMessage = new SongMessage();
        if (!songs.isEmpty()) {
            List<SongDTO> songDTOS = songMapper.getListSong(songs);
            songMessage.setSongDTOS(songDTOS);
            songMessage.setMessage("Successfully");
        } else {
            songMessage.setMessage("Fail");
            songMessage.setSongDTOS(null);
        }
        return songMessage;
    }

    @PostMapping(value = "/getSongOfCategory")
    public SongMessage getSongofCategory(@RequestParam long category_id) {
        List<Song> songs = songRepository.findByCategory(category_id);
        List<SongDTO> songDTOS = songMapper.getListSong(songs);

        SongMessage songMessage = new SongMessage();
        if (songDTOS.isEmpty()) {
            songMessage.setSongDTOS(null);
            songMessage.setMessage("Fail");
        } else {
            songMessage.setMessage("Successfully");
            songMessage.setSongDTOS(songDTOS);
        }
        return songMessage;
    }

    @PostMapping(value = "/update/{id}")
    public SongMessage update(@RequestParam long id, @RequestBody SongUpdate song) {
        SongMessage songMessage = new SongMessage();

        Song songUpdate = songRepository.findById(id).orElseThrow(()-> new RourceNotFoundException("Song not exiest with id: " + id));
        songUpdate.setName(song.getName());
        songUpdate.setAuthor(song.getAuthor());
        songUpdate.setSinger(song.getSinger());
        songUpdate.setCategory(song.getCategory());
        songRepository.save(songUpdate);
        SongDTO songDTO = songMapper.getListSong(songUpdate);
        songMessage.setSong(songDTO);
        songMessage.setMessage("Successfully");
        return songMessage;
    }


}
