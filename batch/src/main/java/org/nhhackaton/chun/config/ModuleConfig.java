package org.nhhackaton.chun.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.nhhackaton.api.ApiCallService;
import org.nhhackaton.api.p2p.P2PApiService;
import org.nhhackaton.chun.dao.InterestReaderDAOMapper;
import org.nhhackaton.fcm.service.FirebaseCloudMessageSender;
import org.nhhackaton.interest.repository.InterestRepository;
import org.nhhackaton.interest.service.InterestService;
import org.nhhackaton.loan.repository.LoanRepository;
import org.nhhackaton.member.repository.MemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@RequiredArgsConstructor
public class ModuleConfig {
    private final LoanRepository loanRepository;
    private final MemberRepository memberRepository;
    private final InterestRepository interestRepository;
    @Bean
    public InterestService interestService(){
        return new InterestService(loanRepository, memberRepository, p2PApiService(), interestRepository);
    }

    @Bean
    public FirebaseCloudMessageSender firebaseCloudMessageSender(){
        return new FirebaseCloudMessageSender(objectMapper());
    }

    @Bean
    public P2PApiService p2PApiService(){
        return new P2PApiService(apiCallService());
    }

    @Bean
    public ApiCallService apiCallService(){
        return new ApiCallService(restTemplate());
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }

    @Bean
    public InterestReaderDAOMapper interestReaderDAOMapper(){
        return new InterestReaderDAOMapper();
    }
}
