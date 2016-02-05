package com.hf.fundamental.view;

import java.awt.Font;
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
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.hf.fundamental.controller.ApplicationController;
import com.hf.fundamental.controller.ViewController;
import com.hf.fundamental.datamodel.Identity;
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
		loadComboBox();	
		try {			
			loadTable((ArrayList<Identity>) ApplicationController.getIdentityController().listAll());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error ocurred with datasource");
		}
	}

	private void loadComboBox() {
//		DefaultComboBoxModel model = new DefaultComboBoxModel<>();
		List<Field> listFields = Reflection.getFields(new Identity());		
		for (Field field : listFields) {
			fieldComboBox.addItem(field.getName());
		}
		
	}

	private void loadTable(List<Identity> resultList) {				
		Object[][] data = new Object[resultList.size()][3];
		int i = 0;
		for (Identity identity : resultList) {
			data[i][0] = identity.getDisplayName();
			data[i][1] = identity.getUid();
			data[i][2] = identity.getEmail();	            
			i++;
		}
		table.setModel(new javax.swing.table.DefaultTableModel(
				data,
				new String[]{
						"NAME", "UID", "EMAIL"
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
		textField.setBounds(428, 20, 86, 20);
		panel.add(textField);
		textField.setColumns(10);

		JLabel lblValue = new JLabel("Value");
		lblValue.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblValue.setBounds(343, 23, 46, 14);
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

		table = new JTable();
		table.setBounds(36, 145, 701, 161);
		contentPane.add(table);

		JButton backButton = new JButton("<< Back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				backButtonActionPerformed();
			}		
		});
		backButton.setBounds(540, 317, 89, 33);
		contentPane.add(backButton);
		
		JButton LogOutButton = new JButton("Log Out");
		LogOutButton.setBounds(666, 317, 89, 33);
		contentPane.add(LogOutButton);
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
		}
	}
}