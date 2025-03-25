package com.onggiyonggi.domain.member.domain;

import com.onggiyonggi.domain.member.dto.request.MemberRequestDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    @Id
    @Column(name = "id", updatable = false)
    private String id;

    @NotNull
    private String password;

    @Column(unique = true)
    @NotNull
    private String nickName;

    public static Member toEntity(MemberRequestDto requestDto) {
        return Member.builder()
            .id(requestDto.getId())
            .password(requestDto.getPassword())
            .nickName(requestDto.getNickname())
            .build();
    }

}
