package org.nhhackaton.loan.repository;

import org.nhhackaton.loan.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan, Long> {
}
