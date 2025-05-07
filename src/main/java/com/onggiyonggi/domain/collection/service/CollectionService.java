package com.onggiyonggi.domain.collection.service;

import com.onggiyonggi.domain.character.domain.NaturalMonumentCharacter;
import com.onggiyonggi.domain.character.repository.CharacterRepository;
import com.onggiyonggi.domain.character.service.CharacterService;
import com.onggiyonggi.domain.collection.domain.Collection;
import com.onggiyonggi.domain.collection.dto.responseDto.CollectionResponseDto;
import com.onggiyonggi.domain.collection.repository.CollectionRepository;
import com.onggiyonggi.domain.member.domain.Member;
import com.onggiyonggi.domain.pet.domain.Pet;
import com.onggiyonggi.domain.pet.dto.response.PetResponseDto;
import com.onggiyonggi.domain.pet.service.PetService;
import com.onggiyonggi.global.auth.CustomUserDetails;
import com.onggiyonggi.global.response.GeneralException;
import com.onggiyonggi.global.response.Status;
import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CollectionService {

    private final CollectionRepository collectionRepository;
    private final CharacterService characterService;
    private final PetService petService;

    public Long addCollection(CustomUserDetails customUserDetails) {
        Member member = customUserDetails.getMember();
        Pet pet = petService.getPetByMemberId(customUserDetails);
        NaturalMonumentCharacter character = characterService.getCharacterById(pet.getNaturalMonumentCharacter().getId());
        Collection collection = findByMemberIdAndCharacterId(member.getId(), character.getId());
        if (collection != null) {
            throw new GeneralException(Status.COLLECTION_ALREADY_EXIST);
        }
        petService.deletePetEntity(pet);
        return saveCollection(member, character).getId();
    }

    public List<CollectionResponseDto> getMyPet(CustomUserDetails customUserDetails) {
        Member member = customUserDetails.getMember();
        List<Collection> collections = findAllByMemberId(member.getId());
        return collections.stream().map(collection -> CollectionResponseDto.toDto(collection)).toList();
    }

    private Collection saveCollection(Member member, NaturalMonumentCharacter character) {
        return collectionRepository.save(Collection.toEntity(member, character));
    }

    private List<Collection> findAllByMemberId(String memberId) {
        return collectionRepository.findAllByMemberId(memberId);
    }

    private Collection findByMemberIdAndCharacterId(String memberId, Long characterId) {
        return collectionRepository.findByMemberIdAndNaturalMonumentCharacterId(memberId, characterId);
    }

}
