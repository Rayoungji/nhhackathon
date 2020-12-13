package org.nhhackaton.member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberResponse {
    private String url;

    public MemberResponse(String url) {
        this.url = url;
    }
}
