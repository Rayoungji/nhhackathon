package org.nhhackaton.document.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Entity
@Table(name = "DOCUMENTS")
@NoArgsConstructor
@AllArgsConstructor @Builder
public class Document {

    @Id
    @Column(name = "DOCUMENT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private DocumentType documentType;
    private String url;
    private LocalDate submitDate;

    private String memberIdentity;
}
