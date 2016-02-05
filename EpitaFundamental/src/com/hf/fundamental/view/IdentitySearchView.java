package com.hf.fundamental.view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.hf.fundamental.controller.ApplicationController;
import com.hf.fundamental.controller.ViewController;
import com.hf.fundamental.datamodel.Identity;
import com.hf.fundamental.view.listeners.BtnLogoutListener;
import com.hf.funproject.util.Reflection;

public class IdentitySearchView extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTable table;
	private JComboBox fieldComboBox; 

	public static void main(String[] args) {
		new IdentitySearchView().setVisible(true);
	}
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public IdentitySearchView() {
		initComponents();		
	}

	private void loadComboBox() {
		fieldComboBox.addItem("displayName");
		fieldComboBox.addItem("email");
	}

	private void loadTable(List<Identity> resultList) {				
		Object[][] data = new Object[resultList.size()][2];
		int i = 0;
		for (Identity identity : resultList) {
			data[i][0] = identity.getDisplayName();			
			data[i][1] = identity.getEmail();	            
			i++;
		}
		table.setModel(new javax.swing.table.DefaultTableModel(
				data,
				new String[]{
						"NAME", "EMAIL"
				}) {

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
	}

	private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(36, 63, 701, 58);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Field");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 21, 46, 14);
		panel.add(lblNewLabel);

		fieldComboBox = new JComboBox();
		fieldComboBox.setBounds(102, 20, 120, 20);
		panel.add(fieldComboBox);

		textField = new JTextField();
		textField.setBounds(367, 20, 147, 20);
		panel.add(textField);
		textField.setColumns(10);

		JLabel lblValue = new JLabel("Value");
		lblValue.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblValue.setBounds(311, 21, 46, 14);
		panel.add(lblValue);

		JButton searchButton = new JButton("Search");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchButtonActionPerformed(e);
			}		
		});
		searchButton.setBounds(585, 19, 89, 23);
		panel.add(searchButton);

		JLabel lblList = new JLabel("List");
		lblList.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblList.setBounds(340, 26, 78, 26);
		contentPane.add(lblList);
		
		JButton backButton = new JButton("<< Back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				backButtonActionPerformed();
			}		
		});
		backButton.setBounds(540, 317, 89, 33);
		contentPane.add(backButton);
		
		JButton LogOutButton = new JButton("Log Out");
		LogOutButton.addActionListener(new BtnLogoutListener());
		LogOutButton.setBounds(666, 317, 89, 33);
		contentPane.add(LogOutButton);
		
		JButton btnShowAll = new JButton("Show All");
		btnShowAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {			
					loadTable((ArrayList<Identity>) ApplicationController.getIdentityController().listAll());
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Error ocurred with datasource");
				}
			}
		});
		btnShowAll.setBounds(46, 322, 89, 23);
		contentPane.add(btnShowAll);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 132, 731, 174);
		contentPane.add(scrollPane);
		
				table = new JTable();
				scrollPane.setViewportView(table);
		
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {				
				if (!e.getValueIsAdjusting()) {
					ViewController.getInstance().showView(ViewIndex.DETAIL, sendObject(e));
				}			
			}

			public Identity sendObject(ListSelectionEvent e) {				
				int selectedRow = table.getSelectedRow();
				Identity identity = new Identity();
				for (int i = 0; i < table.getRowCount(); i++) {
					for (int j = 0; j < table.getColumnCount(); j++) {
						if (i == selectedRow) {
							identity.setDisplayName( (String) table.getValueAt(i, 0));							
							identity.setEmail(( (String) table.getValueAt(i, 1)) );
							break;
						}
					}
				}				
				return identity;
			}
		});
		
		loadComboBox();	
		try {			
			loadTable((ArrayList<Identity>) ApplicationController.getIdentityController().listAll());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error ocurred with datasource");
			System.out.println(e.toString());
		}
	}
	
	private void backButtonActionPerformed() {
		ViewController.getInstance().showView(ViewIndex.MENU);
	}
	
	private void searchButtonActionPerformed(ActionEvent e) {	
		String value = textField.getText();
		Identity criteria = new Identity();
		Reflection.invokeSetter(criteria, fieldComboBox.getSelectedItem().toString(), value);		
		try {			
			List <Identity> resultList = ApplicationController.getIdentityController().search(criteria);			
			loadTable(resultList);
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "Error loading data source");
			e1.printStackTrace();
		}
	}
}