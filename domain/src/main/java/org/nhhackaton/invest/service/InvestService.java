package org.nhhackaton.invest.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.nhhackaton.api.easypament.TransferApiService;
import org.nhhackaton.api.easypament.dto.DrawingTransferRequest;
import org.nhhackaton.api.easypament.dto.DrawingTransferResponse;
import org.nhhackaton.api.finaccount.FinAccountApiService;
import org.nhhackaton.api.finaccount.dto.CheckFinAccountRequest;
import org.nhhackaton.api.finaccount.dto.CheckFinAccountResponse;
import org.nhhackaton.api.finaccount.dto.OpenFinAccountRequest;

import org.nhhackaton.api.finaccount.dto.OpenFinAccountResponse;
import org.nhhackaton.api.p2p.P2PApiService;
import org.nhhackaton.api.p2p.dto.VirtualAccountRequest;
import org.nhhackaton.api.p2p.dto.VirtualAccountResponse;
import org.nhhackaton.invest.entity.Invest;
import org.nhhackaton.invest.repository.InvestRepository;
import org.nhhackaton.member.entity.Member;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class InvestService {
    private final FinAccountApiService finAccountApiService;
    private final TransferApiService transferApiService;
    private final P2PApiService p2PApiService;
    private final InvestRepository investRepository;


    @Transactional
    public void applyInvest(Member member, String investPrice) {

        //투자 핀어카운트 -> 핀테크 약정계좌
        DrawingTransferRequest drawingTransferRequest = DrawingTransferRequest.builder()
                .MractOtlt("입금되었습니다")
                .DractOtlt("출금되었습니다")
                .Tram(investPrice)
                .FinAcno(member.getFinAccount()).build();
        ResponseEntity<DrawingTransferResponse> drawingTransferResponse = transferApiService.draw(drawingTransferRequest);
        System.out.println("투자 등록 일자: "+ drawingTransferResponse.getBody().getHeader().getTsymd());  //투자 등록일자
        System.out.println("에러메세지: "+ drawingTransferResponse.getBody().getHeader().getRsms());

        if (member.getInvestVirtualAccount() == null) {
            //투자자용 가상계좌 발급
            VirtualAccountRequest virtualAccountRequest = VirtualAccountRequest.builder()
                    .P2PVractUsg("1")
                    .P2PCmtmNo("0000000000")
                    .ChidSqno("0000000000")
                    .InvstBrwNm(member.getName()).build();

            makeInvestVirtualAccount(member, virtualAccountRequest);
        }

        //TODO 핀테크 약정계좌 -> 투자용 가상계좌로 투자금 이체는 현재 테스트 불가

        Invest invest = Invest.builder()
                .isLoan(false)
                .investPrice(investPrice)
                .investMember(member)
                .investDate(drawingTransferResponse.getBody().getHeader().getTsymd()).build();

        investRepository.save(invest);

    }

    @Transactional
    public Invest getDeposit(Member member) {
        List<Invest> invests = investRepository.findInvestByInvestMemberAndIsLoanIsFalse(member);
        log.warn(" ========= GET DEPOSIT START =============");
        int depositPrice = invests.stream().mapToInt(invest -> Integer.parseInt(invest.getInvestPrice())).sum();
        Invest investResponse = Invest.builder()
                .investMember(member)
                .investPrice(String.valueOf(depositPrice)).build();
        log.warn("예치금 금액 : " + depositPrice);
        log.warn(" ========= GET DEPOSIT END =============");

        return investResponse;
    }

    public List<Invest> getLoanTrueList(Member member) {
        return investRepository.findInvestByInvestMemberAndIsLoanIsTrue(member);
    }

    public List<Invest> getInvestsByMember(Member member) {
        return investRepository.findInvestByInvestMember(member);
    }

    @Transactional
    public void makeFinAccount(Member member, OpenFinAccountRequest openFinAccountRequest,String bncd, String acno) {
        ResponseEntity<OpenFinAccountResponse> open = finAccountApiService.open(openFinAccountRequest);
        log.warn(" ========= MAKE FIN ACCOUNT START =============");
        System.out.println("OpenFin Response: " + open.getBody().getHeader().getRsms());  //checkFin 요청값

        CheckFinAccountRequest checkFinAccountRequest = CheckFinAccountRequest.builder()
                .Rgno(open.getBody().getRgno())
                .BrdtBrno(member.getBirthday()).build();

        ResponseEntity<CheckFinAccountResponse> check = finAccountApiService.check(checkFinAccountRequest);
        System.out.println("CheckFin Response: " + check.getBody().getHeader().getRsms());  //InvestFin 발급 완료

        member.setFinAccount(check.getBody().getFinAcno());
        member.setAccountInfo(bncd, acno);
        log.warn(" ========= MAKE INVEST FIN ACCOUNT END =============");
    }

    @Transactional
    public void makeInvestVirtualAccount(Member member, VirtualAccountRequest virtualAccountRequest) {
        log.warn(" ========= MAKE INVEST VIRTUAL ACCOUNT START =============");
        ResponseEntity<VirtualAccountResponse> virtualAccountResponse = p2PApiService.create(virtualAccountRequest);
        System.out.println("VirtualAccount: " + virtualAccountResponse.getBody().getHeader().getRsms()); //가상계좌 발급
        member.setInvestVirtualAccount(virtualAccountResponse.getBody().getVran());
        log.warn(" ========= MAKE INVEST VIRTUAL ACCOUNT START =============");
    }
}
