package org.nhhackaton.document.entity;

import org.nhhackaton.member.entity.Member;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Document {

    @Id
    @Column(name = "LOAN_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private DocumentType documentType;
    private String url;
    private LocalDate submitDate;
}
