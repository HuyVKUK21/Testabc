package synchronization;

import interfaces_object.*;
import server_1.*;
import server_2.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataSynchronization {
    private static Connection connection1 ;
    private static Connection connection2 ;

    public DataSynchronization(){
        // Kết nối đến cả hai cơ sở dữ liệu.
        this.connection1 = connectDB_1.getConnection();
        this.connection2 = connectDB_2.getConnection();

        synchronizeData();
    }

    public static void synchronizeData() {
        try {
            // Truy vấn và lấy dữ liệu từ bảng data_log trong cả hai cơ sở dữ liệu.
            ResultSet resultSet1 = getDataFromLogTable(connection1);
            ResultSet resultSet2 = getDataFromLogTable(connection2);

            // So sánh và đồng bộ hóa dữ liệu.
            synchronizeDataTables(resultSet1, resultSet2);

            // Đóng kết nối.
//            connection1.close();
//            connection2.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static ResultSet getDataFromLogTable(Connection connection) throws SQLException {
        String query = "SELECT * FROM data_log";
        PreparedStatement statement = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        return statement.executeQuery();
    }

    private static void synchronizeDataTables(ResultSet resultSet1, ResultSet resultSet2){
        try {
            while (resultSet1.next()) {
                // Lấy thông tin resultSet1 từ bảng data_log của database1.
                int id1 = resultSet1.getInt("id");
                String operation1 = resultSet1.getString("operation");
                String table1 = resultSet1.getString("table");
                String data1 = resultSet1.getString("data");

                boolean dataExists = false;
                while(resultSet2.next()){
                    int id2 = resultSet2.getInt("id");
                    String operation2 = resultSet2.getString("operation");
                    String table2 = resultSet2.getString("table");
                    String data2 = resultSet2.getString("data");

                    // nếu thông tin resultSet1 đã tồn tại trong database2 thì thoát vòng lặp
                    if(id2 == id1){
                        dataExists = true;
                        break;
                    }
                }

                // dataExists == false khi duyệt hết trong resultSet2 thông qua while
                // nhưng thông tin của resultSet1 ko tồn tại trong database2
                if(dataExists == false){
                    // cập nhật lại database 2 sau khi phân tích thông tin từ resultSet1 ...
                    // cập nhật ở đây có thể là INSERT, UPDATE, DELETE tùy theo operation1 trong resultSet1
                    try{
                        switch (table1) {
                            case "employee":
                                EmployeeController_2 employeeController_2 = new EmployeeController_2(connection2);
                                String[] tokens = data1.split("_");
                                int id = Integer.parseInt(tokens[0]);
                                String name = tokens[1];
                                String address = tokens[2];
                                int age = Integer.parseInt(tokens[3]);
                                Employee employee = new Employee(id, name, address, age);
                                databaseSynchronization(employeeController_2, operation1, employee);
                                break;
                            case "department":
                                break;
                            default:
                                break;
                        }
                    }catch(Exception e){
                        e.printStackTrace();
                    }

                    // insert thông tin resultSet1 đó vào data_log trong database2 ..
                    // logSynchronization(operation1, table1, data1);
                }

                // Reset resultSet2 để so sánh với dòng tiếp theo trong resultSet1.
                resultSet2.beforeFirst();
            }

            // Reset resultSet1..
            resultSet1.beforeFirst();
            // NGƯỢC LẠI TA CŨNG CẦN SO SÁNH THÔNG TIN TỪ resultSet2 VỚI database1
            while (resultSet2.next()) {
                // Lấy thông tin resultSet2 từ bảng data_log của database2.
                int id2 = resultSet2.getInt("id");
                String operation2 = resultSet2.getString("operation");
                String table2 = resultSet2.getString("table");
                String data2 = resultSet2.getString("data");

                boolean dataExists2 = false;
                while(resultSet1.next()){
                    int id1 = resultSet1.getInt("id");
                    String operation1 = resultSet1.getString("operation");
                    String table1 = resultSet1.getString("table");
                    String data1 = resultSet1.getString("data");

                    // nếu thông tin resultSet2 đã tồn tại trong database1 thì thoát vòng lặp
                    if(id1 == id2){
                        dataExists2 = true;
                        break;
                    }
                }

                // dataExists == false khi duyệt hết trong resultSet1 thông qua while
                // nhưng thông tin của resultSet2 ko tồn tại trong database1
                if(dataExists2 == false){
                    // cập nhật lại database1 sau khi phân tích thông tin từ resultSet2 ...
                    // cập nhật ở đây có thể là INSERT, UPDATE, DELETE tùy theo operation2 trong resultSet2
                    try{
                        switch (table2) {
                            case "employee":
                                EmployeeController_1 employeeController_1 = new EmployeeController_1(connection1);
                                String[] tokens = data2.split("_");
                                int id = Integer.parseInt(tokens[0]);
                                String name = tokens[1];
                                String address = tokens[2];
                                int age = Integer.parseInt(tokens[3]);
                                Employee employee = new Employee(id, name, address, age);
                                databaseSynchronization(employeeController_1, operation2, employee);
                                break;
                            case "department":
                                break;
                            default:
                                break;
                        }
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    // insert thông tin resultSet2 đó vào data_log trong database1 ..
                    // logSynchronization(operation2, table2, data2);
                }

                // Reset resultSet1 để so sánh với dòng tiếp theo trong resultSet2.
                resultSet1.beforeFirst();
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void databaseSynchronization(InterfaceDB interfaceDB, String operation, Object object) throws SQLException {
        try{
            if ("INSERT".equals(operation)) {
                interfaceDB.insert(object);
            } else if("UPDATE".equals(operation)){
                interfaceDB.update(object);
            } else if("DELETE".equals(operation)){
                interfaceDB.delete(object);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

//    private static void logSynchronization(String operation, String table, String data) throws SQLException {
//        // Ghi log đồng bộ hóa vào cả hai database.
//        // Ví dụ: INSERT INTO data_log (id, operation, data) VALUES (?, ?, ?);
//        String logQuery = "INSERT INTO data_log(operation, `table`, data) VALUES (?, ?, ?)";
//        PreparedStatement preparedStatement2 = connection2.prepareStatement(logQuery);
//        PreparedStatement preparedStatement1 = connection1.prepareStatement(logQuery);
//
//
//        preparedStatement2.setString(1, operation);
//        preparedStatement2.setString(2, table);
//        preparedStatement2.setString(3, data);
//
//        preparedStatement1.setString(1, operation);
//        preparedStatement1.setString(2, table);
//        preparedStatement1.setString(3, data);
//
//
//        preparedStatement2.executeUpdate();
//        preparedStatement1.executeUpdate();
//    }

//    private static void insertDataIntoDatabase2(Connection connection2, String data) throws SQLException {
//        // Thực hiện thao tác INSERT dữ liệu vào database2.
//        // Ví dụ: INSERT INTO table_name (data_column) VALUES (?);
//        String insertQuery = "INSERT INTO table_name (data_column) VALUES (?)";
//        PreparedStatement preparedStatement = connection2.prepareStatement(insertQuery);
//        preparedStatement.setString(1, data);
//        preparedStatement.executeUpdate();
//    }
//
//    private static void updateDataInDatabase2(Connection connection2, int id, String data) throws SQLException {
//        // Thực hiện thao tác UPDATE dữ liệu trong database2.
//        // Ví dụ: UPDATE table_name SET data_column = ? WHERE id = ?;
//        String updateQuery = "UPDATE table_name SET data_column = ? WHERE id = ?";
//        PreparedStatement preparedStatement = connection2.prepareStatement(updateQuery);
//        preparedStatement.setString(1, data);
//        preparedStatement.setInt(2, id);
//        preparedStatement.executeUpdate();
//    }
//
//    private static void deleteDataInDatabase2(Connection connection2, int id) throws SQLException {
//        // Thực hiện thao tác DELETE dữ liệu trong database2.
//        // Ví dụ: DELETE FROM table_name WHERE id = ?;
//        String deleteQuery = "DELETE FROM table_name WHERE id = ?";
//        PreparedStatement preparedStatement = connection2.prepareStatement(deleteQuery);
//        preparedStatement.setInt(1, id);
//        preparedStatement.executeUpdate();
//    }
//
//    private static void logSynchronization(Connection connection1, Connection connection2, int id, String operation, String data) throws SQLException {
//        // Ghi log đồng bộ hóa vào cả hai database.
//        // Ví dụ: INSERT INTO data_log (id, operation, data) VALUES (?, ?, ?);
//        String logQuery = "INSERT INTO data_log (id, operation, data) VALUES (?, ?, ?";
//        PreparedStatement preparedStatement1 = connection1.prepareStatement(logQuery);
//        PreparedStatement preparedStatement2 = connection2.prepareStatement(logQuery);
//
//        preparedStatement1.setInt(1, id);
//        preparedStatement1.setString(2, operation);
//        preparedStatement1.setString(3, data);
//
//        preparedStatement2.setInt(1, id);
//        preparedStatement2.setString(2, operation);
//        preparedStatement2.setString(3, data);
//
//        preparedStatement1.executeUpdate();
//        preparedStatement2.executeUpdate();
//    }

//    public static void main(String[] args) {
//        synchronizeData();
//    }
}
