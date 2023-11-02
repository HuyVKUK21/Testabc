package client;

import interfaces_object.InterfaceDB;
import interfaces_object.Employee;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

public class client_rmi {
    private String serverHost = "localhost";
    private int serverPort = 1234;
    private Registry registry;
    private InterfaceDB<Employee> rmiServerEmployee;
    private String rmiService_Employee = "rmi_service_Employee_1";

    // CLIENT NÀY SỬ DỤNG SERVICE CỦA SERVER1


    public client_rmi() {
        try {
            registry = LocateRegistry.getRegistry(serverHost, serverPort);
            rmiServerEmployee = (InterfaceDB<Employee>) registry.lookup(rmiService_Employee);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Employee> selectAllEmployee(){
        ArrayList<Employee> listEmployee = null;
        try{
            listEmployee = rmiServerEmployee.selectAll();
        }catch(Exception e){
            e.printStackTrace();
        }
        return listEmployee;
    }

    public int insertEmployee(Employee employee){
        int check = 0;
        try{
            check = rmiServerEmployee.insert(employee);
        }catch(Exception e){
            e.printStackTrace();
        }
        return check;
    }

    public int updateEmployee(Employee employee){
        int check = 0;
        try{
            check = rmiServerEmployee.update(employee);
        }catch(Exception e){
            e.printStackTrace();
        }
        return check;
    }

    public int deleteEmployee(Employee employee){
        int check = 0;
        try{
            check = rmiServerEmployee.delete(employee);
        }catch(Exception e){
            e.printStackTrace();
        }
        return check;
    }

}
