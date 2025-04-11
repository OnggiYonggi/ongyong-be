package com.onggiyonggi.domain.data.service;

import com.onggiyonggi.domain.character.service.CharacterService;
import com.onggiyonggi.domain.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DataService {

    private final StoreService storeService;
    private final CharacterService characterService;


}
