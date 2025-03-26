package com.onggiyonggi.domain.auth.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDto {

    @NotNull
    @Schema(description = "아이디", example = "mingmingmon")
    private String id;

    @NotNull
    @Schema(description = "비밀번호", example = "asdf1234!")
    private String password;

}