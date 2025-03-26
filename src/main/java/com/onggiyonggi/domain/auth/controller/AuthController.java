package com.onggiyonggi.domain.auth.controller;

import com.onggiyonggi.domain.auth.dto.request.LoginRequestDto;
import com.onggiyonggi.domain.auth.dto.response.JwtResponseDto;
import com.onggiyonggi.domain.auth.service.AuthService;
import com.onggiyonggi.domain.auth.dto.request.MemberRequestDto;
import com.onggiyonggi.domain.member.domain.Member;
import com.onggiyonggi.global.auth.CustomUserDetails;
import com.onggiyonggi.global.response.ApiResponse;
import com.onggiyonggi.global.response.Status;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Tag(name = "auth", description = "인증 관련 api")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    @Operation(summary = "로그인")
    public ApiResponse<?> login(
        @RequestBody @Valid LoginRequestDto requestDto) {
        JwtResponseDto jwtResponseDTO = authService.login(requestDto);
        return ApiResponse.success(Status.OK.getCode(),
            Status.OK.getMessage(), jwtResponseDTO);
    }

    @PostMapping("/signUp")
    @Operation(summary = "회원가입")
    public ApiResponse<?> signUp(
        @RequestBody @Valid MemberRequestDto memberRequestDto) {
        authService.signUp(memberRequestDto);
        return ApiResponse.success(Status.OK.getCode(),
            Status.CREATED.getMessage(), null);
    }

    @GetMapping("/test")
    @Operation(summary = "로그인 테스트 API", description = "로그인 여부를 확인할 수 있는 API입니다. 회원의 닉네임을 리턴합니다.")
    public ApiResponse<String> test(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        Member member = customUserDetails.getMember();
        String result = member.getNickName();
        return ApiResponse.success(Status.OK.getCode(),
            Status.OK.getMessage(), result);
    }

}
