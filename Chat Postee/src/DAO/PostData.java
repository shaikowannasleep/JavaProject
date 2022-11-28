package DAO;

import static Controller.Connector.sendPost;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Base64;

import javax.imageio.ImageIO;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;

import Data.Data;
import Data.Respond;
import Decoder.BASE64Decoder;
import Model.Account;
import Model.Age;
import Model.Base64Obj;
import Model.CheckIn;
import Model.LoginAccount;
import Model.RegisterData;
import hrm.postee.Temp;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PostData {
    public static final Gson gson = new Gson();
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
        
//        Temp.setCode(response.substring(8,11));
//        System.out.println(Temp.getCode());
   
        return account;
    }
    
    public Account CreateAdmin(String firstName,
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
        String key = "";
        String url = "https://hrm.postee.vn/api/v1/createAdmin?apiKey=" + key;
        Age age = new Age(day, month, year);
        RegisterData data = new RegisterData(firstName, lastName, age, gender,
                username, job, pos, email, phoneNumber,
                password, address);
        Account account = new Account("API_REGISTER", data);
        String obj = gson.toJson(account);
        String response = sendPost(url, obj);
        System.out.println(response);
        return account;
    }

    public String Login(String username, String password) throws Exception{
        String url = "https://hrm.postee.vn/api/v1/login";
        LoginAccount login = new LoginAccount(username,password);
        String obj = gson.toJson(login);
        String respond = sendPost(url,obj);
        System.out.println(respond);
        Respond result = gson.fromJson(respond, Respond.class);
        Data data = result.getData();
        
        String authorization;
        if (data == null) {
        	authorization = null;
        }else {
        	authorization = data.getAuthorization();
        }
        System.out.println(result.getMessage());
        
        Temp.setAuthorization(authorization);
        
        return authorization;
    }
    
    public String GetIdUser(String authorization) throws Exception{
        String url = "https://hrm.postee.vn/checkToken?authorization=" + authorization;
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
            .url(url)
            .build();
        Response response = client.newCall(request).execute();
            String result = response.body().string();
        JSONObject jsonObject = new JSONObject(result);
        String id = jsonObject.getString("id");
        return id;
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
            Temp.setUser((String) info.get("username"));
            Temp.setEmail((String) info.get("email"));
            Temp.setPhoneNumber((String) info.get("phoneNumber"));
            Temp.setAddress((String) info.get("address"));
            Temp.setId((String) info.get("id"));
            Temp.setSalary(info.getDouble("salary"));
            
            JSONObject jobDescrip = info.getJSONObject("jobDescription");
            Temp.setJob(jobDescrip.getString("name"));
            JSONObject type = new JSONObject(result);
            Temp.setType((String) type.get("type"));
            
            JSONObject age = info.getJSONObject("age");
            Temp.setDay((int)age.getDouble("day"));
            Temp.setMonth((int)age.getDouble("month"));
            Temp.setYear((int)age.getDouble("year"));

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
    
    public Account UpdateInfo(String authorization, String firstName,
                             String lastName,
                             int day, int month, int year,
                             String gender,
                             String username,
                             int job,
                             int pos,
                             String email,
                             String phoneNumber,
                             String password,
                             String address) throws Exception{
        String url = "https://hrm.postee.vn/api/v1/changeInfo?authorization=" + authorization;
        Age age = new Age(day, month, year);
        RegisterData data = new RegisterData(firstName, lastName, age, gender,
                username, job, pos, email, phoneNumber,
                password, address);
        Account account = new Account("API_UPDATE", data);
        String obj = gson.toJson(account);
        String response = sendPost(url, obj);
        System.out.println(response);
        return account;
    }
    
    public String GetNewPass(String email, String newPass) throws Exception {
        String url = "https://hrm.postee.vn/api/v1/forgotPassword?email=" + email + "&newPassword=" + newPass;
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), "{}");
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
            .url(url)
            .build();
        Response response = client.newCall(request).execute();
        String result = response.body().string();
        System.out.println(result);
        
        Temp.setCode(result.substring(8,11));
//        System.out.println(Temp.getCode());
        
        return result;
    }
    
    public ArrayList<Object[]> GetListAcc(String authorization) throws Exception {
        String url = "https://hrm.postee.vn/getDbNames?&authorization=" + authorization;
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
            .url(url)
            .build();
        Response response = client.newCall(request).execute();
        String result = response.body().string();
        System.out.println(result);
        JSONObject resultObj = new JSONObject(result);
        JSONArray data = resultObj.getJSONArray("data");
        ArrayList<Object[]> alistObj = new ArrayList<>();
        for(int a = 0; a < data.length(); a++){
            RegisterData userdata = gson.fromJson(data.getJSONObject(a).toString(),RegisterData.class);
            
            Object[] listObj = new Object[10];
            listObj[0] = userdata.getLastName();
            listObj[1] = userdata.getFirstName();
            listObj[2] = (new JSONObject(data.getJSONObject(a).toString())).get("id");
            Age age = userdata.getAge();
            listObj[3] = age.getDay()+"/"+age.getMonth()+"/"+age.getYear();
            listObj[4] = userdata.getGender();
            
            String j1 = (new Gson()).toJson(data.get(a));
            String[] j2 = j1.split("jobDescription\":");
            String job = "";
            if(j2.length>1)
            {
            	String[] j3 = j2[1].split("name\":\"");
            	if(j3.length>1)
            	job = j3[1].split("\"")[0];
            }
            listObj[5] = job;
            
            listObj[6] = userdata.getEmail();
            listObj[7] = userdata.getPhoneNumber();
            listObj[8] = userdata.getAddress();
            
            String[] s2 = j1.split("salary\":");
            String salary = "";
            if(s2.length>1) {
                salary = s2[1].split("\"")[0].replace(",", "");
            }
            listObj[9] = salary;
            
            alistObj.add(listObj);
        }
//        System.out.println(alistObj.toString());
        return alistObj;
    }
    
    public ArrayList GetTimeData(String authorization) throws Exception {
        Gson gson = new Gson();
        String url = "https://hrm.postee.vn/api/v1/tk/getListTimeKeeping?authorization=" + authorization;
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = client.newCall(request).execute();
        String result = response.body().string();
        JSONObject jsonObject = new JSONObject(result);
        ArrayList list = (new Gson()).fromJson(jsonObject.get("data").toString(), ArrayList.class);
        ArrayList<CheckIn> resList = new ArrayList<>();
        for(int i = 0; i < list.size(); i++){
            JSONObject obj = new JSONObject(gson.toJson(list.get(i)));
            CheckIn checkIn = gson.fromJson(obj.toString(), CheckIn.class);
            resList.add(checkIn);
        }
        System.out.println(resList);
        return resList;
    }

    public String Delete(String authorization, String id) throws Exception {
        String url = "https://hrm.postee.vn/api/v1/delete?id=" + id + "&authorization=" + authorization;
        okhttp3.OkHttpClient client = new okhttp3.OkHttpClient();
        okhttp3.Request request = new Request.Builder()
            .url(url)
            .build();
        okhttp3.Response response = client.newCall(request).execute();
        System.out.println(response);
        return (new Gson()).toJson(response.toString());
    }


    public static String UploadAva(String authorization, String base64) throws Exception {
    	String url = "https://hrm.postee.vn/api/v1/uploadImageProfile?authorization="+authorization;
    	System.out.println(url);
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        String json = "{\"base64\":"+ gson.toJson(base64) + "}";
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
            .url(url)
            .post(body)
            .build();
        Response response = client.newCall(request).execute();
        String resp = response.body().string();
        return resp;
    }
    
    public static String UploadFace(String authorization, String base64) throws Exception {
    	String url = "https://hrm.postee.vn/api/v1/uploadImageFace?authorization="+authorization;
    	System.out.println(url);
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        String json = "{\"base64\":"+ gson.toJson(base64) + "}";
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
            .url(url)
            .post(body)
            .build();
        Response response = client.newCall(request).execute();
        String resp = response.body().string();
        System.out.println(resp);
        String code = resp.substring(8,11);
        return code;
    }
    
    public static String CheckIn(String authorization, String base64) throws Exception {
    	String url = "https://hrm.postee.vn/api/v1/checkinFace?authorization="+authorization;
    	System.out.println(url);
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        String json = "{\"base64\":"+ gson.toJson(base64) + "}";
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
            .url(url)
            .post(body)
            .build();
        Response response = client.newCall(request).execute();
        String resp = response.body().string();
        System.out.println(resp);
        String code = resp.substring(8,11);
        return code;
    }
    
    public static String LoginFace(String base64) throws Exception {
    	String url = "https://hrm.postee.vn/api/v1/loginFace?id=84271241866319";
    	System.out.println(url);
        Base64Obj obj = new Base64Obj();
        obj.base64 = base64;
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        String json = gson.toJson(obj);
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
            .url(url)
            .post(body)
            .build();
        Response response = client.newCall(request).execute();
        String resp = response.body().string();
        System.out.println(resp);
        return resp;
    }
    
    public static String encodeBase64(String path)	{
        try
        {
            File f =  new File(path);
            byte[] bytes = Files.readAllBytes(f.toPath());
            String encodedfile = Base64.getEncoder().encodeToString(bytes);
            System.out.println(encodedfile);
            return encodedfile;
        }
        catch(IOException ex)
        {
            System.out.println(ex.toString());
            return null;
        }
    }
    
    public BufferedImage decodeBase64(String base64) throws IOException	{
        try
        {
            BufferedImage image = null;
            byte[] imageByte;
            BASE64Decoder decoder = new BASE64Decoder();
            imageByte = decoder.decodeBuffer(base64);
            try (ByteArrayInputStream bis = new ByteArrayInputStream(imageByte)) {
                image = ImageIO.read(bis);
            }
            File outputfile = new File("image.png");
            ImageIO.write(image, "png", outputfile);
            return image;
        }
        catch(IOException ex)
        {
            System.out.println(ex.toString());
            return null;
        }
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
    
    public static String convertToString(InputStream in) {
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
