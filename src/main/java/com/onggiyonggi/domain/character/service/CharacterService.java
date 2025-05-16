package com.onggiyonggi.domain.character.service;

import com.onggiyonggi.domain.character.domain.NaturalMonumentCharacter;
import com.onggiyonggi.domain.character.dto.request.NaturalMonumentCharacterRequestDto;
import com.onggiyonggi.domain.character.dto.response.NaturalMonumentCharacterResponseDto;
import com.onggiyonggi.domain.character.repository.CharacterRepository;
import com.onggiyonggi.global.response.GeneralException;
import com.onggiyonggi.global.response.Status;
import java.util.List;

import com.onggiyonggi.domain.store.domain.StoreRank;
import com.onggiyonggi.domain.store.dto.request.StoreRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterService {

    private final CharacterRepository characterRepository;

    public List<NaturalMonumentCharacterResponseDto> getAllCharacters() {
        return getAllCharactersEntity().stream()
            .map(NaturalMonumentCharacterResponseDto::toDto)
            .toList();
    }

    public List<NaturalMonumentCharacter> getAllCharactersEntity() {
        return getAllCharacterEntity();
    }

    public NaturalMonumentCharacter getCharacterById(Long id) {
        return findCharacterById(id);
    }

    private NaturalMonumentCharacter findCharacterById(Long id) {
        return characterRepository.findById(id)
            .orElseThrow(() -> new GeneralException(Status.CHARACTER_NOT_FOUND));
    }

    private List<NaturalMonumentCharacter> getAllCharacterEntity() {
        return characterRepository.findAll();
    }

    public Long createCharacter(NaturalMonumentCharacterRequestDto dto) {
        NaturalMonumentCharacter character = NaturalMonumentCharacterRequestDto.toEntity(dto);
        return characterRepository.save(character).getId();
    }

}
