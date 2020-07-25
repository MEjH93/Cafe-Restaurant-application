package com.comtrade.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.IOException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import com.comtrade.constants.ConstBL;
import com.comtrade.constants.ConstUI;
import com.comtrade.controllerFront.ControllerUI;
import com.comtrade.domain.ExistingIngredients;
import com.comtrade.domain.Ingredients;
import com.comtrade.domain.User;
import com.comtrade.transfer.TransferClass;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AddIngredients extends JFrame {

	private JPanel contentPane;
	private JTextField tfName;
	private JTextField tfPrice;
	private JTextField tfMeasurement;
	private JTable table;
	private JTextField tfQuantity;
	private List <Ingredients> ingredientList = null;
	private ExistingIngredients existingIngredients = new ExistingIngredients();
	private List <Ingredients> ingredientList1 = null;
	private JTextField tfMejh;
	
	
	private DefaultTableModel dtm = new DefaultTableModel();
	private JButton btnUpdate;
	private JTextField tfMejh2;
	
	public AddIngredients(Ingredients ingredients) throws ClassNotFoundException, IOException, InterruptedException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 714, 593);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setBounds(10, 11, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setBounds(10, 36, 46, 14);
		contentPane.add(lblPrice);
		
		JLabel lblMeasurement = new JLabel("Measurement");
		lblMeasurement.setBounds(10, 59, 134, 14);
		contentPane.add(lblMeasurement);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = tfName.getText();
				String measurement = tfMeasurement.getText();
				double quantity = Double.parseDouble(tfQuantity.getText());
				double price = Double.parseDouble(tfPrice.getText());
				Ingredients ingredient = new Ingredients(name,measurement,quantity,price);
				//ingredientList1.add(ingredient);
				try {
					TransferClass transferClass = ControllerUI.getControllerUI().execute(
							TransferClass.create(ingredient, ConstUI.INGREDIENTS,ConstBL.POST));
					JOptionPane.showMessageDialog(null, "INGREDIENT ADDED SUCCESSFULLY");
				} catch (ClassNotFoundException e1) {
					JOptionPane.showMessageDialog(null, "Ingredient wasn't added.");
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}finally {
					try {
						showIngredients(ingredients);
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
			}
		});
		btnAdd.setBounds(39, 112, 89, 23);
		contentPane.add(btnAdd);
		
		tfName = new JTextField();
		tfName.setBounds(88, 8, 86, 20);
		contentPane.add(tfName);
		tfName.setColumns(10);
		
		tfPrice = new JTextField();
		tfPrice.setColumns(10);
		tfPrice.setBounds(88, 33, 86, 20);
		contentPane.add(tfPrice);
		
		tfMeasurement = new JTextField();
		tfMeasurement.setColumns(10);
		tfMeasurement.setBounds(88, 56, 86, 20);
		contentPane.add(tfMeasurement);
		
		JScrollPane scrollPane = new JScrollPane();

		scrollPane.setBounds(42, 167, 621, 376);
		contentPane.add(scrollPane);
		
		table = new JTable(dtm);
		scrollPane.setViewportView(table);
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int row = table.getSelectedRow();
				tfName.setText(table.getModel().getValueAt(row,1).toString());
				tfPrice.setText(table.getModel().getValueAt(row, 2).toString());
				tfMeasurement.setText(table.getModel().getValueAt(row,3 ).toString());
				tfQuantity.setText(table.getModel().getValueAt(row, 4).toString());
				tfMejh2.setText(table.getModel().getValueAt(row, 0).toString());
						}
			}
		);
		
		tfQuantity = new JTextField();
		tfQuantity.setBounds(88, 81, 86, 20);
		contentPane.add(tfQuantity);
		tfQuantity.setColumns(10);
		
		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setBounds(10, 87, 68, 14);
		contentPane.add(lblQuantity);
		
		btnUpdate = new JButton("Update Ingredient");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = tfName.getText();
				Double price = Double.parseDouble(tfPrice.getText());
				String measurement = tfMeasurement.getText();
				Double quantity = Double.parseDouble(tfQuantity.getText());
				int idIngredient = Integer.parseInt(tfMejh2.getText());
				Ingredients ingredient = new Ingredients(name,price,measurement,quantity,idIngredient);
				try {
					TransferClass transferClass = ControllerUI.getControllerUI().execute(
							TransferClass.create(ingredient, ConstUI.INGREDIENTS,ConstBL.PUT));
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
		btnUpdate.setBounds(167, 112, 187, 23);
		contentPane.add(btnUpdate);
		
		tfMejh2 = new JTextField();
		tfMejh2.setBounds(589, 113, 86, 20);
		contentPane.add(tfMejh2);
		tfMejh2.setColumns(10);
		
		Object [] columns = {"ID Ingredient","Ingredient Name","Price","Measurement","Quantity"};
		dtm.addColumn(columns[0]);
		dtm.addColumn(columns[1]);
		dtm.addColumn(columns[2]);
		dtm.addColumn(columns[3]);
		dtm.addColumn(columns[4]);
		showIngredients(ingredients);
	}
	private void showIngredients(Ingredients ingredients) throws ClassNotFoundException, IOException, InterruptedException {
		TransferClass transferClass = TransferClass.create(1, ConstUI.INGREDIENTS, ConstBL.RETURN_INGREDIENTS);
		ingredientList = (List<Ingredients>) ControllerUI.getControllerUI().execute(transferClass).getResponse();
		dtm.setRowCount(0);
		for (Ingredients ingredients1 : ingredientList) {
			Object[] columns = { ingredients1.getId_ingredient(), ingredients1.getIngredient_name(), ingredients1.getprice(), ingredients1.getQuantity_measure(),
					ingredients1.getQuantity() };
			dtm.addRow(columns);
		}

	}
}
