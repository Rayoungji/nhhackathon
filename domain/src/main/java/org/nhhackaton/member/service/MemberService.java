package org.nhhackaton.member.service;

import lombok.RequiredArgsConstructor;
import org.nhhackaton.aws.s3.S3Uploader;
import org.nhhackaton.document.entity.Document;
import org.nhhackaton.document.repository.DocumentRepository;
import org.nhhackaton.errors.exception.DocumentUploadFailException;
import org.nhhackaton.errors.exception.MemberNotFoundException;
import org.nhhackaton.member.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final DocumentRepository documentRepository;
    private final S3Uploader s3Uploader;

    public boolean validate(String identity){
        return memberRepository.findByIdentity(identity).orElseThrow(MemberNotFoundException::new).isVerified();
    }

    public String getUrl(MultipartFile multipartFile, String identity){
        try {
            return s3Uploader.upload(multipartFile, identity,  "documents_" + UUID.randomUUID().toString());
        }catch (IOException e){
            throw new DocumentUploadFailException();
        }
    }

    public void saveDocument(List<Document> documents){
        documentRepository.saveAll(documents);
    }
}
