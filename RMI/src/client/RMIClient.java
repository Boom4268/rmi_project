/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package client;

/**
 *
 * @author MinhDuc
 */
public class RMIClient {
    public static void main(String args[]){
        LoginView view = new LoginView();
        ClientController clientController = new ClientController(view);
        view.setVisible(true);
    }
}
