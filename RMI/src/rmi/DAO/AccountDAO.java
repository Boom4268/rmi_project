/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    
    public Account getAccountById(int id){
        return null;
    }
    
    public List<Account> getAllAccount(){
        return null;
    }
    
    public void creatAccount(Account acc){
        
    }
    
    public void updateAccount(Account acc){
        
    }
    
    public void deleteAccount(Account acc){
        
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
