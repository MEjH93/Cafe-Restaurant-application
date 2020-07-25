package com.comtrade.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.comtrade.domain.User;
import javax.swing.JLabel;

public class ManagerForm extends JFrame {

	private JPanel contentPane;


	public ManagerForm(User user) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel lblNewLabel_1 = new JLabel("MANAGERFORMA");
		lblNewLabel_1.setBounds(85, 160, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel label = new JLabel(new ImageIcon("C:\\Users\\MEjH\\Desktop\\Kafic\\kidalica.png"));
		label.setSize(565, 502);
		contentPane.add(label);
		setResizable(false);

	}

}
