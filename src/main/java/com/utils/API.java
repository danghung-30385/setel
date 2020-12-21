package com.utils;

import com.google.gson.JsonObject;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class API {
    private static final Logger logger = LoggerFactory.getLogger(API.class);
    public Response sendPostWithAuthorizeRequest(String uri, JsonObject data, String token) {
        Response response = null;
        OkHttpClient client = new OkHttpClient();
        Request request;
        try {
            if(data != null) {
                RequestBody body = RequestBody.create(MediaType.parse("application/json"), String.valueOf(data));
                request = new Request.Builder()
                        .url(uri)
                        .addHeader("Authorization", "Bearer " + token)
                        .post(body)
                        .build();
            } else {
                request = new Request.Builder()
                        .url(uri)
                        .addHeader("Authorization", "Bearer " + token)
                        .build();
            }
            Call call = client.newCall(request);
            response = call.execute();
        } catch(Exception e){
            logger.error("Can NOT send Post Json & Authorize Request with URI: " + e);
        }
        return  response;
    }

    public Response sendGetWithAuthorizeRequest(String uri, String token) {
        Response response = null;
        OkHttpClient client = new OkHttpClient();
        try {
            Request request = new Request.Builder()
                    .url(uri)
                    .addHeader("Authorization","Bearer " + token)
                    .build();
            Call call = client.newCall(request);
            response = call.execute();
        } catch(Exception e){
            logger.error("Can NOT send Get Authorize Request with URI: " + e);
        }
        return  response;
    }

}
