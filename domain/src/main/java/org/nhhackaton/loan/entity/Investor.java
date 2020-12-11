package org.nhhackaton.loan.entity;

import org.nhhackaton.invest.entity.Invest;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Embeddable
public class Investor {

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "investor", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Invest> invests = new ArrayList<>();
}
