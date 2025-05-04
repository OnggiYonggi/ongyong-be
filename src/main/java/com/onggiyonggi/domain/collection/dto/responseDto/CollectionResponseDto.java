package com.onggiyonggi.domain.collection.dto.responseDto;

import com.onggiyonggi.domain.character.dto.response.NaturalMonumentCharacterResponseDto;
import com.onggiyonggi.domain.collection.domain.Collection;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CollectionResponseDto {

    private Long id;
    private NaturalMonumentCharacterResponseDto characterResponseDto;

    public static CollectionResponseDto toDto(Collection collection) {
        return CollectionResponseDto.builder()
            .id(collection.getId())
            .characterResponseDto(NaturalMonumentCharacterResponseDto.toDto(collection.getNaturalMonumentCharacter()))
            .build();
    }

}
