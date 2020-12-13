package org.nhhackaton.chun.batch.item;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.database.JpaItemWriter;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class JpaItemListWriter<T> extends JpaItemWriter<List<T>> {
    private final JpaItemWriter<T> jpaItemWriter;

    @Override
    public void write(List<? extends List<T>> items) {
        List<T> totalList = new ArrayList<>();

        for(List<T> list : items){
            totalList.addAll(list);
        }

        log.info(totalList.size() + "의 크기 데이터 저장 시작");
        jpaItemWriter.write(totalList);
        log.info("WRITER COMPLETE");
    }
}
