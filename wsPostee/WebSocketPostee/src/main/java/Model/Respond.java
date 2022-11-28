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
public class Respond{
    private String type;
    private String message;
    private Data data;
    private Account registerAccount;

    public Respond(String type, String message, Data data, Account registerAccount) {
        this.type = type;
        this.message = message;
        this.data = data;
        this.registerAccount = registerAccount;
    }

    public String getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    public Data getData() {
        return data;
    }

    public Account getRegisterAccount() {
        return registerAccount;
    }

    @Override
    public String toString() {
        return "Respond{" +
                "type='" + type + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                ", registerAccount=" + registerAccount +
                '}';
    }
}
