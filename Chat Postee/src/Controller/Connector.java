/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

/**
 *
 * @author ADMIN
 */
import okhttp3.*;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class Connector {
    public static String sendPost(String url, String json) throws Exception {
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, json); // new
        // RequestBody body = RequestBody.create(JSON, json); // old
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public static String sendGet(String authorization) throws Exception{
        URL endPoint = new URL("http://hrm.postee.vn/checkToken?authorization=" + authorization);
        HttpURLConnection connection = (HttpURLConnection) endPoint.openConnection();
        connection.setRequestMethod("GET");
        connection.setDoOutput(true);
        connection.setDoInput(true);
        InputStream i = new BufferedInputStream(connection.getInputStream());
        String result = convertToString(i);
        System.out.println(result);
        return result;
    }
    public static String convertToString(InputStream in) {
        // TODO Auto-generated method stub
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
        } finally {
            try {
                in.close();
            } catch (IOException e) {
            }
        }
        return sb.toString();
    }


}