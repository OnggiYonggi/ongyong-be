package com.onggiyonggi.domain.pet.dto.response;

import com.onggiyonggi.domain.character.domain.NaturalMonumentCharacter;
import com.onggiyonggi.domain.pet.domain.Pet;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class PetResponseDto {

    private Long id;
    private NaturalMonumentCharacter naturalMonumentCharacter;
    private Float affinity;

    public static PetResponseDto toDto(Pet pet) {
        return PetResponseDto.builder()
            .id(pet.getId())
            .naturalMonumentCharacter(pet.getNaturalMonumentCharacter())
            .affinity(pet.getAffinity())
            .build();
    }

}
