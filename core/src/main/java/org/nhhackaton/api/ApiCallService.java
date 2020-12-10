package org.nhhackaton.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class ApiCallService<T> {

    private static long DEAL_NO = 1;
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

        headers.set("ApiNm", api.getName());
        headers.set("Tsymd", dateTime[0].replaceAll("-", ""));
        headers.set("Trtm", dateTime[1].replaceAll(":", ""));
        headers.set("Iscd", "기관코드");
        headers.set("FintechApsno", "001");
        headers.set("APISvcCd", "DrawingTransferA");
        headers.set("Istuno", String.format("%06d", DEAL_NO++));
        headers.set("AccessToken", "액세스토큰");

        return headers;
    }

    private String[] getDateTime(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
        String now = formatter.format(System.currentTimeMillis());
        return now.split(" ");
    }
}
