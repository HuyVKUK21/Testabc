package server_2;

public class server2_run {
    public static void main(String[] args) {
        try {
            new server2_rmi();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
