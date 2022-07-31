package server;


import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import rmi.controller.AccountImpl;
import rmi.controller.AccountInterface;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author MinhDuc
 */
public class RMI {
  public static void main(String args[]){
      try{
          System.out.println("Server start ...");
          AccountInterface acc = new AccountImpl();
          LocateRegistry.createRegistry(1099);
          Naming.rebind("rmi://localhost:1099"
                  + "/AccountService", acc);
          System.out.println("Waiting for client invocations");
      }catch(Exception e){
          System.out.println(e);
      }
  }
}
