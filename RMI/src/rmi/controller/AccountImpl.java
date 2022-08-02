/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rmi.controller;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import rmi.DAO.AccountDAO;
import rmi.model.Account;

/**
 *
 * @author MinhDuc
 */
public class AccountImpl extends UnicastRemoteObject implements AccountInterface{
    
    private AccountDAO accountDao;
    
    public AccountImpl() throws RemoteException{
        super();
        accountDao = new AccountDAO();
    }

    @Override
    public Account register(String username, String password) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public Account login(String username, String password) throws RemoteException {
        try {
            Account res = accountDao.checkLogin(username, password);
            return res;
        } catch (SQLException ex) {
            Logger.getLogger(AccountImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public int create(Account account) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void update(Account account) throws RemoteException {
        try {
            accountDao.updateAccount(account);
        } catch (SQLException ex) {
            Logger.getLogger(AccountImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(int id) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public Account getById(int id) throws RemoteException {
        try {
            return accountDao.getAccountById(id);
        } catch (SQLException ex) {
           return null;
        }
    }

    @Override
    public List<Account> getAll() throws RemoteException {
        try {
            return accountDao.getAllAccount();
        } catch (SQLException ex) {
            
        }
        return null;
    }
    
}
