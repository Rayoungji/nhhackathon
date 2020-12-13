package org.nhhackaton.member.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.nhhackaton.api.finaccount.dto.OpenFinAccountRequest;
import org.nhhackaton.deposit.dto.BaseResponse;
import org.nhhackaton.errors.exception.MemberAlreadyExsistException;
import org.nhhackaton.errors.exception.NotCorrectPasswordException;
import org.nhhackaton.errors.exception.NotExistIdentityException;
import org.nhhackaton.fcm.service.FirebaseCloudMessageSender;
import org.nhhackaton.invest.service.InvestService;
import org.nhhackaton.member.dto.DocumentRequest;
import org.nhhackaton.member.dto.SetAccountRequest;
import org.nhhackaton.member.dto.SignInRequest;
import org.nhhackaton.member.dto.SignUpRequest;
import org.nhhackaton.member.entity.Member;
import org.nhhackaton.member.repository.MemberRepository;
import org.nhhackaton.member.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final InvestService investService;

    @GetMapping("/{identity}")
    public boolean validate(@PathVariable String identity) {
        return memberService.validate(identity);
    }

    @PostMapping("/document/{identity}")
    public String getUrl(@PathVariable String identity,
                         MultipartFile multipartFile) {
        return memberService.getUrl(multipartFile, identity);
    }

    @GetMapping("/a/{identity}")
    public void validateTest(@PathVariable String identity){
        memberService.validateTest(identity);
    }

    @PostMapping("/{identity}")
    public void test(@PathVariable String identity) {
        Member m = Member.builder()
                .identity(identity)
                .birthday("19501213")
                .password("password")
                .name("재호")
                .build();

        memberService.signInForTest(m);
    }

    @PostMapping("/set-account/{identity}")
    @ApiOperation("계좌 등록")
    public BaseResponse setAccount(@PathVariable String identity, @RequestBody SetAccountRequest setAccountRequest) {

        Member loginMember = memberService.getMemberByIdentity(identity);
        OpenFinAccountRequest openFinAccountRequest = OpenFinAccountRequest.builder()
                .DrtrRgyn("Y")
                .BrdtBrno(loginMember.getBirthday())
                .Bncd(setAccountRequest.getBncd())
                .Acno(setAccountRequest.getAcno()).build();
        investService.makeFinAccount(loginMember, openFinAccountRequest, setAccountRequest.getBncd(), setAccountRequest.getAcno());

        return new BaseResponse("200");
    }

    @PostMapping
    public void saveDocuments(@RequestBody List<DocumentRequest> documentRequests) {
        memberService.saveDocument(
                documentRequests.stream()
                        .map(DocumentRequest::of)
                        .collect(Collectors.toList())
        );
    }

    @PostMapping("/signup")
    public void signUp(@RequestBody SignUpRequest signUpRequest) {
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
        Member loginMember = memberService.login(member, signInRequest.getFcmToken());

        return ResponseEntity.ok(loginMember);
    }

    @GetMapping("/member-profile/{identity}")
    public ResponseEntity<Member> memberProfile(@PathVariable String identity) {
        Member memberProfile = memberService.getMemberByIdentity(identity);

        return ResponseEntity.ok(memberProfile);
    }

    @GetMapping("/duplication/{identity}")
    public boolean checkId(@PathVariable String identity){
        return memberService.checkId(identity);
    }

    private final FirebaseCloudMessageSender sender = new FirebaseCloudMessageSender(new ObjectMapper());
    private final MemberRepository repository;
    @GetMapping("/send")
    public void send() throws IOException {
//        String token = "eHzxHRzMSTquQLNIbQDaWb:APA91bFG6JXETXxWnIT575cJ44XLge6qKhc1IPH9_u3W3-XwtX2do98tve3osRZggINW0iDvN7Ql0uPrlbB8mhr7tJ-weQ36ApGhgl4wFkjobkyGJ0SE6amcL3wkZ8Xh8jlxXBVcEClQ";
        Member yun = memberService.getMemberByIdentity("yun");
        sender.sendMessageTo(yun.getFcmToken(), "Test", "test");
    }
}
