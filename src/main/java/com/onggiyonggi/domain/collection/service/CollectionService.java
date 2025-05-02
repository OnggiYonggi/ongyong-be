package com.onggiyonggi.domain.collection.service;

import com.onggiyonggi.domain.character.domain.NaturalMonumentCharacter;
import com.onggiyonggi.domain.character.repository.CharacterRepository;
import com.onggiyonggi.domain.character.service.CharacterService;
import com.onggiyonggi.domain.collection.domain.Collection;
import com.onggiyonggi.domain.collection.repository.CollectionRepository;
import com.onggiyonggi.domain.member.domain.Member;
import com.onggiyonggi.domain.pet.domain.Pet;
import com.onggiyonggi.domain.pet.dto.response.PetResponseDto;
import com.onggiyonggi.global.auth.CustomUserDetails;
import com.onggiyonggi.global.response.GeneralException;
import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CollectionService {

    private final CollectionRepository collectionRepository;
    private final CharacterService characterService;

    public Long addCollection(CustomUserDetails customUserDetails, Long characterId) {
        Member member = customUserDetails.getMember();
        NaturalMonumentCharacter character = characterService.getCharacterById(characterId);
        return saveCollection(member, character).getId();
    }

    private Collection saveCollection(Member member, NaturalMonumentCharacter character) {
        return collectionRepository.save(Collection.toEntity(member, character));
    }

}
