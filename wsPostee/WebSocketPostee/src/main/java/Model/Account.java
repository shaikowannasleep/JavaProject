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
public class Account {
    private String type;
    private RegisterData data;

    public Account(String type,
                   RegisterData data) {
        this.type = type;
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public RegisterData getData() {
        return data;
    }

    public void setData(RegisterData data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RegisterAccount{" +
                "type='" + type + '\'' +
                ", data=" + data +
                '}';
    }
}
