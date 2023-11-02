package client;

import interfaces_object.Employee;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Os
 */
public class Employee_Add extends JFrame {
    client_rmi clientController;
    Employee_List employeeList;
    public Employee_Add(Employee_List employeeList) {
        initComponents();
        this.clientController = new client_rmi();
        this.employeeList = employeeList;

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jLabel0 = new JLabel();
        jTextField1 = new JTextField();
        jLabel1 = new JLabel();
        jTextField2 = new JTextField();
        jLabel3 = new JLabel();
        jButton1 = new JButton();
        jButton2 = new JButton();
        jLabel5 = new JLabel();
        jTextField5 = new JTextField();
        jTextField6 = new JTextField();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        jLabel0.setFont(new Font("Tahoma", 1, 16)); // NOI18N
        jLabel0.setForeground(new Color(7, 62, 107, 178));
        jLabel0.setText("Code Employee");

        jTextField1.setFont(new Font("Tahoma", 0, 18)); // NOI18N

        jLabel1.setFont(new Font("Tahoma", 1, 16)); // NOI18N
        jLabel1.setForeground(new Color(7, 62, 107, 178));
        jLabel1.setText("Name");

        jTextField2.setFont(new Font("Tahoma", 0, 18)); // NOI18N


        jLabel3.setFont(new Font("Tahoma", 1, 16)); // NOI18N
        jLabel3.setForeground(new Color(7, 62, 107, 178));
        jLabel3.setText("Address");


        jButton1.setBackground(new Color(7, 62, 107, 178));
        jButton1.setFont(new Font("Tahoma", 1, 18)); // NOI18N
        jButton1.setForeground(new Color(204, 204, 204));
        jButton1.setText("Add");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new Color(7, 62, 107, 178));
        jButton2.setFont(new Font("Tahoma", 1, 18)); // NOI18N
        jButton2.setForeground(new Color(204, 204, 204));
        jButton2.setText("Cancle");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new Font("Tahoma", 1, 16)); // NOI18N
        jLabel5.setForeground(new Color(7, 62, 107, 178));
        jLabel5.setText("Age");

        jTextField5.setFont(new Font("Tahoma", 0, 18)); // NOI18N

        jTextField6.setFont(new Font("Tahoma", 0, 18)); // NOI18N


        GroupLayout jPanel1Layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel1)
                                                        .addComponent(jTextField2, GroupLayout.PREFERRED_SIZE, 280, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel5))
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel3)
                                                        .addComponent(jTextField5, GroupLayout.PREFERRED_SIZE, 280, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jTextField6, GroupLayout.PREFERRED_SIZE, 280, GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 107, Short.MAX_VALUE)
                                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                        .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                        .addComponent(jButton1, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                                                                        .addGap(40, 40, 40)
                                                                        .addComponent(jButton2, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
                                                        )
                                                )
                                        )
                                )
                                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel1)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jTextField2, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                                .addGap(30, 30, 30)
                                                .addComponent(jLabel3)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jTextField5, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
                                )
                                .addGap(30, 30, 30)
                                .addComponent(jLabel5)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButton2, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButton1, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextField6, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(59, Short.MAX_VALUE))
        );

        this.setPreferredSize(new Dimension(600, 350));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        pack();
        this.setLocationRelativeTo(null);
        this.setTitle("Add Reader Form");
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt){
        Employee employee = new Employee();
        employee.setName(jTextField2.getText());
        employee.setAddress(jTextField5.getText());
        employee.setAge(Integer.parseInt(jTextField6.getText()));


        if(jTextField2.getText().isEmpty() ||
                jTextField5.getText().isEmpty() ||
                jTextField6.getText().isEmpty()
        )
        {
            JOptionPane.showMessageDialog(this,
                    "Missing Info",
                    "Warning Title",
                    JOptionPane.WARNING_MESSAGE);
        }else{
            int check = clientController.insertEmployee(employee);
            if(check > 0){
                this.setVisible(false);
                JOptionPane.showMessageDialog(this,
                        "Insert employee success");
                employeeList.jButton1ActionPerformed(evt, "");
            }else{
                JOptionPane.showMessageDialog(this,
                        "Insert employee failed",
                        "Error title",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        this.setVisible(false);
    }


    private JButton jButton1;
    private JButton jButton2;
    private JLabel jLabel0;
    private JLabel jLabel1;
    private JLabel jLabel3;
    private JLabel jLabel5;
    private JTextField jTextField1;
    private JTextField jTextField2;
    private JTextField jTextField5;
    private JTextField jTextField6;
}
