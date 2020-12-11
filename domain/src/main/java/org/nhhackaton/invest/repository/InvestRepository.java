package org.nhhackaton.invest.repository;

import org.nhhackaton.invest.entity.Invest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvestRepository extends JpaRepository<Invest, Long> {
}
