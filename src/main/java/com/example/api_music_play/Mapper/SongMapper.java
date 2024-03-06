package com.example.api_music_play.Mapper;

import com.example.api_music_play.Model.Song;
import com.example.api_music_play.ModelDTO.SongDTO;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface SongMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "author", target = "author")
    @Mapping(source = "singer", target = "singer")
    @Mapping(source = "link", target = "link")
    @Mapping(source = "image", target = "image")
    @Mapping(source = "category", target = "category")
    @BeanMapping(ignoreByDefault = true)
    @Named("SongDTO")
    SongDTO getListSong(Song song);

    @Named("getNoticeBoardStatusName")
    @IterableMapping(elementTargetType = SongDTO.class, qualifiedByName = "SongDTO")
    List<SongDTO> getListSong(List<Song> songs);
}
