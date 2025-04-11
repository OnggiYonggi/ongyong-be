package com.onggiyonggi.domain.character.repository;

import com.onggiyonggi.domain.character.domain.NaturalMonumentCharacter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterRepository extends JpaRepository<NaturalMonumentCharacter, Long> {

}
