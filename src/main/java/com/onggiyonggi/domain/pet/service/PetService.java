package com.onggiyonggi.domain.pet.service;

import com.onggiyonggi.domain.character.domain.NaturalMonumentCharacter;
import com.onggiyonggi.domain.character.service.CharacterService;
import com.onggiyonggi.domain.member.domain.Member;
import com.onggiyonggi.domain.pet.domain.Pet;
import com.onggiyonggi.domain.pet.dto.response.PetResponseDto;
import com.onggiyonggi.domain.pet.repository.PetRepository;
import com.onggiyonggi.global.auth.CustomUserDetails;
import com.onggiyonggi.global.response.GeneralException;
import com.onggiyonggi.global.response.Status;
import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PetService {

    private final PetRepository petRepository;
    private final CharacterService characterService;

    public PetResponseDto createPet(CustomUserDetails customUserDetails) {
        Member member = customUserDetails.getMember();
        try {
            Pet pet = getPetByMemberId(member.getId());
            return PetResponseDto.toDto(pet);
        } catch (GeneralException generalException) {
            List<NaturalMonumentCharacter> naturalMonumentCharacterList = characterService.getAllCharactersEntity();
            int randomIndex = new Random().nextInt(naturalMonumentCharacterList.size());
            NaturalMonumentCharacter naturalMonumentCharacter = naturalMonumentCharacterList.get(randomIndex);
            Pet pet = Pet.create(member, naturalMonumentCharacter);
            savePet(pet);
            return PetResponseDto.toDto(pet);
        }
    }

    public PetResponseDto getMyPet(CustomUserDetails customUserDetails) {
        Member member = customUserDetails.getMember();
        Pet myPet = getPetByMemberId(member.getId());
        return PetResponseDto.toDto(myPet);
    }

    public Pet getPetByMemberId(CustomUserDetails customUserDetails) {
        Member member = customUserDetails.getMember();
        return  getPetByMemberId(member.getId());
    }

    public void deletePetEntity(Pet pet) {
        deletePet(pet);
    }

    public PetResponseDto updateAffinity(CustomUserDetails customUserDetails) {
        Member member = customUserDetails.getMember();
        Pet myPet = getPetByMemberId(member.getId());
        myPet.updateAffinity();
        savePet(myPet);
        return PetResponseDto.toDto(myPet);
    }

    private Pet getPetByMemberId(String memberId) {
        return petRepository.findByMemberId(memberId)
            .orElseThrow(() -> new GeneralException(Status.PET_NOT_FOUND));
    }

    private Pet savePet(Pet pet) {
        return petRepository.save(pet);
    }

    private void deletePet(Pet pet) {
        petRepository.delete(pet);
    }

}
