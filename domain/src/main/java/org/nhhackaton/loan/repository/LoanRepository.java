package org.nhhackaton.loan.repository;

import org.nhhackaton.loan.entity.Loan;
import org.nhhackaton.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LoanRepository extends JpaRepository<Loan, Long> {

    Optional<Loan> findFirstByOrderByIdDesc();
    List<Loan> findByReceiverIdentity(String identity);
    List<Loan> findByLoanNo(String loanNo);

}
