package utils;
/*
 * @author p78o2
 * @date 2020/10/23
 */

import okhttp3.*;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class HttpUtils {
    private static OkHttpClient client;

    static {
        try {
            client = new OkHttpClient.Builder()
                    .retryOnConnectionFailure(true)
                    .connectTimeout(5, TimeUnit.SECONDS) //连接超时
                    .build();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static String get(String url, Map<String ,String> maps) {
        url += url+"?";
        for(String key : maps.keySet()){
            url+=url+key+"&"+(String)maps.get(key);
        }
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        String responseContent = "";
        try {
            Response response = client.newCall(request).execute();
            responseContent = response.body().string();
        }catch (IOException e){
            e.printStackTrace();
        }
        return responseContent;
    }

    public static String postJson(String url,String jsonStr){
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, jsonStr);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .addHeader("Content-Type", "application/json")
                .build();
        String responseContent = "";
        try {
            Response response = client.newCall(request).execute();
            responseContent = response.body().string();
        }catch (IOException e){
            e.printStackTrace();
        }
        return responseContent;
    }
}
