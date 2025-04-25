package com.onggiyonggi.domain.character.service;

import com.onggiyonggi.domain.character.domain.NaturalMonumentCharacter;
import com.onggiyonggi.domain.character.dto.request.NaturalMonumentCharacterRequestDto;
import com.onggiyonggi.domain.character.repository.CharacterRepository;
import java.util.List;

import com.onggiyonggi.domain.store.domain.StoreRank;
import com.onggiyonggi.domain.store.dto.request.StoreRequestDto;
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

    public Long createCharacter(NaturalMonumentCharacterRequestDto dto) {
        NaturalMonumentCharacter character = NaturalMonumentCharacterRequestDto.toEntity(dto);
        return characterRepository.save(character).getId();
    }

}
