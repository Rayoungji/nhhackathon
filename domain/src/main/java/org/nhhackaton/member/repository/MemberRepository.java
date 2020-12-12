package org.nhhackaton.member.repository;

import org.nhhackaton.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {

    Optional<Member> findByIdentity(String identity);
}
