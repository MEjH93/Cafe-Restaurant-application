package com.comtrade.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.comtrade.constants.ConstBL;
import com.comtrade.constants.ConstUI;
import com.comtrade.controllerFront.ControllerUI;
import com.comtrade.domain.RestaurantTable;
import com.comtrade.domain.User;
import com.comtrade.transfer.TransferClass;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class WaiterForm extends JFrame {

	private JPanel contentPane;
	List<RestaurantTable> tables = new ArrayList<>();
	int table_number_traveling = 0;
	List<Integer> tableNumberList = new ArrayList<>();
	RestaurantTable restaurantTableGET = new RestaurantTable();
	

	 

	public WaiterForm(User user) throws InterruptedException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 719, 596);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblRestaurantTables = new JLabel("Choose restaurant table");
		lblRestaurantTables.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblRestaurantTables.setBounds(219, 66, 281, 14);
		contentPane.add(lblRestaurantTables);
		
		JComboBox cbTables = new JComboBox();
		cbTables.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		cbTables.setBounds(219, 181, 183, 22);
		contentPane.add(cbTables);
		
		fillInComboBox(cbTables);
		
		JButton btnAccess = new JButton("Get into the selected table");
		btnAccess.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integer a =  (Integer) cbTables.getSelectedItem();
				RestaurantTable restaurantTable = new RestaurantTable();
				restaurantTable.setTable_number(a);
				restaurantTableGET.setTable_number(a);
				table_number_traveling = a;
				tableNumberList.add(a);
				String abc = "gejh";
			
						//OrderForm orderForm = new OrderForm(a);
						//orderForm(a).setVisible(true);
						new OrderForm(a,user).setVisible(true);
				
			}
			
		});
		
		
		btnAccess.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnAccess.setBounds(219, 376, 183, 23);
		contentPane.add(btnAccess);
		setResizable(false);
		
		

	}

	RestaurantTable getRestaurantTableGET() {
		return restaurantTableGET;
	}



	private void fillInComboBox(JComboBox cbTables) throws InterruptedException {
		try {
				TransferClass request = TransferClass.create(null, ConstUI.RESTAURANT_TABLE,ConstBL.GET);
				List<Object> tablezz = (List<Object>) ControllerUI.getControllerUI().execute(request).getResponse();
				//List<RestaurantTable> list = new ArrayList<>();
				//list.add((RestaurantTable) tablezz);
				cbTables.removeAllItems();
				for (Object table : tablezz) {
					cbTables.addItem(table);
				}
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
}

	}
	
}
