package com.comtrade.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import com.comtrade.constants.ConstBL;
import com.comtrade.constants.ConstImages;
import com.comtrade.constants.ConstUI;
import com.comtrade.controllerFront.ControllerUI;
import com.comtrade.domain.Offers;
import com.comtrade.domain.RestaurantTable;
import com.comtrade.domain.Restaurants;
import com.comtrade.domain.User;

import com.comtrade.transfer.TransferClass;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

public class RestaurantTablesForm extends JFrame {

	private JPanel contentPane;
	private JButton btnTable1;
	private JButton btnTable2;
	private List<Offers> menuList = new ArrayList<>();
	private JButton btnUpdateTables;
	private List<JButton> buttonList = new ArrayList<>();
	private Restaurants restaurant;
	private User user;
	private List<RestaurantTable>nonBusyTables = new ArrayList<>();
	private RestaurantTable restaurantTable;
	private JLabel lblNewLabel;
	private JTextField tfTableNumber;
	private JButton btnCreateTables;
	private JLabel lblNewLabel_2;
	private JLabel lblNumberTable;
	

	public RestaurantTablesForm(User user) throws Exception {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 731, 585);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnTable2 = new JButton("Table 2");
		btnTable2.setBounds(455, 313, 250, 189);
		contentPane.add(btnTable2);
		
		btnTable1 = new JButton("Table 1");
		btnTable1.setBounds(10, 313, 250, 189);
		contentPane.add(btnTable1);
		
		btnUpdateTables = new JButton("Update tables");
		btnUpdateTables.setBounds(276, 512, 148, 23);
		contentPane.add(btnUpdateTables);
		
		lblNewLabel = new JLabel("Create tables in restaurant");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel.setBounds(250, 38, 290, 14);
		contentPane.add(lblNewLabel);
		
		tfTableNumber = new JTextField();
		tfTableNumber.setBounds(366, 72, 86, 20);
		contentPane.add(tfTableNumber);
		tfTableNumber.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Table number");
		lblNewLabel_1.setBounds(253, 75, 103, 14);
		contentPane.add(lblNewLabel_1);
		
		btnCreateTables = new JButton("Create Table Number");
		btnCreateTables.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int tableNumber = Integer.parseInt(tfTableNumber.getText());
				RestaurantTable restaurantTable = new RestaurantTable();
				restaurantTable.setTable_number(tableNumber);

					try {
						TransferClass transferClass = ControllerUI.getControllerUI()
									.execute(TransferClass.create(restaurantTable, ConstUI.RESTAURANT_TABLE, ConstBL.POST));
						JOptionPane.showMessageDialog(null,"A table with number "+ tfTableNumber.getText()+" has been created");	
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			
					
			}
		});
		btnCreateTables.setBounds(250, 123, 161, 23);
		contentPane.add(btnCreateTables);
		
		lblNewLabel_2 = new JLabel("CODE BELOW IS IN PROGRESS");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_2.setBounds(215, 277, 622, 25);
		contentPane.add(lblNewLabel_2);
		
		lblNumberTable = new JLabel("");
		lblNumberTable.setBounds(419, 127, 46, 14);
		contentPane.add(lblNumberTable);
		lblNumberTable.setText(tfTableNumber.getText());
		
		//list of restaurants has to be returned from managerform with user parameter?
		this.user = user;
		//restaurants equals to restaurants that are found first ???
		buttonList = attachButtonsToList();
		//setColours();
		btnTable1.addActionListener(new ButtonListener());
        btnTable2.addActionListener(new ButtonListener());
        btnUpdateTables.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                	//setColours();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
    }
	private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            int numberOfTable = 0;
            JButton buttonClicked = (JButton) actionEvent.getSource();
            int tableNumberArrived = Integer.parseInt(buttonClicked.getName());
            switch (tableNumberArrived) {
                case 1:
                	numberOfTable = 1;
                    break;
                case 2:
                	numberOfTable = 2;
                    break;
            }
            try {
            	restaurantTable  = returnRestaurantTable(numberOfTable, restaurant.getId_restaurant());
            } catch (Exception e) {
                e.printStackTrace();
            }
            OrderForm orderForm = null;
            try {
              //  orderForm = new OrderForm(user, restaurant, restaurantTable);
            } catch (Exception e) {
                e.printStackTrace();
            }
            orderForm.setVisible(true);
            try {
            	//setColours();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    private List<JButton> attachButtonsToList() {
        buttonList.add(btnTable1);
        buttonList.add(btnTable2);
        return buttonList;
    }

    public static RestaurantTable returnRestaurantTable(int table_number, int id_restaurant) throws Exception 
    	{
    	RestaurantTable returnRestaurantTable = new RestaurantTable(table_number, id_restaurant);
        TransferClass transferClass = TransferClass.create(returnRestaurantTable, ConstUI.RESTAURANT, ConstBL.RETURN_RESTAURANT_TABLE);
        try 
        {
        	returnRestaurantTable = (RestaurantTable) ControllerUI.getControllerUI().execute(transferClass).getResponse();
        } catch (IOException | ClassNotFoundException | InterruptedException e) {
            e.printStackTrace();
        }
        return returnRestaurantTable;
    }
    
    private void setColours() throws Exception {

        TransferClass transferClass = TransferClass.create(null, ConstUI.RESTAURANT, ConstBL.RETURN_AVAILABLE_TABLES);
        try {
        	nonBusyTables = (List<RestaurantTable>) ControllerUI.getControllerUI().execute(transferClass).getResponse();
        } catch (IOException | InterruptedException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        for (JButton button : buttonList) {
            Image buttonPhoto = new ImageIcon(ConstImages.AVAILABLE_TABLE.imageAvailableTable()).getImage();
            button.setIcon((Icon) buttonPhoto);
        }
        if (nonBusyTables.size()>0){
            for (RestaurantTable table: nonBusyTables) {
                if (table.getId_restaurant() == restaurant.getId_restaurant()) {

                    for (int i = 0; i <buttonList.size() ; i++) {
                        if (table.getTable_number() == (i+1)){
                            Image buttonPhoto = new ImageIcon(ConstImages.BUSY_TABLE.imageBusyTable()).getImage();
                            buttonList.get(i).setIcon((Icon) buttonPhoto);
                        }
                    }
                }
            }
        }
    }
	}

