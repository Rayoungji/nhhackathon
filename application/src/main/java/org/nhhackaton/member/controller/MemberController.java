package org.nhhackaton.member.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.nhhackaton.aws.s3.S3Uploader;
import org.nhhackaton.errors.exception.DocumentUploadFailException;
import org.nhhackaton.fcm.service.FirebaseCloudMessageSender;
import org.nhhackaton.member.dto.DocumentRequest;
import org.nhhackaton.member.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/{identity}")
    public boolean validate(@PathVariable String identity){
        return memberService.validate(identity);
    }

    @PostMapping("/document/{identity}")
    public String getUrl(@PathVariable String identity,
                         MultipartFile multipartFile) {
        return memberService.getUrl(multipartFile, identity);
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
