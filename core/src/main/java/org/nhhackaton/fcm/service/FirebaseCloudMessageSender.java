package org.nhhackaton.fcm.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.google.auth.oauth2.GoogleCredentials;
import com.squareup.okhttp.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHeaders;
import org.nhhackaton.fcm.dto.FcmMessageDto;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

// 빈으로 등록
@Slf4j
@RequiredArgsConstructor
public class FirebaseCloudMessageSender {
    private final String API_URI = "https://fcm.googleapis.com/v1/projects/nhhackathon-d738f/messages:send";
    private final String JSON_PATH = "firebase/firebase_service_key.json";
    private final ObjectMapper om;

    public void sendMessageTo(String targetToken, String title, String body) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(API_URI)
                .post(RequestBody.create(MediaType.parse("application/json")
                        , makeMessage(targetToken, title, body)))
                .addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + getAccessToken())
                .addHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                .build();
        Response response = client.newCall(request).execute();
        log.info(response.body().string());
    }

    private String makeMessage(String targetToken, String title, String body) throws JsonProcessingException {
        FcmMessageDto dto = FcmMessageDto.builder().validate_only(false)
                .message(FcmMessageDto.Message.builder()
                        .token(targetToken)
                        .notification(FcmMessageDto.Notification.builder()
                                .title(title)
                                .body(body)
                                .image(null)
                                .build()
                        ).build()
                ).build();
        return om.writeValueAsString(dto);
    }

    private String getAccessToken() {
        GoogleCredentials googleCredentials;
        try {
            googleCredentials = GoogleCredentials
//                    .fromStream(Objects.requireNonNull(FirebaseCloudMessageSender.class.getClassLoader().getResourceAsStream(JSON_PATH)))
                    .fromStream(new ClassPathResource(JSON_PATH).getInputStream())
                    .createScoped(Arrays.asList("https://www.googleapis.com/auth/cloud-platform"));
            googleCredentials.refreshIfExpired();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return googleCredentials.getAccessToken().getTokenValue();
    }
}
