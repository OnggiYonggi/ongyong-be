package com.onggiyonggi.domain.auth.service;

import com.onggiyonggi.domain.auth.domain.Token;
import com.onggiyonggi.domain.auth.dto.request.LoginRequestDto;
import com.onggiyonggi.domain.auth.dto.request.MemberRequestDto;
import com.onggiyonggi.domain.auth.dto.response.IdExistenceResponseDto;
import com.onggiyonggi.domain.auth.dto.response.JwtResponseDto;
import com.onggiyonggi.domain.auth.dto.response.NickNameExistenceResponseDto;
import com.onggiyonggi.domain.member.domain.Member;
import com.onggiyonggi.domain.member.domain.Role;
import com.onggiyonggi.domain.member.service.MemberService;
import com.onggiyonggi.global.auth.JwtProvider;
import com.onggiyonggi.global.response.GeneralException;
import com.onggiyonggi.global.response.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    public void signUp(MemberRequestDto requestDto){
        Member member = Member.toEntity(requestDto, passwordEncoder);
        memberService.saveMember(member);
    }

    public JwtResponseDto login(LoginRequestDto requestDto) {
        String id = requestDto.getId();
        String password = requestDto.getPassword();

        Member member = memberService.getMemberById(id);

        if (!passwordEncoder.matches(password, member.getPassword())) {
            throw new GeneralException(Status.MEMBER_UNAUTHORIZED);
        }

        Token token = jwtProvider.generateToken(id, Role.CUSTOMER);

        return JwtResponseDto.builder()
            .accessToken(token.getAccessToken())
            .refreshToken(token.getRefreshToken())
            .build();
    }

    public IdExistenceResponseDto checkId(String id) {
        Boolean isExist = memberService.checkExistencesById(id);
        return IdExistenceResponseDto.builder()
            .isExist(isExist)
            .build();
    }

    public NickNameExistenceResponseDto checkNickName(String nickname) {
        Boolean isExist = memberService.checkExistencesByNickName(nickname);
        return NickNameExistenceResponseDto.builder()
                .isExist(isExist)
                .build();
    }

    public Member getCurrentMember() {
        String id = jwtProvider.getUsernameFromAuthentication();
        return memberService.getMemberById(id);
    }

}
