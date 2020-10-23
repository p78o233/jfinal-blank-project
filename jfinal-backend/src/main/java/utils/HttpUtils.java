package utils;
/*
 * @author p78o2
 * @date 2020/10/23
 */

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Map;

public class HttpUtils {
    public static String get(String url, Map<String ,String> maps) {
        OkHttpClient client = new OkHttpClient();
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
}
