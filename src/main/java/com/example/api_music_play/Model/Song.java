package com.example.api_music_play.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "song")
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "author")
    private String author;

    @Column(name = "singer")
    private String singer;

    @Column(name = "link")
    private String link;

    @Column(name = "image")
    private String image;

    @ManyToOne
    private Category category;

    @OneToMany(mappedBy = "song", cascade = CascadeType.ALL)
    private List<Favourite> favourites;

}
