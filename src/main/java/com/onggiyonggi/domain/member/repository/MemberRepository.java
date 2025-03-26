package com.onggiyonggi.domain.member.repository;

import com.onggiyonggi.domain.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {

}