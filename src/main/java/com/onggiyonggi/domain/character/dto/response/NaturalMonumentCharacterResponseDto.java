package com.onggiyonggi.domain.character.dto.response;

import com.onggiyonggi.domain.character.domain.NaturalMonumentCharacter;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class NaturalMonumentCharacterResponseDto {

    private Long id;
    private String name;
    private String description;
    private String story;
    private String imageURL;

    public static NaturalMonumentCharacterResponseDto toDto(NaturalMonumentCharacter character) {
        return NaturalMonumentCharacterResponseDto.builder()
            .id(character.getId())
            .name(character.getName())
            .description(character.getDescription())
            .story(character.getStory())
            .imageURL(character.getImageURL())
            .build();
    }
}
