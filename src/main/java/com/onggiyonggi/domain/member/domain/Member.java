package com.onggiyonggi.domain.member.domain;

import com.onggiyonggi.domain.auth.dto.request.MemberRequestDto;
import com.onggiyonggi.global.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Collections;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member extends BaseEntity {

    @Id
    @Column(name = "id", updatable = false)
    private String id;

    @NotNull
    private String password;

    @Column(unique = true)
    @NotNull
    private String nickName;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role;

    public static Member toEntity(MemberRequestDto requestDto, PasswordEncoder passwordEncoder) {
        return Member.builder()
            .id(requestDto.getId())
            .password(passwordEncoder.encode(requestDto.getPassword()))
            .role(Role.CUSTOMER) // 추후 가게 사장님 로그인 버전 만들 때 수정 필요
            .nickName(requestDto.getNickname())
            .build();
    }

}
