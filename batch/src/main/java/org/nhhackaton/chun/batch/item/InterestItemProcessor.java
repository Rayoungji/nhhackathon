package org.nhhackaton.chun.batch.item;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.nhhackaton.chun.dao.InterestReaderDAO;
import org.nhhackaton.fcm.service.FirebaseCloudMessageSender;
import org.nhhackaton.interest.entity.Interest;
import org.nhhackaton.interest.enums.InterestState;
import org.nhhackaton.interest.service.InterestService;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class InterestItemProcessor implements ItemProcessor<List<InterestReaderDAO>, List<Interest>> {
    private final InterestService interestService;
    private final FirebaseCloudMessageSender sender;

    @Override
    public List<Interest> process(List<InterestReaderDAO> itemList) throws Exception {
        if(itemList.size() == 0)
            return null;

        List<Interest> list = new ArrayList<>();
        for(InterestReaderDAO item : itemList) {
            if (interestService.executeInterestRepayment(item.getLoanNo())) {
                sender.sendMessageTo(item.getAccessToken(), RepayMessage.SUCCESS.getTitle(), RepayMessage.SUCCESS.getBody());
                list.add(Interest.builder()
                        .borrower(item.getBorrower())
                        .investor(item.getInvestor())
                        .repaymentDate(LocalDate.now())
                        .repaymentPrice(item.getInterest())
                        .state(InterestState.SUCCESS)
                        .build());
            }
            sender.sendMessageTo(item.getAccessToken(), RepayMessage.FAIL.getTitle(), RepayMessage.FAIL.getBody());
            list.add(Interest.builder()
                    .borrower(item.getBorrower())
                    .investor(item.getInvestor())
                    .repaymentDate(LocalDate.now())
                    .repaymentPrice(item.getInterest())
                    .state(InterestState.FAIL)
                    .build());
        }
        return list;
    }

    @Getter
    @AllArgsConstructor
    private enum RepayMessage{
        SUCCESS("이자 상환이 완료되었습니다.", "이자 상환 날짜에 맞춰 가상 계좌에 입금하신 금액을 이자에 맞게 상환되었음을 알려드립니다.")
        ,FAIL("이자 상환에 실패하였습니다.", "이자 상환 날짜입니다. 가상 계좌에 잔액이 부족하여 이자를 입금해주시기 바랍니다.");

        private final String title;
        private final String body;
    }
}
