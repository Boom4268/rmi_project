/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package client;

import java.rmi.Naming;
import rmi.controller.AccountInterface;

/**
 *
 * @author MinhDuc
 */
public class RMIClient {
    public static void main(String args[]){
        try{
            AccountInterface acc = (AccountInterface) Naming.lookup("rmi://localhost/AccountService");
            System.out.println(acc.getById(0).getUsername());
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
