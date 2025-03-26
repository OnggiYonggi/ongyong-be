package com.onggiyonggi.domain.member.service;

import com.onggiyonggi.domain.member.domain.Member;
import com.onggiyonggi.domain.auth.dto.request.MemberRequestDto;
import com.onggiyonggi.domain.member.repository.MemberRepository;
import com.onggiyonggi.global.response.GeneralException;
import com.onggiyonggi.global.response.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void saveMember(Member member) {
        save(member);
    }

    public Member getMemberById(String id) {
        return findMemberById(id);
    }

    private void save(Member member) {
        memberRepository.save(member);
    }

    private Member findMemberById(String id) {
        return memberRepository.findById(id)
            .orElseThrow(() -> new GeneralException(Status.MEMBER_NOT_FOUND));
    }

}
