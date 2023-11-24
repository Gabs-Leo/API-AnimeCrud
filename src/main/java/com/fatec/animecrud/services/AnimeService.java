package com.fatec.animecrud.services;

import com.fatec.animecrud.dtos.AnimeDto;
import com.fatec.animecrud.entities.Anime;
import com.fatec.animecrud.repositories.AnimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
                .setFinished(animeDto.finished())
                .setId(animeDto.id());
        return anime;
    }

    public AnimeDto convertAnimeToDto(Anime anime){
        return new AnimeDto(
                anime.getId(),
                anime.getName(),
                anime.getDescription(),
                anime.getImage(),
                anime.getNumberOfEpisodes(),
                anime.isFinished()
        );
    }

    public void delete(Long id) {
        animeRepository.deleteById(id);
    }
    public List<AnimeDto> getAllAnimes() {
        List<Anime> animes = animeRepository.findAll();
        return animes.stream()
                .map(this::convertAnimeToDto)
                .collect(Collectors.toList());
    }

    public Optional<Anime> getAnimeById(Long id) {
        return animeRepository.findById(id);
    }
}
