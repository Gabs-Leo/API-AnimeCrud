package com.fatec.animecrud.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record AnimeDto(
        @NotNull(message = "Name can't be empty.")
        String name,
        @NotNull(message = "Description can't be empty.")
        String description,
        @NotNull(message = "Image can't be empty.")
        String image,
        @NotNull(message = "Number of Episodes can't be empty.")
        int numberOfEpisodes,
        @NotNull(message = "Finished status can't be empty.")
        boolean finished
) { }
