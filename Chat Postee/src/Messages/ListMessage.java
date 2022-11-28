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
public class ListMessage {
    private String sender;
    private String received;
    private String content;
    private String ID;
    private String idOnServer;
    private String timer;

    public ListMessage(String sender, String received, String content, String ID, String idOnServer,String timer) {
        this.sender = sender;
        this.received = received;
        this.content = content;
        this.ID = ID;
        this.idOnServer = idOnServer;
        this.timer = timer;
    }
    
    
    
    public ListMessage(){
        
    }

    public String getIdOnServer() {
        return idOnServer;
    }

    public void setIdOnServer(String idOnServer) {
        this.idOnServer = idOnServer;
    }
    
    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }



    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTimer() {
        return timer;
    }

    public void setTimer(String timer) {
        this.timer = timer;
    }

    public String getReceived() {
        return received;
    }

    public void setReceived(String received) {
        this.received = received;
    }

    @Override
    public String toString() {
        return "ListMessage{" + "sender=" + sender + ", received=" + received + ", content=" + content + ", ID=" + ID + ", idOnServer=" + idOnServer + ", timer=" + timer + '}';
    }

    
    
    
}
