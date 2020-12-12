package org.nhhackaton.invest.repository;

import org.nhhackaton.invest.entity.Invest;
import org.nhhackaton.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvestRepository extends JpaRepository<Invest, Long> {

    List<Invest> findInvestByInvestMemberAndIsLoanIsFalse(Member member);
}
