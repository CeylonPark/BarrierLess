package com.nalssam.barrierless.api;

import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;


public class PlaceSearchService {
    public String searchPlace(String keyword){
        try {
            keyword = URLEncoder.encode(keyword, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("encoding fail!",e);
        }

        String apiURL = "https://openapi.naver.com/v1/search/local.json?query="+keyword+"&display=20&start=1&sort=random";

        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", "wU2fDESa1aZxK5fDP91x");
        requestHeaders.put("X-Naver-Client-Secret", "HVQwWxVfrw");
        String responseBody = new HttpGet().get(apiURL,requestHeaders);

        System.out.println("네이버에서 받은 결과 = " + responseBody);
        System.out.println("-----------------------------------------");

        return responseBody;
    }
}
