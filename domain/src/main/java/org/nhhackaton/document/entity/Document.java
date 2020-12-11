package org.nhhackaton.document.entity;


import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "DOCUMENTS")
public class Document {

    @Id
    @Column(name = "DOCUMENT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private DocumentType documentType;
    private String url;
    private LocalDate submitDate;
}
