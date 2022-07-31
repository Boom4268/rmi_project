/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi.DAO;

import java.sql.Connection;
import java.sql.DriverManager;

public class DAO {
    private static String DB_URL = "jdbc:mysql://localhost:3306/rmi";
    private static String USER_NAME = "root";
    private static String PASSWORD = "minhbom4268";
    protected  Connection conn;
    public DAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
            System.out.println("connect successfully!");
        } catch (Exception ex) {
            System.out.println("connect failure!");
            ex.printStackTrace();
        }
    }
}
