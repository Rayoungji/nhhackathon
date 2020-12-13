package org.nhhackaton.chun.batch.item;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.nhhackaton.interest.entity.Interest;
import org.nhhackaton.interest.repository.InterestRepository;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

@Slf4j
//@Configuration
@RequiredArgsConstructor
public class InterestItemWriter extends JpaItemWriter<List<Interest>> {
    private final InterestRepository interestRepository;
    @Override
    protected void doWrite(EntityManager entityManager, List<? extends List<Interest>> items) {
        List<Interest> totalList = new ArrayList<>();

        for(List<Interest> list : items){
            totalList.addAll(list);
        }

        interestRepository.saveAll(totalList);
    }
}
