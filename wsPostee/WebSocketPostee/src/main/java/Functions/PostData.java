/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Functions;

import Model.Account;
import Model.Age;
import Model.Data;
import Model.LoginAccount;
import Model.RegisterData;
import Model.Respond;
import Model.Temp;
import com.google.gson.Gson;
import okhttp3.*;
import org.json.JSONObject;
/**
 *
 * @author mac
 */
public class PostData {
    public static final Gson gson = new Gson();
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
    
    public Account CreateAcc(String firstName,
                             String lastName,
                             int day, int month, int year,
                             String gender,
                             String username,
                             int job,
                             int pos,
                             String email,
                             String phoneNumber,
                             String password,
                             String address) throws Exception {
        String url = "https://hrm.postee.vn/api/v1/register";
        Age age = new Age(day, month, year);
        RegisterData data = new RegisterData(firstName, lastName, age, gender,
                username, job, pos, email, phoneNumber,
                password, address);
        Account account = new Account("API_REGISTER", data);
        String obj = gson.toJson(account);
        String response = sendPost(url, obj);
        System.out.println(response);
        
        Temp.setCode(response.substring(8,11));
        System.out.println(Temp.getCode());
   
        return account;
    }
    
    public String Login(String username, String password) throws Exception{
        String url = "https://hrm.postee.vn/api/v1/login";
        Gson gson = new Gson();
        LoginAccount login = new LoginAccount(username,password);
        String obj = gson.toJson(login);
        String respond = sendPost(url,obj);
        Respond result = gson.fromJson(respond, Respond.class);
        Data data = result.getData();
        
        String authorization;
        if (data == null) {
        	authorization = null;
        }else {
        	authorization = data.getAuthorization();
        }
        
        Temp.setAuthorization(authorization);
        
        return authorization;
    }
    
   
    
    public String GetInforByAuthorization(String authorization) throws Exception {
    	
    	if(authorization == null) {
            return null;
    	}else {
            String url = "https://hrm.postee.vn/checkToken?authorization=" + authorization;
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                .url(url)
                .build();
            Response response = client.newCall(request).execute();
            String result = response.body().string();
            System.out.println(result);
            
            JSONObject info = new JSONObject(result);
            Temp.setFirstName((String) info.get("firstName"));
            Temp.setLastName((String) info.get("lastName"));
            Temp.setGender((String) info.get("gender"));
            Temp.setUsername((String) info.get("username"));
            Temp.setEmail((String) info.get("email"));
            Temp.setPhoneNumber((String) info.get("phoneNumber"));
            Temp.setAddress((String) info.get("address"));
            Temp.setId((String) info.get("id"));
            Temp.setSalary(info.getDouble("salary"));
            Temp.setUsername((String) info.getString("username"));
            
            JSONObject jobDescrip = info.getJSONObject("jobDescription");
            Temp.setJob(jobDescrip.getString("name"));
            JSONObject type = new JSONObject(result);
            Temp.setType((String) type.get("type"));
            
            JSONObject age = info.getJSONObject("age");
            Temp.setDay((int)age.getDouble("day"));
            Temp.setMonth((int)age.getDouble("month"));
            Temp.setYear((int)age.getDouble("year"));

            
//            System.out.println(result);
            return result;
        }
    }
    
    public String GetIDByAuthorization(String authorization) throws Exception {
    	
    	if(authorization == null) {
            return null;
    	}else {
            String url = "https://hrm.postee.vn/checkToken?authorization=" + authorization;
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                .url(url)
                .build();
            Response response = client.newCall(request).execute();
            String result = response.body().string();
            JSONObject info = new JSONObject(result);
            
            Temp.setId((String) info.get("id"));
            return Temp.getId();
        }
    }
    
    public String GetUsername(String authorization) throws Exception {
    	
    	if(authorization == null) {
            return null;
    	}else {
            String url = "https://hrm.postee.vn/checkToken?authorization=" + authorization;
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                .url(url)
                .build();
            Response response = client.newCall(request).execute();
            String result = response.body().string();
            
            JSONObject info = new JSONObject(result);
            Temp.setUsername((String) info.getString("username"));
            return Temp.getUsername();
        }
    } 
    
    public String GetListAcc(String authorization) throws Exception {
        String url = "https://hrm.postee.vn/getDbNames?&authorization=" + authorization;
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
            .url(url)
            .build();
        Response response = client.newCall(request).execute();
        String result = response.body().string();
        System.out.println(result);
        return result;
    }
    
     public String Delete(String authorization, String id) throws Exception {
        String url = "http://hrm.postee.vn/api/v1/delete?id=" + id + "&authorization=" + authorization;
        okhttp3.OkHttpClient client = new okhttp3.OkHttpClient();
        okhttp3.Request request = new Request.Builder()
            .url(url)
            .build();
        okhttp3.Response response = client.newCall(request).execute();
        System.out.println(response);
        return (new Gson()).toJson(response.toString());
    }
     
     public String GetAva(String authorization) throws Exception{
        String url = "https://hrm.postee.vn/api/v1/getImageProfile?authorization=" + authorization;
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
            .url(url)
            .build();
        Response response = client.newCall(request).execute();
        String result = response.body().string();
        return result;
    }
     
    
}
