package com.fatec.animecrud.controllers;

import com.fatec.animecrud.dtos.AnimeDto;
import com.fatec.animecrud.services.AnimeService;
import com.fatec.animecrud.utils.Response;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
