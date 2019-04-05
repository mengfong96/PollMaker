package gui;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import logic.Admin;
import logic.System;
import java.awt.Font;
import java.awt.SystemColor;

@SuppressWarnings("serial")
public class AdminManageAccount extends JFrame implements ActionListener{

	private AdminMainmenu prevScreen;
	private System system;
	private Admin admin;
	
	private JPanel contentPane;
	private JButton btnSubmit;
	private JButton btnback;
	private JPasswordField pwd_1;
	private JPasswordField pwd_2;
	private JLabel lblChangePassword;

	public AdminManageAccount(System system, Admin admin, AdminMainmenu prevScreen) {	
		this.prevScreen = prevScreen;
		this.system = system;
		this.admin = admin;
		
		ImageIcon img = new ImageIcon("img/logo1.png");
		setIconImage(img.getImage());
		setTitle("Admin Manage Account");
		setResizable(false);		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 390, 290);
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel p1 = new JPanel();
		contentPane.add(p1, BorderLayout.CENTER);
		p1.setLayout(null);
		
		JLabel lbl_password = new JLabel("Enter your new password:");
		lbl_password.setFont(new Font("Bell MT", Font.PLAIN, 18));
		lbl_password.setBounds(37, 56, 218, 27);
		p1.add(lbl_password);
		
		JLabel lbl_password2 = new JLabel("Re-enter your password: ");
		lbl_password2.setFont(new Font("Bell MT", Font.PLAIN, 18));
		lbl_password2.setBounds(37, 118, 218, 27);
		p1.add(lbl_password2);
		
		btnSubmit = new JButton("Submit");
		btnSubmit.setForeground(SystemColor.textHighlight);
		btnSubmit.setFont(new Font("Bell MT", Font.PLAIN, 16));
		btnSubmit.setBounds(72, 200, 100, 30);
		p1.add(btnSubmit);
		
		btnback = new JButton("Back");
		btnback.setForeground(SystemColor.activeCaptionText);
		btnback.setFont(new Font("Bell MT", Font.PLAIN, 16));
		btnback.setBounds(197, 200, 100, 30);
		p1.add(btnback);
		
		pwd_1 = new JPasswordField();
		pwd_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		pwd_1.setBounds(37, 89, 300, 25);
		p1.add(pwd_1);
		
		pwd_2 = new JPasswordField();
		pwd_2.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		pwd_2.setBounds(37, 148, 300, 25);
		p1.add(pwd_2);
		
		lblChangePassword = new JLabel("Change Password");
		lblChangePassword.setFont(new Font("Trajan Pro", Font.PLAIN, 18));
		lblChangePassword.setBounds(90, 13, 207, 30);
		p1.add(lblChangePassword);
		
		btnSubmit.addActionListener(this);
		btnback.addActionListener(this);
		
	}

	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == btnSubmit){
			if(Arrays.equals(pwd_1.getPassword(), pwd_2.getPassword()) && pwd_1.getPassword().length!=0 && pwd_2.getPassword().length!=0){
				// change password function
				String new_pwd = String.valueOf(pwd_1.getPassword());
				if(system.ChangeAdminPassword(admin, new_pwd)){
					JOptionPane.showMessageDialog(null, "Password changed succesfully!", "Message" ,JOptionPane.INFORMATION_MESSAGE);
					this.dispose();
					prevScreen.setVisible(true);
				}
				else
					JOptionPane.showMessageDialog(null, "Password change failed!", "Error" ,JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				JOptionPane.showMessageDialog(null, "The password does not match. Please re-enter your password.", "Error Message" ,JOptionPane.ERROR_MESSAGE);
				pwd_1.setText("");
				pwd_2.setText("");
			}
		}
		else if(obj == btnback){
			this.dispose();
			prevScreen.setVisible(true);
		}
		else {
			setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		}
		
	}
}
