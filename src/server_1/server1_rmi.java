package server_1;


import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.Connection;

public class server1_rmi {
    private int serverPort = 1234;
    private Registry registry;
    private Connection connection;
    private String rmiService_Employee = "rmi_service_Employee_1";

    public server1_rmi(){
        connection = connectDB_1.getConnection();

        if (connection != null) {
            System.out.println("Connection success!");
        }

        try {
            registry = LocateRegistry.createRegistry(serverPort);
            registry.bind(rmiService_Employee, new EmployeeController_1(connection));
            System.out.println("Server Started!");
        } catch (Exception e) {
            System.err.println("Server initialization error, error details: " + e);
        }
    }
}
