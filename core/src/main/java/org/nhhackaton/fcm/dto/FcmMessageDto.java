package org.nhhackaton.fcm.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class FcmMessageDto {
    private boolean validate_only;
    private Message message;

    @Builder
    @Getter
    public static class Message {
        private Notification notification;
        private String token;
    }

    @Builder
    @Getter
    public static class Notification {
        private String title;
        private String body;
        private String image;
    }
}
