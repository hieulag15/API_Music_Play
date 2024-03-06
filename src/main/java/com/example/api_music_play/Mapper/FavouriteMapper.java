package com.example.api_music_play.Mapper;

import com.example.api_music_play.Model.Favourite;
import com.example.api_music_play.ModelDTO.FavouriteDTO;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface FavouriteMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "song", target = "song")
    @Mapping(source = "user", target = "user")
    @BeanMapping(ignoreByDefault = true)
    @Named("FavouriteDTO")
    FavouriteDTO getListFavourite(Favourite favourite);

    @Named("getNoticeBoardStatusName")
    @IterableMapping(elementTargetType = FavouriteDTO.class, qualifiedByName = "FavouriteDTO")
    List<FavouriteDTO> getListFavourite(List<Favourite> favourites);
}
