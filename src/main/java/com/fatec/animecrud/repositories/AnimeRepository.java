package com.fatec.animecrud.repositories;

import com.fatec.animecrud.entities.Anime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimeRepository extends JpaRepository<Anime, Long> {

}
