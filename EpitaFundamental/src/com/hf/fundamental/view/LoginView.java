package com.hf.fundamental.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.hf.fundamental.controller.ApplicationController;
import com.hf.fundamental.controller.ViewController;
import com.hf.fundamental.dao.Storage;

public class LoginView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField userTextField;
	private JPasswordField passwordTextField;
	private ButtonGroup buttonGroup;
	private JRadioButton rdbtnXmlFiles;
	private JRadioButton rdbtnDataBase; 
	/**
	 * Create the frame.
	 */
	public LoginView() {
		ViewController.getInstance().addFrame(ViewIndex.LOGIN, this);
		initComponents();
	}

	private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("User Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(38, 86, 79, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(38, 142, 79, 14);
		contentPane.add(lblNewLabel_1);
		
		JButton quitButton = new JButton("Quit");
		quitButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				quitButtonActionPerformed(e);
			}
		});
		quitButton.setBounds(59, 227, 89, 23);
		contentPane.add(quitButton);
		
		userTextField = new JTextField();
		userTextField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		userTextField.setBounds(124, 85, 86, 20);
		contentPane.add(userTextField);
		userTextField.setColumns(10);
		
		passwordTextField = new JPasswordField();
		passwordTextField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		passwordTextField.setBounds(124, 139, 89, 20);
		contentPane.add(passwordTextField);
		
		JButton acceptButton = new JButton("Accept");
		acceptButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				acceptButtonActionPerformed(e);
			}
		});
		acceptButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		acceptButton.setBounds(196, 227, 101, 23);
		contentPane.add(acceptButton);
		
		JLabel lblNewLabel_2 = new JLabel("Login");
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 24));
		lblNewLabel_2.setBounds(144, 11, 89, 38);
		contentPane.add(lblNewLabel_2);
		
		JPanel panel = new JPanel();
		panel.setBounds(243, 89, 119, 67);
		contentPane.add(panel);
		
		rdbtnXmlFiles = new JRadioButton("XML Files");
		rdbtnXmlFiles.setSelected(true);
		panel.add(rdbtnXmlFiles);
		
		
		rdbtnDataBase = new JRadioButton("Data Base");
		panel.add(rdbtnDataBase);
		
		buttonGroup = new ButtonGroup();
		buttonGroup.add(rdbtnDataBase);
		buttonGroup.add(rdbtnXmlFiles);
		
		JLabel lblSelectFormatTo = new JLabel("Select format to store");
		lblSelectFormatTo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSelectFormatTo.setBounds(233, 64, 141, 14);
		contentPane.add(lblSelectFormatTo);
	}

	private void quitButtonActionPerformed(ActionEvent e) {
		System.exit(0);		
	}
	
	private void acceptButtonActionPerformed(ActionEvent e) {		
    	// Validates if fields are not empty
        if (userTextField.getText().equals("")){ 
            JOptionPane.showMessageDialog(null, "The must not be empty fields", "ERROR", JOptionPane.WARNING_MESSAGE);
            clearFields();
        } else {        
            String userName = userTextField.getText();
            String password = String.copyValueOf(passwordTextField.getPassword());   
            // Connect through Database.
            if (rdbtnDataBase.isSelected()) {
				ApplicationController.init(Storage.DERBY);
			}else{// Connect through XML
				ApplicationController.init(Storage.XML);
			}
            
            try {
				if( ApplicationController.getUserController().authenticate(userName, password) ){
					ViewController.getInstance().showView(ViewIndex.LIST);
					System.out.println("success");
				} else {
					System.out.println("failure");
					JOptionPane.showMessageDialog(null, "User or password incorrect");
					clearFields();	
				}
			} catch (Exception e1) {
				
				e1.printStackTrace();
			}            	
        }		        
	}
	
	private void clearFields() {
        userTextField.setText("");
        passwordTextField.setText("");
    }
}
