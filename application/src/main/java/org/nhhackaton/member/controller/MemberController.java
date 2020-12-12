package org.nhhackaton.member.controller;

import lombok.RequiredArgsConstructor;
import org.nhhackaton.api.finaccount.dto.OpenFinAccountRequest;
import org.nhhackaton.deposit.dto.BaseResponse;
import org.nhhackaton.errors.exception.MemberAlreadyExsistException;
import org.nhhackaton.errors.exception.NotCorrectPasswordException;
import org.nhhackaton.errors.exception.NotExistIdentityException;
import org.nhhackaton.invest.service.InvestService;
import org.nhhackaton.member.dto.DocumentRequest;
import org.nhhackaton.member.dto.SetAccountRequest;
import org.nhhackaton.member.dto.SignInRequest;
import org.nhhackaton.member.dto.SignUpRequest;
import org.nhhackaton.member.entity.Member;
import org.nhhackaton.member.service.MemberService;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/signup")
    public void signUp(@RequestBody SignUpRequest signUpRequest) {

        if (memberService.getMemberByIdentity(signUpRequest.getIdentity()) != null) {
            throw new MemberAlreadyExsistException();
        }
        Member member = Member.builder()
                .identity(signUpRequest.getIdentity())
                .password(signUpRequest.getPassword())
                .birthday(signUpRequest.getBirthday())
                .name(signUpRequest.getName())
                .build();
        memberService.saveMember(member);

    }

    @PostMapping("/signin")
    public ResponseEntity<Member> signIn(@RequestBody SignInRequest signInRequest) {

        if (memberService.getMemberByIdentity(signInRequest.getIdentity()) == null) {
            throw new NotExistIdentityException();
        } else if (!(memberService.getMemberByIdentity(signInRequest.getIdentity()).getPassword()).equals(signInRequest.getPassword())) {
            throw new NotCorrectPasswordException();
        }
        Member member = Member.builder()
                .identity(signInRequest.getIdentity())
                .password(signInRequest.getPassword())
                .build();
        Member loginMember = memberService.login(member);

        return ResponseEntity.ok(loginMember);
    }

    @GetMapping("/member-profile/{identity}")
    public ResponseEntity<Member> memberProfile(@PathVariable String identity) {
        Member memberProfile = memberService.getMemberByIdentity(identity);

        return ResponseEntity.ok(memberProfile);
    }

}
