package com.onggiyonggi.global.auth;

import com.onggiyonggi.domain.member.domain.Member;
import com.onggiyonggi.domain.member.repository.MemberRepository;
import com.onggiyonggi.domain.member.service.MemberService;
import com.onggiyonggi.global.response.GeneralException;
import com.onggiyonggi.global.response.Status;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        return memberRepository.findById(id)
            .map(CustomUserDetails::new)
            .orElseThrow(() -> new GeneralException(Status.MEMBER_NOT_FOUND));
    }

}

