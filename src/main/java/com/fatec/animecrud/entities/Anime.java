package com.fatec.animecrud.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Anime {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private String image;
    private int numberOfEpisodes;
    private boolean finished;

    public Anime(){}

    public Long getId() {
        return id;
    }

    public Anime setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Anime setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Anime setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getImage() {
        return image;
    }

    public Anime setImage(String image) {
        this.image = image;
        return this;
    }

    public int getNumberOfEpisodes() {
        return numberOfEpisodes;
    }

    public Anime setNumberOfEpisodes(int numberOfEpisodes) {
        this.numberOfEpisodes = numberOfEpisodes;
        return this;
    }

    public boolean isFinished() {
        return finished;
    }

    public Anime setFinished(boolean finished) {
        this.finished = finished;
        return this;
    }
}
