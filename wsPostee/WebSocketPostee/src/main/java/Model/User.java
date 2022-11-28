/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author mac
 */
public class User {
    private String sessionId;
    private String id;
    private String idOnServer;
    private String token;
    private String username;

    public User(String sessionId, String id, String idOnServer,String token, String username) {
        this.sessionId = sessionId;
        this.id = id;
        this.idOnServer = idOnServer;
        this.token = token;
        this.username = username;
    }

    public User(String sessionId,String idOnServer, String token, String username) {
        this.sessionId = sessionId;
        this.idOnServer = idOnServer;
        this.token = token;
        this.username = username;
    }
    
    public String getIdOnServer() {
        return idOnServer;
    }

    public void setIdOnServer(String idOnServer) {
        this.idOnServer = idOnServer;
    }
    
    
    
    public String getUsername(){
        return username;
    }
    
    public void setUsername(String username){
        this.username = username;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public String toString() {
        return "User{" + "sessionId=" + sessionId + ", id=" + id + ", idOnServer=" + idOnServer + ", token=" + token + ", username=" + username + '}';
    }

    
    
}
