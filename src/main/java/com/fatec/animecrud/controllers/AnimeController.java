package com.fatec.animecrud.controllers;

import com.fatec.animecrud.dtos.AnimeDto;
import com.fatec.animecrud.entities.Anime;
import com.fatec.animecrud.services.AnimeService;
import com.fatec.animecrud.utils.Response;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/animes")
public class AnimeController {
    private final AnimeService animeService;

    @Autowired
    public AnimeController(AnimeService animeService) {
        this.animeService = animeService;
    }

    @PostMapping
    public ResponseEntity<Response<AnimeDto>> postAnime(@Valid @RequestBody AnimeDto animeDto, BindingResult result){
        Response<AnimeDto> response = new Response<>();
        if(result.hasErrors()){
            result.getAllErrors().forEach(i -> response.getErrors().add(i.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }
        response.setData(
                animeService.convertAnimeToDto(
                    animeService.save(animeService.convertDtoToAnime(animeDto))
                )
        );
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Response<String>> deleteAnime(@PathVariable Long id) {
        Response<String> response = new Response<>();
        Optional<Anime> anime = animeService.getAnimeById(id);
        if(anime.isEmpty()){
            response.getErrors().add("Anime não encontrado!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        animeService.delete(id);
        response.setData("Anime removido com sucesso");
        return ResponseEntity.ok(response);
    }
    @GetMapping
    public ResponseEntity<Response<List<AnimeDto>>> getAllAnimes() {
        Response<List<AnimeDto>> response = new Response<>();
        List<AnimeDto> animes = animeService.getAllAnimes();
        response.setData(animes);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<AnimeDto>> getAnime(@PathVariable Long id) {
        Response<AnimeDto> response = new Response<>();
        Optional<Anime> anime = animeService.getAnimeById(id);
        if(anime.isEmpty()){
            response.getErrors().add("Anime não encontrado!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        response.setData(animeService.convertAnimeToDto(anime.get()));
        return ResponseEntity.ok(response);
    }
}
