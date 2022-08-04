/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import rmi.model.Account;

public class AccountDAO extends DAO{

    public AccountDAO() {
        super();
    }
    
    public Account checkLogin(String username, String password) throws SQLException{
        
        String sql = "SELECT * FROM account as acc "
                    + " WHERE acc.user_name = ? AND acc.password= ?";
        PreparedStatement pstm = this.conn.prepareStatement(sql);
        pstm.setString(1, username);
        pstm.setString(2, password);
        ResultSet rs = pstm.executeQuery();
        Account acc = null;
        while (rs.next()) {
            int id = rs.getInt("id");
            String usernameAcc = rs.getString("user_name");
            String passwordAcc = rs.getString("password");
            int role = rs.getInt("role");
           
            acc = new Account(id,usernameAcc, passwordAcc, role );  
        }
        return acc;
    }
    
    public Account getAccountById(int id) throws SQLException{
        String sql = "SELECT * FROM account as acc "
                    + " WHERE acc.id = ? ";
        PreparedStatement pstm = this.conn.prepareStatement(sql);
        pstm.setInt(1, id);
        ResultSet rs = pstm.executeQuery();
        Account acc = null;
        while (rs.next()) {
            int idAcc = rs.getInt("id");
            String usernameAcc = rs.getString("user_name");
            String passwordAcc = rs.getString("password");
            int role = rs.getInt("role");
           
            acc = new Account(id,usernameAcc, passwordAcc, role );  
        }
        return acc;
    }
    
    public List<Account> getAllAccount() throws SQLException{
        List<Account> list = new ArrayList<>();
        
        Statement statement = this.conn.createStatement();
        String sql = "SELECT * FROM account";
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            int id = rs.getInt("id");
            String usernameAcc = rs.getString("user_name");
            String passwordAcc = rs.getString("password");
            int role = rs.getInt("role");
           
            list.add(new Account(id,usernameAcc, passwordAcc, role ));  
        }
        return list;
    }
    
    //quy dinh: -: account is exsited; != -1 : create sussecss
    public int createAccount(Account acc) throws SQLException{
        String sql = "SELECT * FROM account "
                    + " WHERE user_name = ? ";
        PreparedStatement pstm = this.conn.prepareStatement(sql);
        pstm.setString(1, acc.getUsername());
        ResultSet rs = pstm.executeQuery();
        int temp = 0;
        while (rs.next()) {
           temp ++ ;
        }
        if(temp != 0) return -1;
        else {
            sql =  "INSERT INTO `account` (`user_name`, `password`, `role`) VALUES (?,?,?)";
            pstm = this.conn.prepareStatement(sql);
            pstm.setString(1, acc.getUsername());
            pstm.setString(2, acc.getPassword());
            pstm.setInt(3, acc.getRole());
            int res = pstm.executeUpdate();
            return 1;
        }
    }
    
    public void updateAccount(Account acc) throws SQLException{
        String sql = "UPDATE account SET user_name = ?, password = ?, role = ? "
                + " WHERE id = ? ";
        PreparedStatement pstm = this.conn.prepareStatement(sql);
        pstm.setString(1, acc.getUsername());
        pstm.setString(2, acc.getPassword());
        pstm.setInt(3, acc.getRole());
        pstm.setInt(4, acc.getId());
        pstm.executeUpdate();
    }
    
    public void deleteAccount(Account acc) throws SQLException{
        String sql = "DELETE FROM account as acc "
                    + " WHERE acc.id = ? ";
        PreparedStatement pstm = this.conn.prepareStatement(sql);
        pstm.setInt(1, acc.getId());
        pstm.executeUpdate();
    }
    
    public static void main(String[] args) throws SQLException {
        // test ket noi
        AccountDAO test = new AccountDAO();
        Account acc = test.checkLogin("phan_duc", "123");
        if(null != acc){
            System.out.println("Login success !!!");
            System.out.println("Id = " + acc.getId() + "user_name = "+ acc.getUsername() + "password = "+ acc.getPassword() + " role = " + acc.getRole());
        }else {
            System.out.println("Login fail !!!");
        }
        
    }
}
