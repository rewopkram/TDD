
public class DBConnection {

    private static DBConnection instance;
    
    public static DBConnection getConnection() {
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }
}
