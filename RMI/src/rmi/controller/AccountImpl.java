/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rmi.controller;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import rmi.model.Account;

/**
 *
 * @author MinhDuc
 */
public class AccountImpl extends UnicastRemoteObject implements AccountInterface{
    
    public AccountImpl() throws RemoteException{
        super();
    }

    @Override
    public Account register(String username, String password) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public Account login(String username, String password) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public int create(Account account) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public Account update(int id, Account account) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void delete(int id) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public Account getById(int id) throws RemoteException {
        return new Account(1, "a","a",1);
    }

    @Override
    public Account[] getAll() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
}
