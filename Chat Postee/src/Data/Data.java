package Data;

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



