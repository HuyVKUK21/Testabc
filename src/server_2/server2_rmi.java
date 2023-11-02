package server_2;


import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.Connection;

public class server2_rmi {
    private int serverPort = 4321;
    private Registry registry;
    private Connection connection;
    private String rmiService_Employee = "rmi_service_Employee_2";

    public server2_rmi(){
        connection = connectDB_2.getConnection();

        if (connection != null) {
            System.out.println("Connection success!");
        }

        try {
            registry = LocateRegistry.createRegistry(serverPort);
            registry.bind(rmiService_Employee, new EmployeeController_2(connection));
            System.out.println("Server Started!");
        } catch (Exception e) {
            System.err.println("Server initialization error, error details: " + e);
        }

    }
}
