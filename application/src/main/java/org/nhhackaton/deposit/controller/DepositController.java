package org.nhhackaton.deposit.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.nhhackaton.api.finaccount.dto.*;
import org.nhhackaton.deposit.dto.*;
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
    @ApiOperation("예치금 조회")
    public GetDepositResponse getDeposit(@PathVariable String identity) {
        Member loginMember = memberService.getMemberByIdentity(identity);
        Invest invest = investService.getDeposit(loginMember);
        return new GetDepositResponse(invest.getInvestPrice());
    }

    @PostMapping("/apply-invest/{identity}")
    @ApiOperation("대출하기")
    public BaseResponse applyInvest(@PathVariable String identity, @RequestBody ApplyInvestRequest applyInvestRequest) {

        Member loginMember = memberService.getMemberByIdentity(identity);

        investService.applyInvest(loginMember, applyInvestRequest.getInvestPrice());

        return new BaseResponse("200");
    }

    @GetMapping("/already-invest-list/{identity}")
    @ApiOperation("대출목록보기 - 투자완료 리스트")
    public List<AlreadyInvestResponse> getAlreadyInvestList(@PathVariable String identity) {
        Member member = memberService.getMemberByIdentity(identity);
        List<AlreadyInvestResponse> alreadyInvestResponses = new ArrayList<>();
        investService.getLoanTrueList(member).stream()
                .forEach(invest -> {
                    alreadyInvestResponses.add(
                            AlreadyInvestResponse.builder()
                                    .loanDate(invest.getLoanDate())
                                    .loanPrice(invest.getInvestPrice())
                                    .build()
                    );
                });
        return alreadyInvestResponses;
    }

    @GetMapping("/apply-invest-list/{identity}")
    @ApiOperation("대출목록보기 - 대출신청 리스트")
    public List<ApplyInvestResponse> getApplyInvestList(@PathVariable String identity){
        Member member = memberService.getMemberByIdentity(identity);
        List<ApplyInvestResponse> applyInvestResponses = new ArrayList<>();
        investService.getInvestsByMember(member).stream()
                .forEach(invest -> {
                    applyInvestResponses.add(
                            ApplyInvestResponse.builder()
                                    .investPrice(invest.getInvestPrice())
                                    .investDate(invest.getInvestDate())
                                    .build()
                    );
                });
        return applyInvestResponses;
    }
}
