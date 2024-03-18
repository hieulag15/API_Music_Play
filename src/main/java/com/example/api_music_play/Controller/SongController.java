package com.example.api_music_play.Controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.api_music_play.Exception.RourceNotFoundException;
import com.example.api_music_play.Mapper.SongMapper;
import com.example.api_music_play.Model.Category;
import com.example.api_music_play.Model.Song;
import com.example.api_music_play.Model.SongUpdate;
import com.example.api_music_play.ModelDTO.SongDTO;
import com.example.api_music_play.ModelMessage.SongMessage;
import com.example.api_music_play.Repository.CategoryRepository;
import com.example.api_music_play.Repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @PostMapping("/create")
    public SongMessage createSong(@RequestParam("file")MultipartFile file, @RequestParam("image") MultipartFile image, @RequestParam String name, @RequestParam String author, @RequestParam String singer, @RequestParam Long category_id) throws IOException {
        SongMessage songMessage = new SongMessage();

        String fileName = name;
        String link = "";
        String linkImage = "";

        //upload file anh
        try {
            Map<String, Object> uploadResult = cloudinary.uploader().upload(image.getBytes(), ObjectUtils.asMap("resource_type", "auto"));
            linkImage = uploadResult.get("secure_url").toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //upload file mp3
        try {
            Map<String, Object> options = new HashMap<>();
            options.put("resource_type", "video");
            options.put("format", "mp3");
            Map<String, Object> result = cloudinary.uploader().upload(file.getBytes(), options);
            link = result.get("secure_url").toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Category category = categoryRepository.getById(category_id);
        Song song = new Song();
        song.setImage(linkImage);
        song.setLink(link);
        song.setName(fileName);
        song.setAuthor(author);
        song.setSinger(singer);
        song.setCategory(category);

        try {
            SongDTO songDTO = songMapper.getListSong(song);
            songMessage.setSong(songDTO);
            songMessage.setMessage("You have successfully created song");
        } catch (Exception e) {
            songMessage.setMessage("You have failed created a song");
        }
        return songMessage;
    }

    @PostMapping(value = "/delete")
    public SongMessage deleteSong(@RequestParam Long id) {
        SongMessage songMessage = new SongMessage();

        Song song = songRepository.findById(id).orElseThrow(()-> new RourceNotFoundException("Song not exits with id: " + id));

        //Lay link bai hat
        String urlSong = song.getLink();
        String[] partsSong = urlSong.split("/");
        String lastPartSong = partsSong[partsSong.length - 1];
        String publicIdSong = lastPartSong.substring(0, lastPartSong.lastIndexOf("."));

        //Lay link image
        String urlImage = song.getImage();
        String[] partsImage = urlSong.split("/");
        String lastPartImage = partsImage[partsImage.length - 1];
        String publicIdImage = lastPartImage.substring(0, lastPartSong.lastIndexOf("."));

        try {
            Map resultSong = cloudinary.uploader().destroy(publicIdSong, ObjectUtils.asMap("resource_type", "video"));
            Map resultImage = cloudinary.uploader().destroy(publicIdImage, ObjectUtils.asMap("resource_type", "image"));
            songRepository.delete(song);
        } catch (IOException e) {
            e.printStackTrace();
        }

        songMessage.setMessage("Successfully");
        return songMessage;
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

    @PostMapping(value = "/getListId")
    public SongMessage getById(@RequestParam List<Long> listId) {
        SongMessage songMessage = new SongMessage();
        if (listId.isEmpty()) {
            songMessage.setMessage("Fail");
            return songMessage;
        }

        List<Song> songs = new ArrayList<Song>();

        for(Long id : listId) {
            if (songRepository.findById(id) != null) {
                Song song = songRepository.findById(id).orElse(null);
                if (song != null) {
                    songs.add(song);
                }
            }
        }

        if (!songs.isEmpty()) {
            List<SongDTO> songDTOS = songMapper.getListSong(songs);
            songMessage.setSongDTOS(songDTOS);
            songMessage.setMessage("Successfully");
        } else {
            songMessage.setMessage("Fail");
        }
        return songMessage;
    }
}
