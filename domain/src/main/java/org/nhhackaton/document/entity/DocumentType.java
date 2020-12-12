package org.nhhackaton.document.entity;

import lombok.Getter;
import org.nhhackaton.errors.exception.UserDefineException;

import java.util.Arrays;

/**
 * 공통서류
 * 주민등록등본, 본인 소득금액 증명원, 배우자 소득금액 증명원(기혼자에 한함)
 *
 * 근로청년 추가서류
 * 건강보험 자격득실확인서, 현재 연봉기재된 근로계약서(연봉계약서), 사업자등록증(사업자의 경우에 한하여 제출)
 *
 * 비근로 청년
 * 가족관계증명서, 부모소득금액 증명원(부모모두 제출)
 *
 * 대출신청
 * 임대차계약서(사본),
 * - 현재 거주중인 주택의 계약서는 제출 불가(잔금납부일 이후에는 신청 불가)
 * - 단, 임차계약기간 만료 후, 동일주택에 재계약을 하는 경우에는 신청 가능(재계약서 제출)
 * 기타 대출 관련 필요서류
 */
@Getter
public enum DocumentType {

    attested_copy("주민등록등본"),
    self_Certificate_of_income_amount("본인 소득 금액 증명원"),
    spouse_Certificate_of_income_amount("배우자 소득 금액 증명원"),
    Health_Insurance_Qualification_Certificate("건강보험 자격득실 확인서"),
    Annual_salary_contract("연봉계약서"),
    Business_Registration("사업자등록증"),
    Family_Relations_Certificate("가족관계 증명서"),
    Certificate_of_parental_income("부모 소득 금액 증명원"),
    Lease_agreement("임대차계약서")
    ;

    private String document;

    DocumentType(String document) {
        this.document = document;
    }

    public static DocumentType of(String type){
        return Arrays.stream(DocumentType.values())
                .filter(documentType -> documentType.getDocument().equals(type))
                .findAny().orElseThrow(() -> new UserDefineException("해당하는 서류 타입을 찾을 수 없습니다."));
    }
}
