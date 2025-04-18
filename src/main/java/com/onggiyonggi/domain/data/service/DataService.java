package com.onggiyonggi.domain.data.service;

import com.onggiyonggi.domain.character.dto.request.NaturalMonumentCharacterRequestDto;
import com.onggiyonggi.domain.character.service.CharacterService;
import com.onggiyonggi.domain.store.domain.StoreRank;
import com.onggiyonggi.domain.store.dto.request.StoreRequestDto;
import com.onggiyonggi.domain.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DataService {

    private final StoreService storeService;
    private final CharacterService characterService;

    public void saveStores(List<StoreRequestDto> storeList) {
        for (StoreRequestDto dto : storeList) {
            StoreRank defaultRank = StoreRank.ROOKIE;
            storeService.createStore(dto, defaultRank);
        }
    }

    public void saveCharacters(List<NaturalMonumentCharacterRequestDto> characterList) {
        for (NaturalMonumentCharacterRequestDto dto : characterList) {
            characterService.createCharacter(dto);
        }
    }


}
