package com.onggiyonggi.domain.member.controller;

import com.onggiyonggi.domain.auth.dto.request.LoginRequestDto;
import com.onggiyonggi.domain.auth.dto.request.MemberRequestDto;
import com.onggiyonggi.domain.auth.dto.response.JwtResponseDto;
import com.onggiyonggi.domain.member.service.MemberService;
import com.onggiyonggi.global.response.ApiResponse;
import com.onggiyonggi.global.response.Status;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
@Tag(name = "Member", description = "멤버 api")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/test")
    @Operation(summary = "test")
    public ApiResponse<?> test() {
        return ApiResponse.success(Status.OK.getCode(),
            Status.OK.getMessage(), null);
    }

}
