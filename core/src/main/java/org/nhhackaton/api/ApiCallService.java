package org.nhhackaton.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
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

    public ResponseEntity<T> post(ApiName api, Object body) {
        return callApiEndpoint(api.getName(), HttpMethod.POST, setHeader(api), body,(Class<T>)Object.class);
    }

    private ResponseEntity<T> callApiEndpoint(String api, HttpMethod httpMethod, HttpHeaders httpHeaders, Object body, Class<T> clazz) {
        return restTemplate.exchange(URL+api, httpMethod, new HttpEntity<>(body, httpHeaders), clazz);
    }

    private HttpHeaders setHeader(ApiName api){
        HttpHeaders headers = new HttpHeaders();

        String[] dateTime = getDateTime();

        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

//        headers.set("ApiNm", api.getName());
//        headers.set("Tsymd", dateTime[0].replaceAll("-", ""));
//        headers.set("Trtm", dateTime[1].replaceAll(":", ""));
//        headers.set("Iscd","000671");
//        headers.set("FintechApsno", "001");
//        headers.set("APISvcCd", "DrawingTransferA");
//        headers.set("Istuno", String.format("%06d", DEAL_NO++));
//        headers.set("AccessToken", "e053c94bd5b1b0cf188b61dfc9b0378a857c56c15245a15da1cd8a72322e5342");

        return headers;
    }

    private String[] getDateTime(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
        String now = formatter.format(System.currentTimeMillis());
        return now.split(" ");
    }
}
