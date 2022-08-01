/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import rmi.controller.AccountInterface;
import rmi.model.Account;

public class ClientController {
    private LoginView loginView;
    private ClientView clientView;
    private UpdateView updateView;
    private AccountInterface accountServer;
   
    public ClientController(LoginView loginView) {
        try {
            this.accountServer = (AccountInterface) Naming.lookup("rmi://localhost/AccountService");
        } catch (NotBoundException ex) {
            System.out.println("");
        } catch (MalformedURLException ex) {
          
        } catch (RemoteException ex) {
            
        }
        this.loginView = loginView;
        this.loginView.addLoginListener(new LoginListener());
    }
    
    class LoginListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                Account accNew = loginView.getAccount();
                Account res = accountServer.login(accNew.getUsername(), accNew.getPassword());
                if(null != res){
                    System.out.println("Login thanh cong !!! id_user = " + res.getId());
                    ClientView view = new ClientView(res);
                    view.addChangeListener(new UpdateListener());
                    view.setVisible(true);
                    clientView = view;
                    loginView.setVisible(false);
                }else {
                    loginView.showMess("Tên tài khoản hoặc mật khẩu không đúng !!!");
                }
           }catch(Exception ex){
                System.out.println(e);
           }
        }  
    }
    
    class UpdateListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            updateView = new UpdateView();
            updateView.addSaveListener(new SaveListener());
            updateView.setVisible(true);
        }  
    }
    
    class SaveListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
           String newPass = updateView.getNewPassword();
           System.out.println("new = " + newPass);
           if(newPass.equals("")){
               updateView.showMessErr();
               return;
           }
           Account accNew = clientView.getAcc();
           accNew.setPassword(newPass);
            try {
                accountServer.update(accNew);
                updateView.showMessSuccess();
                updateView.dispose();
                clientView.setAcc(accountServer.getById(accNew.getId()));
                clientView.setInfor();
            } catch (RemoteException ex) {
                
            }
        }  
    }
    
}
