package org.nhhackaton.invest.service;

import lombok.RequiredArgsConstructor;
import org.nhhackaton.invest.repository.InvestRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InvestService {
    private final InvestRepository investRepository;

    public void getDeposit(String identity) {

    }
}
