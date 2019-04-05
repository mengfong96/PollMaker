package gui;
import gui.LoginForm;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPasswordField;

import logic.System;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import java.awt.SystemColor;
import java.awt.Color;

public class Account_Registration extends JFrame implements ActionListener, ChangeListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LoginForm prevScreen;
	private JPanel contentPane;
	private JTextField txt_firstname;
	private JTextField txt_lastname;
	private JTextField txt_email;
	private JTextField txt_username;
	private JPasswordField txt_password;
	private JPasswordField txt_retypepassword;
	private JButton btn_submit, btn_cancel, btnClear;
	private JCheckBox cb_Terms;
	
	private System system;
	/**
	 * Create the frame.
	 */
	public Account_Registration(LoginForm prevScreen, System system) {
		
		ImageIcon img = new ImageIcon("img/logo1.png");
		setIconImage(img.getImage());
		setFont(new Font("Times New Roman", Font.PLAIN, 12));
		setTitle("Account Registration");
		this.prevScreen = prevScreen;
		this.system = system;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setBounds(100, 100, 544, 374);
		setLocationRelativeTo(null);
		setResizable(false);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl_signup = new JLabel("Sign Up New Account");
		lbl_signup.setForeground(SystemColor.menuText);
		lbl_signup.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_signup.setFont(new Font("Adobe Caslon Pro Bold", Font.ITALIC, 20));
		lbl_signup.setBounds(165, 13, 196, 22);
		contentPane.add(lbl_signup);
		
		JLabel lbl_firstname = new JLabel("First Name :");
		lbl_firstname.setForeground(SystemColor.controlText);
		lbl_firstname.setHorizontalAlignment(SwingConstants.TRAILING);
		lbl_firstname.setFont(new Font("Bell MT", Font.PLAIN, 18));
		lbl_firstname.setBounds(45, 91, 152, 25);
		contentPane.add(lbl_firstname);
		
		JLabel lbl_lastname = new JLabel("Last Name :");
		lbl_lastname.setForeground(SystemColor.controlText);
		lbl_lastname.setHorizontalAlignment(SwingConstants.TRAILING);
		lbl_lastname.setFont(new Font("Bell MT", Font.PLAIN, 18));
		lbl_lastname.setBounds(45, 121, 152, 25);
		contentPane.add(lbl_lastname);
		
		JLabel lbl_password = new JLabel("Password :");
		lbl_password.setForeground(SystemColor.controlText);
		lbl_password.setHorizontalAlignment(SwingConstants.TRAILING);
		lbl_password.setFont(new Font("Bell MT", Font.PLAIN, 18));
		lbl_password.setBounds(45, 151, 152, 25);
		contentPane.add(lbl_password);
		
		JLabel lbl_retypepassword = new JLabel("Retype Password :");
		lbl_retypepassword.setForeground(SystemColor.controlText);
		lbl_retypepassword.setHorizontalAlignment(SwingConstants.TRAILING);
		lbl_retypepassword.setFont(new Font("Bell MT", Font.PLAIN, 18));
		lbl_retypepassword.setBounds(45, 181, 152, 25);
		contentPane.add(lbl_retypepassword);
		
		JLabel lbl_email = new JLabel("Email :");
		lbl_email.setForeground(SystemColor.controlText);
		lbl_email.setHorizontalAlignment(SwingConstants.TRAILING);
		lbl_email.setFont(new Font("Bell MT", Font.PLAIN, 18));
		lbl_email.setBounds(45, 211, 152, 25);
		contentPane.add(lbl_email);
		
		txt_firstname = new JTextField();
		txt_firstname.setForeground(SystemColor.textText);
		txt_firstname.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txt_firstname.setBounds(224, 91, 249, 25);
		contentPane.add(txt_firstname);
		txt_firstname.setColumns(10);
		
		txt_lastname = new JTextField();
		txt_lastname.setForeground(SystemColor.textText);
		txt_lastname.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txt_lastname.setBounds(224, 121, 249, 25);
		contentPane.add(txt_lastname);
		txt_lastname.setColumns(10);
		
		txt_email = new JTextField();
		txt_email.setForeground(SystemColor.textText);
		txt_email.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txt_email.setBounds(224, 211, 249, 25);
		contentPane.add(txt_email);
		txt_email.setColumns(10);
		
		btn_submit = new JButton("Register");
		btn_submit.setForeground(SystemColor.activeCaptionText);
		btn_submit.setFont(new Font("Bell MT", Font.PLAIN, 18));
		btn_submit.setBounds(208, 280, 110, 33);
		contentPane.add(btn_submit);
		
		btn_cancel = new JButton("Cancel");
		btn_cancel.setForeground(SystemColor.controlDkShadow);
		btn_cancel.setFont(new Font("Bell MT", Font.PLAIN, 18));
		btn_cancel.setBounds(321, 280, 110, 33);
		contentPane.add(btn_cancel);
		
		JLabel lbl_username = new JLabel("Username :");
		lbl_username.setForeground(SystemColor.controlText);
		lbl_username.setHorizontalAlignment(SwingConstants.TRAILING);
		lbl_username.setFont(new Font("Bell MT", Font.PLAIN, 18));
		lbl_username.setBounds(45, 61, 152, 25);
		contentPane.add(lbl_username);
		
		txt_username = new JTextField();
		txt_username.setForeground(SystemColor.textText);
		txt_username.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txt_username.setBounds(224, 61, 249, 25);
		contentPane.add(txt_username);
		txt_username.setColumns(10);
		
		txt_password = new JPasswordField();
		txt_password.setForeground(SystemColor.textText);
		txt_password.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txt_password.setBounds(224, 151, 249, 25);
		contentPane.add(txt_password);
		
		txt_retypepassword = new JPasswordField();
		txt_retypepassword.setForeground(SystemColor.textText);
		txt_retypepassword.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txt_retypepassword.setBounds(224, 181, 249, 25);
		contentPane.add(txt_retypepassword);
		
		cb_Terms = new JCheckBox("I accept the Terms and Condition");
		cb_Terms.setForeground(SystemColor.textHighlight);
		cb_Terms.setFont(new Font("Bell MT", Font.PLAIN, 14));
		cb_Terms.setBounds(224, 246, 234, 25);
		contentPane.add(cb_Terms);
		cb_Terms.addChangeListener(this);
		
		btnClear = new JButton("Clear");
		btnClear.setForeground(new Color(0, 0, 255));
		btnClear.setFont(new Font("Bell MT", Font.PLAIN, 18));
		btnClear.setBounds(96, 280, 110, 33);
		contentPane.add(btnClear);
		
		btn_submit.addActionListener(this);
		btn_cancel.addActionListener(this);
		btnClear.addActionListener(this);
	}
	
	public void refresh(){
		txt_username.setText("");
		txt_email.setText("");
		txt_firstname.setText("");
		txt_lastname.setText("");
		txt_password.setText("");
		txt_retypepassword.setText("");
		cb_Terms.setSelected(false);
	}

	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource();
		
		if(obj == btn_submit ){
			//Check if every field got text
			if(txt_email.getText().isEmpty() || txt_username.getText().isEmpty() || txt_firstname.getText().isEmpty() || txt_lastname.getText().isEmpty() || txt_password.getText().isEmpty() || txt_retypepassword.getText().isEmpty() || !cb_Terms.isSelected() )
				JOptionPane.showMessageDialog(null, "Please input required field.","Error",JOptionPane.INFORMATION_MESSAGE);
			else{
				//Check for validity
				if((!txt_password.getText().equals(txt_retypepassword.getText())))
					JOptionPane.showMessageDialog(null, "Password does not match! Please try again!","Error",JOptionPane.INFORMATION_MESSAGE);
				else if(system.verifyMemberEmail(txt_email.getText()) || system.verifyMemberUsername(txt_username.getText()))
					JOptionPane.showMessageDialog(null, "Input Email or Username are in used.","Error",JOptionPane.INFORMATION_MESSAGE);
				else if(system.verifyPendingEmail(txt_email.getText()) || system.verifyPendingUsername(txt_username.getText()) )
					JOptionPane.showMessageDialog(null, "Invalid Email or Username.","Error",JOptionPane.INFORMATION_MESSAGE);
				//Add to pendingList
				else{
					String pendingUsername = txt_username.getText();
					String pendingEmail = txt_email.getText();
					String pendingFirstName = txt_firstname.getText();
					String pendingLastName = txt_lastname.getText();
					String password = txt_password.getText();
					system.AddToPendingList(pendingUsername, pendingEmail, pendingFirstName, pendingLastName, password);
					JOptionPane.showMessageDialog(null, "Waiting for account approval by Admin","Succesful Registration",JOptionPane.INFORMATION_MESSAGE);
					this.setVisible(false);
					prevScreen.setVisible(true);
					prevScreen.refresh();
				}	
			}
		}else if(obj == btn_cancel){
			this.setVisible(false);
			prevScreen.setVisible(true);
			prevScreen.refresh();
		}else if(obj == btnClear){
			refresh();
		}
	}
	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		
	}
}
