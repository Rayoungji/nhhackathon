package org.nhhackaton.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class ApiCallService<T> {

    private static long DEAL_NO =50;
    private final String URL = "https://developers.nonghyup.com/";

    private final RestTemplate restTemplate;

    public ResponseEntity<T> post(ApiName api, HeaderRequestParent body, Class<T> clazz) {
        return callApiEndpoint(api.getName(), HttpMethod.POST, setHeader(), setBody(api.getName(), body), clazz);
    }

    private ResponseEntity<T> callApiEndpoint(String api, HttpMethod httpMethod, HttpHeaders httpHeaders, Object body, Class<T> clazz) {
        return restTemplate.exchange(URL + api, httpMethod, new HttpEntity<>(body, httpHeaders), clazz);
    }

    private HttpHeaders setHeader(){
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        return headers;
    }

    private Object setBody(String api, HeaderRequestParent body){
        body.setHeader(HeaderRequest.of(api, getDateTime()));
        return body;
    }

    private  String[] getDateTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
        String now = formatter.format(System.currentTimeMillis());
        return now.split(" ");
    }
}
