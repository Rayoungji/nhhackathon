package org.nhhackaton.member.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.nhhackaton.api.HeaderRequest;
import org.nhhackaton.api.HeaderRequestParent;
import org.nhhackaton.member.entity.Member;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SignUpRequest {

    @JsonProperty("identity")
    private String identity;

    @JsonProperty("password")
    private String password;

    @JsonProperty("birthday")
    private String birthday;

    @JsonProperty("name")
    private String name;

//    @JsonProperty("investVirtualAccount")
//    private String investVirtualAccount;
//
//    @JsonProperty("repaymentVirtualAccount")
//    private String repaymentVirtualAccount;
//
//    @JsonProperty("investFinAccount")
//    private String investFinAccount;
//
//    @JsonProperty("repaymentFinAccount")
//    private String repaymentFinAccount;




}
