/**
 * CreateIdentityView.java
 */
package com.hf.fundamental.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.hf.fundamental.controller.ApplicationController;
import com.hf.fundamental.controller.ViewController;
import com.hf.fundamental.datamodel.Identity;
import com.hf.fundamental.view.listeners.BtnIdentitiesListener;
import com.hf.fundamental.view.listeners.BtnLogoutListener;

/**
 * The {@code CreateIdentityView} JFrame displays the <i>GUI</i> that contains the components for 
 * filling {@link Identity}'s fields in order to create a new {@code Identity}
 * @author Hoang / Favio
 *
 */
public class CreateIdentityView extends JFrame {
	
	private static final long serialVersionUID = 1L;
	/**
	 * Swing Components declaration
	 */
	private JPanel contentPane;
	private JTextField textName;
	private JTextField textEmail;
	private JButton btnAddAttribute;
	private JList<String> list;
	private JTextField textAttributeKey;
	private JLabel lblIdentity;
	private JLabel lblKey;
	private JTextField textAttributeValue;
	private JLabel lblValue;
	private JButton btnDeleteAttribute;
	private JButton btnReset;
	private JButton btnCreate;
	private JButton btnIdentities;
	private JButton btnLogout;
	private JScrollPane scrollPane;
	private DefaultListModel<String> listModel;
	private Identity currentIdentity;
	private Map<String, String> attributes;

	/**
	 * Create the frame.
	 */
	public CreateIdentityView() {
		ViewController.getInstance().addFrame(ViewIndex.CREATE, this);
		initComponents();
		currentIdentity = new Identity();
		attributes = new HashMap<String, String>();
	}

	/**
	 * Initialize Swing Components
	 */
	private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Name:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(29, 44, 80, 30);
		contentPane.add(lblNewLabel);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEmail.setBounds(29, 75, 80, 30);
		contentPane.add(lblEmail);
		
		textName = new JTextField();
		textName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textName.setBounds(106, 46, 270, 28);
		contentPane.add(textName);
		textName.setColumns(10);
		
		textEmail = new JTextField();
		textEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textEmail.setColumns(10);
		textEmail.setBounds(106, 77, 270, 28);
		contentPane.add(textEmail);
		
		btnAddAttribute = new JButton("Add Attribute");
		btnAddAttribute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addAttribute();
			}
		});
		btnAddAttribute.setBounds(313, 180, 117, 36);
		contentPane.add(btnAddAttribute);
		
		list = new JList<String>();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setBounds(29, 117, 218, 128);
		
		scrollPane = new JScrollPane(list);
		scrollPane.setBounds(29, 117, 218, 128);
		contentPane.add(scrollPane);
		
		textAttributeKey = new JTextField();
		textAttributeKey.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textAttributeKey.setColumns(10);
		textAttributeKey.setBounds(313, 112, 160, 28);
		contentPane.add(textAttributeKey);
		
		lblIdentity = new JLabel("New Identity");
		lblIdentity.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblIdentity.setBounds(29, 6, 218, 28);
		contentPane.add(lblIdentity);
		
		lblKey = new JLabel("Key:");
		lblKey.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblKey.setBounds(264, 112, 49, 30);
		contentPane.add(lblKey);
		
		textAttributeValue = new JTextField();
		textAttributeValue.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textAttributeValue.setColumns(10);
		textAttributeValue.setBounds(313, 145, 160, 28);
		contentPane.add(textAttributeValue);
		
		lblValue = new JLabel("Value:");
		lblValue.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblValue.setBounds(264, 145, 49, 30);
		contentPane.add(lblValue);
		
		btnDeleteAttribute = new JButton("Delete Attribute");
		btnDeleteAttribute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteAttribute();
			}
		});
		btnDeleteAttribute.setBounds(313, 222, 117, 36);
		contentPane.add(btnDeleteAttribute);
		
		btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset();
			}
		});
		btnReset.setBounds(141, 270, 90, 36);
		contentPane.add(btnReset);
		
		btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createIdentity();
			}
		});
		btnCreate.setBounds(29, 270, 90, 36);
		contentPane.add(btnCreate);
		
		btnIdentities = new JButton("Identities");
		btnIdentities.addActionListener(new BtnIdentitiesListener());
		btnIdentities.setBounds(260, 270, 90, 36);
		contentPane.add(btnIdentities);
		
		btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new BtnLogoutListener());
		btnLogout.setBounds(377, 270, 90, 36);
		contentPane.add(btnLogout);
		
		listModel = new DefaultListModel<String>();
		list.setModel(listModel);
		list.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting()) {
					loadAttribute();
				}
			}
		});
		
		addComponentListener(new ComponentAdapter() {
			public void componentHidden(ComponentEvent e) {
				currentIdentity = new Identity();
				reset();
			}
			public void componentShown(ComponentEvent e) {
				
			}
		});
	}
	
	/**
	 * Action Listener Methods 
	 */
	private void loadAttribute() {
		String[] array = list.getSelectedValue().split(":");
		textAttributeKey.setText(array[0]);
		textAttributeValue.setText(array[1]);
	}

	private void deleteAttribute() {
		System.out.println(list.getSelectedIndex());
		if (list.getSelectedIndex() != -1) {
			String[] array = list.getSelectedValue().split(":");
			attributes.remove(array[0]);
			listModel.remove(list.getSelectedIndex());
		}
	}
	
	private void addAttribute() {
		if (!attributes.containsKey(textAttributeKey.getText())) {
			listModel.addElement(textAttributeKey.getText() + ":" + textAttributeValue.getText());
		} else {
			listModel.set(list.getSelectedIndex(), textAttributeKey.getText() + ":" + textAttributeValue.getText());
		}
		
		attributes.put(textAttributeKey.getText(), textAttributeValue.getText());
		textAttributeKey.setText("");
		textAttributeValue.setText("");
	}
	
	private void reset() {
		textName.setText("");
		textEmail.setText("");
		textAttributeKey.setText("");
		textAttributeValue.setText("");
		listModel.clear();
		attributes.clear();
	}
	
	private void initIdentity() {
		currentIdentity.setDisplayName(textName.getText());
		currentIdentity.setEmail(textEmail.getText());
		currentIdentity.setUid(UUID.randomUUID().toString());
		currentIdentity.setAttributes(attributes);
	}
	
	private void createIdentity() {
		initIdentity();
		
		if (currentIdentity.isValid()) {
			try {
				ApplicationController.getIdentityController().create(currentIdentity);
				ViewController.getInstance().showView(ViewIndex.LIST);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			// TODO: Display error.
		}
	}
}
