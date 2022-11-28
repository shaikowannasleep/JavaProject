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
public class Data {
    private String authorization;
    private String id;
    private String timespan;

    public Data(String authorization, String id, String timespan) {
        this.authorization = authorization;
        this.id = id;
        this.timespan = timespan;
    }

    public String getAuthorization() {
        return authorization;
    }

    public String getId() {
        return id;
    }

    public String getTimespan() {
        return timespan;
    }
}