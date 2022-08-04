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
        this.loginView.addRegisterListener(new RegisterListener());
    }
    
    class LoginListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                Account accNew = loginView.getAccount();
                if(null == accNew) return;
                Account res = accountServer.login(accNew.getUsername(), accNew.getPassword());
                if(null != res){
                    ClientView view = new ClientView(res);
                    view.addChangePasswordListener(new ChangePasswordListener());
                    view.addCreateListener(new CreateListener());
                    view.addUpdateListener(new UpdateListener());
                    view.addDeleteListener(new DeleteListener());
                    view.setVisible(true);
                    if(res.getRole() == 1){
                        view.setDataTable(accountServer.getAll());
                    }
                    
                    clientView = view;
                    loginView.setVisible(false);
                }else {
                    loginView.showMess("Incorrect !!!", "Fail");
                }
           }catch(Exception ex){
                System.out.println(e);
           }
        }  
    }
    
    class ChangePasswordListener implements ActionListener{
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
    
    class UpdateListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Account acc = clientView.getAccountToChange();
            if(acc.getPassword().equals("") || acc.getUsername().equals("")){
                clientView.showLog("Warning", "Do not leave information empty !!!");
                return;
            }
            if(acc.getId() == -1){
                clientView.showLog("Warning", "Choose a account !!!");
                return;
            }
            try {
                accountServer.update(acc);
                clientView.showLog("Success", "Update Success !");
                clientView.setDataTable(accountServer.getAll());
                clientView.resfresh();
            } catch (RemoteException ex) {
                
            }
        }
    }
    
    class DeleteListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Account acc = clientView.getAccountToChange();
            if(acc.getId() == -1){
                clientView.showLog("Warning", "Choose a account !!!");
                return;
            }
            try {
                accountServer.delete(acc);
                clientView.showLog("Success", "Delete Success !");
                clientView.setDataTable(accountServer.getAll());
                clientView.resfresh();
            } catch (RemoteException ex) {
                
            }
        }
    }
    
    class RegisterListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Account acc = loginView.getAccount();
             if(null == acc) return;
            try {
                int res = accountServer.create(acc);
                if(res == -1){
                    loginView.showMess("Account already exists ", "Fail");
                    return;
                }
                loginView.showMess("Register Success ", "Success");
                loginView.refresh();
            } catch (RemoteException ex) {
              
            }
        }
    }
    
    class CreateListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Account acc = clientView.getAccountToChange();
            if(acc.getPassword().equals("") || acc.getUsername().equals("")){
                clientView.showLog("Warning", "Do not leave information empty !!!");
                return;
            }
            try {
                int res = accountServer.create(acc);
                if(res == -1){
                    clientView.showLog("Fail", "Account already exists ");
                    return;
                }
                clientView.showLog("Success", "Register Success ");
                clientView.setDataTable(accountServer.getAll());
                clientView.resfresh();
            } catch (RemoteException ex) {
              
            }
        }
    }
    
}
