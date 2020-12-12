package org.nhhackaton.interest.repository;

import org.nhhackaton.interest.entity.Interest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InterestRepository extends JpaRepository<Interest, Long> {
    List<Interest> findByBorrower(String borrower);
    List<Interest> findByInvestor(String investor);
}
