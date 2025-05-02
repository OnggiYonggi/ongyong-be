package com.onggiyonggi.domain.collection.domain;

import com.onggiyonggi.domain.auth.dto.request.MemberRequestDto;
import com.onggiyonggi.domain.character.domain.NaturalMonumentCharacter;
import com.onggiyonggi.domain.member.domain.Member;
import com.onggiyonggi.domain.member.domain.Role;
import com.onggiyonggi.global.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Collection extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "character_id", nullable = false)
    private NaturalMonumentCharacter naturalMonumentCharacter;

    public static Collection toEntity(Member member, NaturalMonumentCharacter character) {
        return Collection.builder()
            .member(member)
            .naturalMonumentCharacter(character)
            .build();
    }

}
