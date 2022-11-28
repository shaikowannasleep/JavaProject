/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MongodbHelper;

import AuthClient.Signup.PostData;
import AuthClient.Signup.ServerPutRegister;
import Model.ListMessage;
import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.WriteResult;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Random;
import javax.swing.text.Document;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import org.bson.types.ObjectId;
import org.json.JSONObject;
import okhttp3.Request;
import okhttp3.Request.Builder;

/**
 *
 * @author mac
 */
public class DataHelper {
    
    public static Boolean isInDB(String k, Object v, String dbName, String collectionName) {
        DB db = MongoHelper.clientDB.getDB(dbName);
        DBCollection dept = db.getCollection(collectionName);
        BasicDBObjectBuilder whereBuilder = BasicDBObjectBuilder.start();
        whereBuilder.append(k, v);
        DBObject where = whereBuilder.get();
        DBCursor cursor = dept.find(where);
        if (cursor.size() != 0) {

            DBObject dbo = cursor.next();
        }
//        System.out.println(cursor.count());
        return !(cursor.count() == 0);
    }
    
    public static String generateNewToken() {
        byte[] randomBytes = new byte[64];
        Random secureRandom = new Random();
        secureRandom.nextBytes(randomBytes);
        Base64.Encoder base64Encoder = Base64.getUrlEncoder();
        return base64Encoder.encodeToString(randomBytes);
    }
    
    public static Boolean isInDB_Postee_UserData(String k, Object v)
    {
        return isInDB(k, v, "postee_hrm_db","userData");
    }
    
    public static Boolean createCollections(String collectionName) {
        DB db = MongoHelper.clientDB.getDB("postee_hrm_db");
//        DBCollection newCollect = db.createCollection(collectionName, new BasicDBObject());
        db.createCollection(collectionName, new BasicDBObject());
        return false;
    }

    public static Boolean dropCollections(String collectionName) {
        DB db = MongoHelper.clientDB.getDB("postee_hrm_db");
        DBCollection dept = db.getCollection(collectionName);
        dept.drop();
        //db.dropDatabase();
        return false;
    }
    
    public static ArrayList<Object> getDB(String k, Object v, String dbName, String collectionName) {
        ArrayList<Object> listObj = new ArrayList<Object>();
        DB db = MongoHelper.clientDB.getDB(dbName);
        DBCollection dept = db.getCollection(collectionName);
        BasicDBObjectBuilder whereBuilder = BasicDBObjectBuilder.start();
        whereBuilder.append(k, v);
        DBObject where = whereBuilder.get();
        DBCursor cursor = dept.find(where);
        while (cursor.hasNext()) {
            //System.out.println("hihi");
            DBObject dbo = cursor.next();

            System.out.println(dbo.toString());
            listObj.add(dbo);
        }
        return listObj;
    }

    public static ArrayList<DBObject> getDB_DBObject(String k, Object v, String dbName, String collectionName) {
        ArrayList<DBObject> listObj = new ArrayList<DBObject>();
        DB db = MongoHelper.clientDB.getDB(dbName);
        DBCollection dept = db.getCollection(collectionName);
        BasicDBObjectBuilder whereBuilder = BasicDBObjectBuilder.start();
        whereBuilder.append(k, v);
        DBObject where = whereBuilder.get();
        DBCursor cursor = dept.find(where);
        while (cursor.hasNext()) {
            //System.out.println("hihi");
            listObj.add(cursor.next());
        }
        return listObj;
    }
    
    public static Boolean putDataTo_PosteeDB_Register(Object obj) {
        DB db = MongoHelper.clientDB.getDB("postee_hrm_db");
        DBCollection dept = db.getCollection("userData");
        DBObject dbo = (DBObject) com.mongodb.util.JSON.parse((new Gson()).toJson(obj));
//        WriteResult result = dept.insert(dbo);
        dept.insert(dbo);
//        System.out.println(result.toString());
        return false;
    }
    
    public static String getIdByFields(String k, String v) {
        ArrayList<DBObject> dbo = new ArrayList<DBObject>();
        dbo = getDB_DBObject(k, v, "postee_hrm_db", "userData");
        if (dbo.size() > 0) {
            DBObject obj = dbo.get(0);
            return obj.get("idOnServer").toString();
        }
        return "";
    }
    
    public static Boolean putDataTo_PosteeDB(Object obj, String collection) {
        DB db = MongoHelper.clientDB.getDB("postee_hrm_db");
        DBCollection dept = db.getCollection(collection);
        DBObject dbo = (DBObject) com.mongodb.util.JSON.parse((new Gson()).toJson(obj));
//        WriteResult result = dept.insert(dbo);
        dept.insert(dbo);
        //System.out.println(result.toString());
        return false;
    }
    
    public static Boolean remove_From_DB_Postee(String k, Object v, String db_f) {
        try {
            DB db = MongoHelper.clientDB.getDB("postee_hrm_db");
            DBCollection dept = db.getCollection(db_f);
            ArrayList<DBObject> obj = getDB_DBObject(k, v, "postee_hrm_db", db_f);
            for (int i = 0; i < obj.size(); i++) {
                dept.remove(obj.get(i));
            }
            return true;
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return false;
        }
    }
}
