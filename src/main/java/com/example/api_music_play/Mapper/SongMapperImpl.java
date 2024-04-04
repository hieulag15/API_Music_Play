package com.example.api_music_play.Mapper;

import com.example.api_music_play.Model.Category;
import com.example.api_music_play.Model.Song;
import com.example.api_music_play.ModelDTO.CategoryDTO;
import com.example.api_music_play.ModelDTO.SongDTO;
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
public class SongMapperImpl implements SongMapper{
    @Override
    public SongDTO getListSong(Song song) {
        if ( song == null ) {
            return null;
        }

        SongDTO songDTO = new SongDTO();

        songDTO.setId( song.getId() );
        songDTO.setName( song.getName() );
        songDTO.setAuthor( song.getAuthor() );
        songDTO.setSinger( song.getSinger() );
        songDTO.setLink( song.getLink() );
        songDTO.setImage( song.getImage() );
        songDTO.setCategory( categoryToCategoryDTO( song.getCategory() ) );

        return songDTO;
    }

    @Override
    public List<SongDTO> getListSong(List<Song> songs) {
        if ( songs == null ) {
            return null;
        }

        List<SongDTO> list = new ArrayList<SongDTO>( songs.size() );
        for ( Song song : songs ) {
            list.add( getListSong( song ) );
        }

        return list;
    }

    protected CategoryDTO categoryToCategoryDTO(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryDTO categoryDTO = new CategoryDTO();

        categoryDTO.setId( category.getId() );
        categoryDTO.setName( category.getName() );
        categoryDTO.setImage( category.getImage() );
        categoryDTO.setDescription( category.getDescription() );

        return categoryDTO;
    }
}
