package com.onggiyonggi.domain.character.service;

import com.onggiyonggi.domain.character.domain.NaturalMonumentCharacter;
import com.onggiyonggi.domain.character.repository.CharacterRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterService {

    private final CharacterRepository characterRepository;

    public List<NaturalMonumentCharacter> getAllCharacters() {
        return getAllCharacterEntity();
    }

    private List<NaturalMonumentCharacter> getAllCharacterEntity() {
        return characterRepository.findAll();
    }

}
