/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rmi.controller;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import rmi.model.Account;

/**
 *
 * @author MinhDuc
 */
public interface AccountInterface extends Remote{
    
    public Account login(String username, String password) throws RemoteException;
    
    public int create(Account account) throws RemoteException;
    
    public void update(Account account) throws RemoteException;
    
    public void delete(Account account) throws RemoteException;

    public Account getById(int id) throws RemoteException;

    public List<Account> getAll() throws RemoteException;

}
