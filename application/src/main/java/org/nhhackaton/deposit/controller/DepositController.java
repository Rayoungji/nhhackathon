package org.nhhackaton.deposit.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.nhhackaton.api.finaccount.dto.*;
import org.nhhackaton.deposit.dto.AlreadyInvestResponse;
import org.nhhackaton.deposit.dto.ApplyInvestResponse;
import org.nhhackaton.deposit.dto.BaseResponse;
import org.nhhackaton.deposit.dto.GetDepositResponse;
import org.nhhackaton.invest.entity.Invest;
import org.nhhackaton.invest.service.InvestService;
import org.nhhackaton.member.entity.Member;
import org.nhhackaton.member.service.MemberService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/deposit")
@RestController
@RequiredArgsConstructor
@Slf4j
public class DepositController {

    private final MemberService memberService;
    private final InvestService investService;

    @GetMapping("/{identity}")
    public GetDepositResponse getDeposit(@PathVariable String identity) {
        Member loginMember = memberService.getMemberByIdentity(identity);
        Invest invest = investService.getDeposit(loginMember);
        return new GetDepositResponse(invest.getInvestPrice());
    }

    @PostMapping("/apply-invest/{identity}")
    public BaseResponse applyInvest(@PathVariable String identity, @RequestBody ApplyInvestRequest applyInvestRequest) {

        Member loginMember = memberService.getMemberByIdentity(identity);

        if (loginMember.getInvestFinAccount() == null) {
            OpenFinAccountRequest openFinAccountRequest = OpenFinAccountRequest.builder()
                    .DrtrRgyn("Y")
                    .BrdtBrno(loginMember.getBirthday())
                    .Bncd(applyInvestRequest.getBncd())
                    .Acno(applyInvestRequest.getAcno()).build();

            investService.makeInvestFinAccount(loginMember, openFinAccountRequest);
        }

        investService.applyInvest(loginMember, applyInvestRequest.getInvestPrice());

        return new BaseResponse("200");
    }

    @GetMapping("/already-invest-list/{identity}")
    public List<AlreadyInvestResponse> getAlreadyInvestList(@PathVariable String identity) {
        Member member = memberService.getMemberByIdentity(identity);
        List<AlreadyInvestResponse> alreadyInvestResponses = new ArrayList<>();
        investService.getLoanTrueList(member).stream()
                .map(invest -> {
                    return alreadyInvestResponses.add(
                            AlreadyInvestResponse.builder()
                                    .loanPrice(invest.getInvestPrice())
                                    .loanDate(invest.getLoanDate())
                                    .build()
                    );
                });
        return alreadyInvestResponses;
    }

    @GetMapping("/apply-invest-list/{identity}")
    public List<ApplyInvestResponse> getApplyInvestList(@PathVariable String identity){
        Member member = memberService.getMemberByIdentity(identity);
        List<ApplyInvestResponse> applyInvestResponses = new ArrayList<>();
        investService.getInvestsByMember(member).stream()
                .map(invest -> {
                    return applyInvestResponses.add(
                            ApplyInvestResponse.builder()
                                    .investPrice(invest.getInvestPrice())
                                    .investDate(invest.getInvestDate())
                                    .build()
                    );
                });
        return applyInvestResponses;
    }
}
