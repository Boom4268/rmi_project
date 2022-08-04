package client;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import rmi.model.Account;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author MinhDuc
 */
public class ClientView extends javax.swing.JFrame implements ListSelectionListener {

    private Account acc;
    private List<Account> listAcc = new ArrayList<>();
    private AccountTable model;
    private int id = -1;
   
    public ClientView(Account acc) {
        initComponents();
        this.acc = acc;
        this.setInfor();
        model = new AccountTable(listAcc);
        this.accountTable.setModel(model);
        ListSelectionModel listModel = accountTable.getSelectionModel();
        listModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listModel.addListSelectionListener(this);
        
    }
    
    class AccountTable extends AbstractTableModel{
        
        private final String[] columns = new String[] { "Id", "UserName", "Password", "Role"};
        private List<Account> list;
        
        public AccountTable(List<Account> list) {
            this.list = list;
        }

        public List<Account> getList() {
            return list;
        }

        public void setList(List<Account> list) {
            this.list = list;
        }

        @Override
        public int getRowCount() {
            return list.size();
        }

        @Override
        public String getColumnName(int column) {
            return  columns[column];
        }
        
        @Override
        public int getColumnCount() {
            return columns.length;
        }
        
        public void is(){
            
        }
        
        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            if(columnIndex == 0){
                return this.list.get(rowIndex).getId();
            }else if(columnIndex == 1){
                return this.list.get(rowIndex).getUsername();
            }else if(columnIndex == 2){
                return this.list.get(rowIndex).getPassword();
            }else {
                return this.list.get(rowIndex).getRole() == 1 ? "Admin" : "User";
            }
        }
    }
    
    // ham bat su  kien khi select dong trong bang
    @Override
    public void valueChanged(ListSelectionEvent e) {
        int[] sel;
        Object value;
        if (!e.getValueIsAdjusting()) 
        {
          sel = accountTable.getSelectedRows();
          if (sel.length > 0) 
          {
            TableModel tm = accountTable.getModel();
            int id = (int) tm.getValueAt(sel[0],0);
            this.id = id;
            String username = (String) tm.getValueAt(sel[0],1);
            String password = (String) tm.getValueAt(sel[0],2);
            String role = (String) tm.getValueAt(sel[0],3);
            
            userNameChangeTf.setText(username);
            passwordChangeTf.setText(password);
            
            authoritieCheckBox.setSelectedIndex(role.equals("Admin") ? 0 : 1);
          }
        }
    }
    
    public void setInfor(){
        usernameLb.setText(this.acc.getUsername());
        passwordLb.setText(this.acc.getPassword());
        roleLb.setText(this.acc.getRole()==1 ? "Admin" : "User");
        tablePane1.setEnabledAt(1, this.acc.getRole()==1);
    }

    public Account getAcc() {
        return acc;
    }

    public void setAcc(Account acc) {
        this.acc = acc;
    }
    
    public void addChangePasswordListener(ActionListener e){
        this.changeBtn.addActionListener(e);
    }
    
    public void addUpdateListener(ActionListener e){
        this.updateBtn.addActionListener(e);
    }
    
    public void addDeleteListener(ActionListener e){
        this.deleteBtn.addActionListener(e);
    }
    
    public void addCreateListener(ActionListener e){
        this.createBtn.addActionListener(e);
    }
    
    public void setDataTable(List<Account> list){
        this.model.setList(list);
        this.model.fireTableDataChanged();
        //this.model.getColumnCount();
        //this.model.getRowCount();
    }
    
    public void showLog(String title, String mess){
        JOptionPane.showMessageDialog(null, mess, title, JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void resfresh(){
        this.id = -1;
        userNameChangeTf.setText("");
        passwordChangeTf.setText("");  
        authoritieCheckBox.setSelectedIndex(0);
    }
    
    public Account getAccountToChange(){
         
         String username = userNameChangeTf.getText().trim();
         String password = passwordChangeTf.getText().trim();
         int role = authoritieCheckBox.getSelectedIndex()==0 ? 1 : 2;
         return new Account(this.id, username, password, role);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        tablePane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        usernameLb = new javax.swing.JLabel();
        passwordLb = new javax.swing.JLabel();
        roleLb = new javax.swing.JLabel();
        changeBtn = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        accountTable = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        userNameChangeTf = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        passwordChangeTf = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        authoritieCheckBox = new javax.swing.JComboBox<>();
        updateBtn = new javax.swing.JButton();
        deleteBtn = new javax.swing.JButton();
        createBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("INFORMATION");

        jLabel2.setText("UserName:");

        jLabel3.setText("Password:");

        jLabel4.setText("Role:");

        usernameLb.setText("jLabel5");

        passwordLb.setText("jLabel5");

        roleLb.setText("jLabel5");

        changeBtn.setText("Change Password");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(149, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(changeBtn)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4))
                        .addGap(66, 66, 66)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(passwordLb, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(usernameLb, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(roleLb, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(100, 100, 100))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(255, 255, 255)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addComponent(jLabel1)
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(usernameLb))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(passwordLb))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(roleLb)
                    .addComponent(jLabel4))
                .addGap(47, 47, 47)
                .addComponent(changeBtn)
                .addContainerGap(91, Short.MAX_VALUE))
        );

        tablePane1.addTab("User", jPanel1);

        jPanel2.setDoubleBuffered(false);

        accountTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(accountTable);

        jLabel5.setText("UserName: ");

        jLabel6.setText("Password: ");

        jLabel7.setText("Role:");

        authoritieCheckBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "User" }));

        updateBtn.setText("Change");

        deleteBtn.setText("Delete");

        createBtn.setText("Create");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(userNameChangeTf, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(authoritieCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(passwordChangeTf, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addContainerGap(82, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(createBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(updateBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteBtn)
                        .addGap(26, 26, 26))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(userNameChangeTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(passwordChangeTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(authoritieCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(updateBtn)
                            .addComponent(deleteBtn)
                            .addComponent(createBtn))))
                .addContainerGap(76, Short.MAX_VALUE))
        );

        tablePane1.addTab("Manage", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tablePane1, javax.swing.GroupLayout.PREFERRED_SIZE, 692, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tablePane1, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable accountTable;
    private javax.swing.JComboBox<String> authoritieCheckBox;
    private javax.swing.JButton changeBtn;
    private javax.swing.JButton createBtn;
    private javax.swing.JButton deleteBtn;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField passwordChangeTf;
    private javax.swing.JLabel passwordLb;
    private javax.swing.JLabel roleLb;
    private javax.swing.JTabbedPane tablePane1;
    private javax.swing.JButton updateBtn;
    private javax.swing.JTextField userNameChangeTf;
    private javax.swing.JLabel usernameLb;
    // End of variables declaration//GEN-END:variables
}
