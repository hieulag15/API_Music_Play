package com.example.api_music_play.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RourceNotFoundException extends RuntimeException{
    public RourceNotFoundException(String message){
        super(message);
    }
}
