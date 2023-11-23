package com.fatec.animecrud.services;

import com.fatec.animecrud.dtos.AnimeDto;
import com.fatec.animecrud.entities.Anime;
import com.fatec.animecrud.repositories.AnimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnimeService {
    private final AnimeRepository animeRepository;

    @Autowired
    public AnimeService(AnimeRepository animeRepository) {
        this.animeRepository = animeRepository;
    }

    public Anime save(Anime anime){
        return animeRepository.save(anime);
    }

    public Anime convertDtoToAnime(AnimeDto animeDto){
        Anime anime = new Anime();
        anime.setName(animeDto.name())
                .setDescription(animeDto.description())
                .setImage(animeDto.image())
                .setNumberOfEpisodes(animeDto.numberOfEpisodes())
                .setFinished(animeDto.finished());
        return anime;
    }

    public AnimeDto convertAnimeToDto(Anime anime){
        return new AnimeDto(
                anime.getName(),
                anime.getDescription(),
                anime.getImage(),
                anime.getNumberOfEpisodes(),
                anime.isFinished());
    }
}
