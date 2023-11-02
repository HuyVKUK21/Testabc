package server_2;

import interfaces_object.InterfaceDB;
import interfaces_object.Employee;
import synchronization.insertLog;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class EmployeeController_2 extends UnicastRemoteObject implements InterfaceDB<Employee> {
    private Connection connection;
    public EmployeeController_2(Connection connection) throws RemoteException {
        this.connection = connection;
    }

    @Override
    public ArrayList<Employee> selectAll() throws RemoteException {
        ArrayList<Employee> check = new ArrayList<Employee>();
        try {
            String sql = "SELECT * FROM employee";

            PreparedStatement pst = connection.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();

            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String address = rs.getString("address");
                int age = rs.getInt("age");

                Employee employee = new Employee(id, name, address, age);
                check.add(employee);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }

    @Override
    public int insert(Employee employee) throws RemoteException {
        int check = 0;
        try {
            // Tạo ra đối tượng PreparedStatement
            String sql = "INSERT INTO employee(name, address, age)"
                    + " VALUE(?, ?, ?)";
            PreparedStatement pst = connection.prepareStatement(sql);

            pst.setString(1, employee.getName());
            pst.setString(2, employee.getAddress());
            pst.setInt(3, employee.getAge());

            // Thực thi 1 câu lệnh SQL
            check = pst.executeUpdate();

            // Xử lý kết quả
            if(check > 0){
                System.out.println("Insert Employee Success");
                String data = employee.getId() + "_" + employee.getName() + "_" + employee.getAddress() + "_" + employee.getAge();
                new insertLog(connection, "INSERT", "employee", data);
            }else{
                System.out.println("Insert Employee Failed");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }

    @Override
    public int update(Employee employee) throws RemoteException {
        int check = 0;
        try {
            String sql = "UPDATE employee" +
                    " SET " +
                    " name= ?," +
                    " address= ?," +
                    " age= ?" +
                    " WHERE id= ? ";

            PreparedStatement pst = connection.prepareStatement(sql);

            pst.setString(1, employee.getName());
            pst.setString(2, employee.getAddress());
            pst.setInt(3, employee.getAge());
            pst.setInt(4, employee.getId());

            check = pst.executeUpdate();
            if(check > 0){
                System.out.println("Update Employee Success");
                String data = employee.getId() + "_" + employee.getName() + "_" + employee.getAddress() + "_" + employee.getAge();
                new insertLog(connection, "UPDATE", "employee", data);
            }else{
                System.out.println("Update Employee Failed");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }

    @Override
    public int delete(Employee employee) throws RemoteException {
        int check = 0;
        try {

            String sql = "DELETE FROM employee" +
                    " WHERE id= ?";

            PreparedStatement pst = connection.prepareStatement(sql);

            pst.setInt(1, employee.getId());

            // check = số lượng dòng thay đổi trong database
            check = pst.executeUpdate();
            if(check > 0){
                System.out.println("Delete Employee Success");
                String data = employee.getId() + "_" + employee.getName() + "_" + employee.getAddress() + "_" + employee.getAge();
                new insertLog(connection, "DELETE", "employee", data);
            }else{
                System.out.println("Delete Employee Failed");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }
}
