/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MongodbHelper;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import java.net.UnknownHostException;
/**
 *
 * @author Hoang Quoc Bao
 */
public class MongoHelper {
    private static String HOST;
    private static int PORT;
    public static MongoClient clientDB;
    private MongoClient connect() throws UnknownHostException {
      MongoClient mongoClient = new MongoClient(HOST, PORT);
      return mongoClient;
    }
    public MongoHelper(String host, int port) throws UnknownHostException
    {
        HOST = host;
        PORT = port;
        clientDB = connect();
    }
    public MongoHelper() throws UnknownHostException
    {
        HOST = "localhost";
        PORT = 27017;
        clientDB = connect();
    }
}
