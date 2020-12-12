package org.nhhackaton.member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.nhhackaton.document.entity.Document;
import org.nhhackaton.document.entity.DocumentType;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class DocumentRequest {

    private String url;
    private String type;
    private String identity;

    public Document of(){
        return Document.builder()
                .documentType(DocumentType.of(this.type))
                .submitDate(LocalDate.now())
                .url(this.url)
                .memberIdentity(this.identity)
                .build();
    }
}
