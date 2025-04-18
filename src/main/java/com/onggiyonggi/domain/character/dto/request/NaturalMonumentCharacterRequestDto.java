package com.onggiyonggi.domain.character.dto.request;

import com.onggiyonggi.domain.character.domain.NaturalMonumentCharacter;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NaturalMonumentCharacterRequestDto {

    @NotNull
    @Schema(description = "이름", example = "두루미")
    private String name;

    @NotNull
    @Schema(description = "설명", example = "두루미목 두루미과의 대형 조류. 1968년 5월 31일 천연기념물로 지정.")
    private String description;

    @NotNull
    @Schema(description = "스토리", example = "《하늘을 잇는 꼬마 두루미, 하쿠》\n" +
            "하쿠는 하늘과 땅을 잇는 전령 두루미 가문의 막내로 태어났어.\n" +
            "온몸이 하얀 깃털로 덮여 있고, 정수리엔 태양의 조각처럼 붉은 점이 있어.")
    private String story;

    @NotNull
    @NotEmpty
    @NotBlank
    @Schema(description = "imageURL", example = "http://~")
    private String imageURL;

    public static NaturalMonumentCharacter toEntity(NaturalMonumentCharacterRequestDto dto) {
        return NaturalMonumentCharacter.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .story(dto.getStory())
                .imageURL(dto.getImageURL())
                .build();
    }
}
