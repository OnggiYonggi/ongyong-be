package com.onggiyonggi.domain.member.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberRequestDto {

    @NotNull
    @NotEmpty
    @NotBlank
    @Schema(description = "아이디", example = "mingmingmon")
    String id;

    @NotNull
    @NotEmpty
    @NotBlank
    @Schema(description = "비밀번호", example = "asdf1234!")
    String password;

    @NotNull
    @NotEmpty
    @NotBlank
    @Schema(description = "닉네임", example = "민트")
    String nickname;

}
