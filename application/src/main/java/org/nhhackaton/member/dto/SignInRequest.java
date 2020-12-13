package org.nhhackaton.member.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.nhhackaton.api.HeaderRequestParent;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SignInRequest {

    @JsonProperty("identity")
    private String identity;

    @JsonProperty("password")
    private String password;

    @JsonProperty("fcmToken")
    private String fcmToken;

}
