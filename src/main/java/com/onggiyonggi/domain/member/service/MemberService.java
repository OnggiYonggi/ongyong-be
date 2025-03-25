package com.onggiyonggi.domain.member.service;

import com.onggiyonggi.domain.member.domain.Member;
import com.onggiyonggi.domain.member.dto.request.MemberRequestDto;
import com.onggiyonggi.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void createMember(MemberRequestDto memberRequestDto) {
        Member newMember = Member.toEntity(memberRequestDto);
        save(newMember);
    }

    public void save(Member member) {
        memberRepository.save(member);
    }

}
