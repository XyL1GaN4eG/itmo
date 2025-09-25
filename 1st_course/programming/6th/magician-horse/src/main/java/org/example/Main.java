package org.example;

import java.beans.Statement;
import java.rmi.ConnectException;
import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5433/studs","s408614","mCL2Y1wQAwyz3d9G");
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM person");
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        System.out.println(resultSet.getString("fullname"));
    }
}