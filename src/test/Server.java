package test;

import server_1.EmployeeController_1;
import server_1.connectDB_1;

import java.io.*;
import java.net.InetAddress;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RMISocketFactory;
import java.sql.Connection;
import java.util.logging.*;

public class Server {
    private Connection connection;
    private String rmiService_Employee = "rmi_service_Employee_1";
    private static final Logger logger = Logger.getLogger(Server.class.getName());

    public Server(){
        // Cấu hình ghi log
        LogManager.getLogManager().reset();
        logger.setLevel(Level.ALL);
        connection = connectDB_1.getConnection();

        try {
            FileHandler fileHandler = new FileHandler("server.log");
            fileHandler.setLevel(Level.ALL);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Bắt đầu máy chủ
        int serverPort = 1099; // Cổng RMI
        try {
            Registry registry = LocateRegistry.createRegistry(serverPort);
            registry.bind(rmiService_Employee, new EmployeeController_1(connection));
            logger.info("Server is running on port " + serverPort);

            // Đợi client kết nối
            while (true) {
                // Xử lý đăng ký dịch vụ RMI từ client và ghi log
                // ...

                // Ví dụ:
//                InetAddress clientAddress = InetAddress.getByName(host);
//                 logger.info("Client connected: " + clientAddress);
//                String clientAddress = "Unknown";
//                try {
//                    clientAddress = RemoteServer.getClientHost();
//                } catch (ServerNotActiveException e) {
//                    e.printStackTrace();
//                }
//
//                System.out.println("Client connected from: " + clientAddress);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public java.net.Socket createSocket(String host, int port) {
        try {
            // Lấy địa chỉ IP của client
            InetAddress clientAddress = InetAddress.getByName(host);
            System.out.println("Client connected from IP: " + clientAddress.getHostAddress());
            return RMISocketFactory.getDefaultSocketFactory().createSocket(host, port);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



    public static void main(String[] args) {
        new Server();
    }
}
