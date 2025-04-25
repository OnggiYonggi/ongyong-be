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

    public Boolean checkExistencesById(String id) {
        return existsById(id);
    }

    public Boolean checkExistencesByNickName(String nickname) {
        return existsByNickName(nickname);
    }

    private void save(Member member) {
        memberRepository.save(member);
    }

    private Member findMemberById(String id) {
        return memberRepository.findById(id)
            .orElseThrow(() -> new GeneralException(Status.MEMBER_NOT_FOUND));
    }

    private Member findMemberByNickName(String nickname) {
        return memberRepository.findByNickName(nickname)
                .orElseThrow(() -> new GeneralException(Status.MEMBER_NOT_FOUND));
    }

    private Boolean existsById(String id) {
        return memberRepository.existsById(id);
    }

    private Boolean existsByNickName(String nickname) {
        return memberRepository.existsByNickName(nickname);
    }

}
