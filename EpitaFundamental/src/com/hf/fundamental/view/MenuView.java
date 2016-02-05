package com.hf.fundamental.view;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.hf.fundamental.controller.ViewController;
import com.hf.fundamental.view.listeners.BtnCreateIdentityListener;
import com.hf.fundamental.view.listeners.BtnIdentitiesListener;
import com.hf.fundamental.view.listeners.BtnLogoutListener;

public class MenuView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuView frame = new MenuView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MenuView() {
		ViewController.getInstance().addFrame(ViewIndex.MENU, this);
		initComponents();
	}
	
	private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnIdentities = new JButton("Identities");
		btnIdentities.addActionListener(new BtnIdentitiesListener());
		btnIdentities.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnIdentities.setBounds(120, 56, 160, 35);
		contentPane.add(btnIdentities);
		
		JButton btnCreateIdentity = new JButton("Create Identity");
		btnCreateIdentity.addActionListener(new BtnCreateIdentityListener());
		btnCreateIdentity.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCreateIdentity.setBounds(120, 115, 160, 35);
		contentPane.add(btnCreateIdentity);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new BtnLogoutListener());
		btnLogout.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnLogout.setBounds(120, 176, 160, 35);
		contentPane.add(btnLogout);
	}
}
