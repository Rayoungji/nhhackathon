package org.nhhackaton.loan.repository;

import org.nhhackaton.loan.entity.Loan;
import org.nhhackaton.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

    Optional<Loan> findFirstByOrderByIdDesc();
    List<Loan> findByReceiverIdentity(String identity);
    List<Loan> findByLoanNo(long loanNo);

}
