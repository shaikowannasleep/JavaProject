package Data;

import Model.Account;

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
