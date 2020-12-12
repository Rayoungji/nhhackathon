package org.nhhackaton.member.controller;

import lombok.RequiredArgsConstructor;
import org.nhhackaton.api.finaccount.dto.OpenFinAccountRequest;
import org.nhhackaton.deposit.dto.BaseResponse;
import org.nhhackaton.invest.service.InvestService;
import org.nhhackaton.member.dto.DocumentRequest;
import org.nhhackaton.member.dto.SetAccountRequest;
import org.nhhackaton.member.entity.Member;
import org.nhhackaton.member.service.MemberService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final InvestService investService;

    @GetMapping("/{identity}")
    public boolean validate(@PathVariable String identity){
        return memberService.validate(identity);
    }

    @PostMapping("/document/{identity}")
    public String getUrl(@PathVariable String identity,
                         MultipartFile multipartFile) {
        return memberService.getUrl(multipartFile, identity);
    }

    @PostMapping("/set-account/{identity}")
    public BaseResponse setAccount(@PathVariable String identity, @RequestBody SetAccountRequest setAccountRequest) {
        Member loginMember = memberService.getMemberByIdentity(identity);
        loginMember.setAccountInfo(setAccountRequest.getBncd(), setAccountRequest.getAcno());
        OpenFinAccountRequest openFinAccountRequest = OpenFinAccountRequest.builder()
                .DrtrRgyn("Y")
                .BrdtBrno(loginMember.getBirthday())
                .Bncd(setAccountRequest.getBncd())
                .Acno(setAccountRequest.getAcno()).build();

        investService.makeFinAccount(loginMember, openFinAccountRequest);

        return new BaseResponse("200");
    }

    @PostMapping
    public void saveDocuments(@RequestBody List<DocumentRequest> documentRequests){
        memberService.saveDocument(
                documentRequests.stream()
                .map(DocumentRequest::of)
                .collect(Collectors.toList())
        );
    }
}
