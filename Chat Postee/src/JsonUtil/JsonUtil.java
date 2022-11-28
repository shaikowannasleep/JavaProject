/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JsonUtil;

import javax.json.Json;

/**
 *
 * @author mac
 */
public class JsonUtil {
    public static String formatMessage(String message, String user){
        return Json.createObjectBuilder()
                .add("message", message)
                .add("sender", user)
                .add("received", "")
                .build().toString();
                
    }
}
    

