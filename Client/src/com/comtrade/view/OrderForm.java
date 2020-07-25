package com.comtrade.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.comtrade.constants.ConstBL;
import com.comtrade.constants.ConstUI;

import com.comtrade.controllerFront.ControllerUI;
import com.comtrade.domain.Bill;
import com.comtrade.domain.Ingredients;
import com.comtrade.domain.Orders;
import com.comtrade.domain.RestaurantTable;
import com.comtrade.domain.User;
import com.comtrade.transfer.TransferClass;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ComboBoxEditor;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.ConstructorProperties;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.awt.event.ActionEvent;

public class OrderForm extends JFrame {

	private JPanel contentPane;
	private JTextField tfQuantity;
	private JTable table;
	protected DefaultTableModel dtm = new DefaultTableModel();
	private List<Ingredients> ingredientList = null;
	private List<Orders> orders1;

	private Bill bill;
	protected JComboBox comboBox = new JComboBox();
	private JButton btnFinalize;
	WaiterForm waiterForm;
	//int z = waiterForm.getRestaurantTableGET().getTable_number();
	int bla = 0;
	private double price;
	LoginForm loginform;
	User user;
	
	


	
//	public OrderForm(Integer a) {
		//this.bla = a;
	//}

	 
	public OrderForm(Integer a,User user)  {
		this.user = user;
		this.bla = a;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 722, 595);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Existing Ingredients");
		lblNewLabel.setFont(new Font("Sitka Display", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel.setBounds(254, 21, 309, 39);
		contentPane.add(lblNewLabel);
		
		
		comboBox.setBounds(20, 72, 654, 22);
		contentPane.add(comboBox);
		
		tfQuantity = new JTextField();
		tfQuantity.setBounds(300, 128, 86, 20);
		contentPane.add(tfQuantity);
		tfQuantity.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(254, 219, 442, 326);
		contentPane.add(scrollPane);
		
		table = new JTable(dtm);
		scrollPane.setViewportView(table);
		try {
			showIngredients(ingredientList);
		} catch (ClassNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (InterruptedException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		JButton btnAdd = new JButton("Add items to order");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
		
				Double quantity = Double.parseDouble(tfQuantity.getText());
				Ingredients ingredients = (Ingredients)comboBox.getSelectedItem();
				double a = ingredients.getQuantity();
				if(quantity <= ingredients.getQuantity()) {
					double quantityMinus = ingredients.getQuantity();
					int tableNumber = bla;
					price = quantity * ingredients.getprice();
					double quantityGoesBackToBase = quantityMinus - quantity;
					ingredients.setQuantity(quantityGoesBackToBase);
					Ingredients ingredient = new Ingredients(ingredients.getId_ingredient(),ingredients.getprice(),1,ingredients.getQuantity(),
							ingredients.getQuantity_measure(),ingredients.getIngredient_name());
					try {
						TransferClass transferClass =ControllerUI.getControllerUI().execute(TransferClass.create(ingredient, ConstUI.INGREDIENTS, ConstBL.PUT));
						String waitersFirstName = user.getFirst_name();
						int idRestaurant = 1;
						SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						Date date = new Date(System.currentTimeMillis());
						String timez = formatter.format(date);
						Orders orders5 = new Orders(1,tableNumber,price,ingredient.getId_ingredient());
						orders1.add(orders5);
						String zec = "zec";
					//	Bill bill = new Bill(waitersFirstName,idRestaurant,price,tableNumber,timez);
					//	TransferClass transferClass1 = ControllerUI.getControllerUI().execute(TransferClass.create(bill, ConstUI.BILL, ConstBL.ADDBILL));
						
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
					
					
					fillTable();
				}else {
					JOptionPane.showMessageDialog(null, "It seems like quantity you've entered is higher than the one that's in our warehouse,please try less or another item thank you.");
				}

			}
		});
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int row = table.getSelectedRow();
				String name = (table.getModel().getValueAt(row, 0).toString());
				Double quantity = Double.parseDouble((table.getModel().getValueAt(row, 1).toString()));
		
				
						}
			}
		);
		
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnAdd.setBounds(32, 238, 185, 23);
		contentPane.add(btnAdd);
		
		JButton btnBill = new JButton("List of bills");
		btnBill.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListOfBillsFinalForm finalBill = null;
				try {
					finalBill = new ListOfBillsFinalForm(bill);
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
				finalBill.setVisible(true);
			}
		});
		btnBill.setBounds(20, 522, 224, 23);
		contentPane.add(btnBill);
		Object [] columns = {"Name", "Quantity"};
		dtm.addColumn(columns[0]);
		dtm.addColumn(columns[1]);
	
		
		
		btnFinalize = new JButton("Checkout - Payment - Close Bill");
		btnFinalize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double price = 0;
				for(int i = 0; i < orders1.size();i++) {
					price+=orders1.get(i).getPrice();
				}
				int tableNumber = bla;
				int idRestaurant = 1;
				SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date date = new Date(System.currentTimeMillis());
				String timez = formatter.format(date);
				String waitersFirstName = user.getFirst_name();
				Bill bill = new Bill(waitersFirstName,idRestaurant,price,tableNumber,timez);
				try {
					TransferClass transferClass1 = ControllerUI.getControllerUI().execute(TransferClass.create(bill, ConstUI.BILL, ConstBL.ADDBILL));
					JOptionPane.showMessageDialog(null, "You've succesfully closed your bill.");
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
		btnFinalize.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnFinalize.setBounds(20, 488, 224, 23);
		contentPane.add(btnFinalize);
		
		orders1 = new ArrayList<>();
		OrderForm.this.bill = bill;
		//fillTable();
	}
		
		




















	private void showIngredients(List<Ingredients> ingredientList) throws ClassNotFoundException, IOException, InterruptedException {
		TransferClass transferClass = TransferClass.create(1, ConstUI.INGREDIENTS, ConstBL.RETURN_INGREDIENTS_WITH_QUANTITY_BIGGER_THAN_0);
		ingredientList = (List<Ingredients>) ControllerUI.getControllerUI().execute(transferClass).getResponse();
		//List<Ingredients> s = ingredientList.stream().map(x -> x.getIngredient_name()).collect(Collectors.toList());
		
		comboBox.removeAllItems();
		
		for (Ingredients ingredients1 : ingredientList) {
			comboBox.addItem(ingredients1);

		}
		

	}
	protected void fillTable() {
		Object[] row = new Object[3];
	//	for (Orders order : orders) {
		//	row[0] = orders.stream().filter(a -> a.getIngredient().getId_ingredient() == order.getIngredient().getId_ingredient()).findFirst().get()
		//			.getIngredient().getIngredient_name();
		String quantity = tfQuantity.getText();
			row[1] = quantity;
		Ingredients a = (Ingredients) comboBox.getSelectedItem();
		row[0] = a;
		dtm.addRow(row);
		

	
		}
	private void quantityCheck() {
		
	}
}
