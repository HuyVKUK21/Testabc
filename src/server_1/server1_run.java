package server_1;

public class server1_run {
    public static void main(String[] args) {
        try {
            new server1_rmi();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
