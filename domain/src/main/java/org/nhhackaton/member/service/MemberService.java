package org.nhhackaton.member.service;

import lombok.RequiredArgsConstructor;
import org.nhhackaton.member.entity.Member;
import org.nhhackaton.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member getMemberByIdentity(String identity){
        return memberRepository.findByIdentity(identity).orElseThrow(EntityExistsException::new);
    }

    public Member signInForTest(Member member) {
        return memberRepository.save(member);
    }
}
