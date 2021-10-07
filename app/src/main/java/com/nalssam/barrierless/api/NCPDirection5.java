package com.nalssam.barrierless.api;

import com.naver.maps.geometry.LatLng;

import java.util.HashMap;
import java.util.Map;


public class NCPDirection5 {
    public String getDirection(LatLng startLatLng, LatLng goalLatLng){
        String start = startLatLng.longitude+","+startLatLng.latitude;
        String goal = goalLatLng.longitude+","+goalLatLng.latitude;

        String url = "https://naveropenapi.apigw.ntruss.com/map-direction/v1/driving?start="+start+"&goal="+goal;

        System.out.println(url);

        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-NCP-APIGW-API-KEY-ID", "fzkcxevekr");
        requestHeaders.put("X-NCP-APIGW-API-KEY", "F3DJXmshUtnatFMiRA8ve6s2yFTULTz4rs3ALrLb");
        String responseBody = new HttpGet().get(url,requestHeaders);

        System.out.println("네이버에서 받은 결과 = " + responseBody);
        System.out.println("-----------------------------------------");

        return responseBody;
    }
}
