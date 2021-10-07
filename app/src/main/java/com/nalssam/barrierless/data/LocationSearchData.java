package com.nalssam.barrierless.data;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.naver.maps.geometry.LatLng;
import com.naver.maps.geometry.Tm128;

public class LocationSearchData {
    private final String title;
    private final String address;
    private final double mapX;
    private final double mapY;

    public LocationSearchData(String title, String address, double mapX, double mapY) {
        this.title = title;
        this.address = address;
        this.mapX = mapX;
        this.mapY = mapY;
    }

    public LocationSearchData(JsonObject jsonObject) {
        this.title = jsonObject.get("title").getAsString()
                .replace("<b>", "").replace("</b>", "");
        this.address = jsonObject.get("address").getAsString();
        this.mapX = jsonObject.get("mapx").getAsDouble();
        this.mapY = jsonObject.get("mapy").getAsDouble();
    }

    public LocationSearchData(String jsonString) {
        this(JsonParser.parseString(jsonString).getAsJsonObject());
    }

    public String getTitle() {
        return this.title;
    }
    public String getAddress() {
        return this.address;
    }
    public double getMapX() {
        return this.mapX;
    }
    public double getMapY() {
        return this.mapY;
    }
    public LatLng getLatLng() {
        return new Tm128(this.mapX, this.mapY).toLatLng();
    }

    public String toJsonFromString() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("title", this.title);
        jsonObject.addProperty("address", this.address);
        jsonObject.addProperty("mapx", this.mapX);
        jsonObject.addProperty("mapy", this.mapY);
        return jsonObject.toString();
    }
}
