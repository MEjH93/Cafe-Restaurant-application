package com.comtrade.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import com.comtrade.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.comtrade.ClientCommunication.ClientCommunication;
import com.comtrade.constants.ConstBL;
import com.comtrade.constants.ConstUI;
import com.comtrade.controllerFront.ControllerUI;
import com.comtrade.domain.User;
import com.comtrade.proxy.Proxy;
import com.comtrade.proxy.ProxyLogin;
import com.comtrade.transfer.TransferClass;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class LoginForm extends JFrame {

	private JPanel contentPane;
	private JTextField tfUser;
	private JPasswordField pfPass;
	String usersName;

	
	String getUsersName() {
		return usersName;
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginForm frame = new LoginForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public LoginForm() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 621, 537);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tfUser = new JTextField();
		tfUser.setColumns(10);
		tfUser.setBounds(178, 165, 263, 20);
		contentPane.add(tfUser);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(10, 168, 158, 14);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(10, 201, 158, 14);
		contentPane.add(lblPassword);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = tfUser.getText();
                String password = String.copyValueOf(pfPass.getPassword());
                User user = new User();
                user.setUsername(username);
                user.setPassword(password);
                TransferClass transferClass;
                usersName = username;
                try {
                	transferClass = TransferClass.create(user, ConstUI.USER, ConstBL.GET_LOGIN);
                    Proxy proxy  =new ProxyLogin();
                    user = (User) ControllerUI.getControllerUI().execute(transferClass).getResponse();
                    if (user != null){
                        ClientCommunication.getInstance().setUserID(user.getId_user());
                        proxy.login(user);
                        //dispose();

                    }else {
                        JOptionPane.showMessageDialog(null, "Ain't gonna work bro");
                    }

                } catch (IOException a) {
                    a.printStackTrace();
                } catch (ClassNotFoundException a) {
                    a.printStackTrace();
                } catch (InterruptedException a) {
                    a.printStackTrace();
                } catch (InvocationTargetException a) {
                    a.printStackTrace();
                }
				
			}
		});
		btnLogin.setBounds(227, 342, 121, 23);
		contentPane.add(btnLogin);
		
		pfPass = new JPasswordField();
		pfPass.setBounds(178, 198, 263, 20);
		contentPane.add(pfPass);
		
		JButton btnRegistration = new JButton("Registration");
		btnRegistration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistrationForm registrationForm = new RegistrationForm();
				registrationForm.setVisible(true);
			}
		});
		btnRegistration.setBounds(227, 457, 121, 23);
		contentPane.add(btnRegistration);
		setResizable(false);
		JLabel label1 = new JLabel(new ImageIcon("C:\\Users\\MEjH\\Desktop\\cafe\\kidalica.png"));
		label1.setSize(565, 502);
		contentPane.add(label1);

		
		
		
	}
}