package gui;
import logic.Member;
import logic.System;
import logic.Admin;
import gui.MainMenuForm;
import gui.Account_Registration; 


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import javax.swing.JPasswordField;
import java.awt.SystemColor;

@SuppressWarnings("serial")
public class LoginForm extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField txt_LoginUsername;
	private JButton btn_Register;
	private JButton btn_Login;
	private JPasswordField passwordField;
	private System system; 
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try 
				{
					System system = new System("Kolej Pendeta Zaba");
					new LoginForm(system);	
				} 
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the frame.
	 */
	public LoginForm(System system) {
		this.system = system;
		
		ImageIcon img = new ImageIcon("img/logo1.png");
		setIconImage(img.getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Login");
		setVisible(true);
		setBounds(100, 100, 545, 415);
		setLocationRelativeTo(null);
		setResizable(false);
		
		contentPane = new JPanel();
		contentPane.setLayout(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lbl_Title = new JLabel("Poll Maker");
		lbl_Title.setFont(new Font("Trajan Pro", Font.PLAIN, 22));
		lbl_Title.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Title.setBounds(180, 20, 165, 35);
		contentPane.add(lbl_Title);
		
		JLabel lbl_Register = new JLabel("Not a member yet? ");
		lbl_Register.setForeground(SystemColor.textHighlight);
		lbl_Register.setFont(new Font("Bell MT", Font.PLAIN, 16));
		lbl_Register.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Register.setBounds(193, 300, 141, 16);
		contentPane.add(lbl_Register);
		
		btn_Register = new JButton("Sign Up Now");
		btn_Register.setForeground(SystemColor.textHighlight);
		btn_Register.setFont(new Font("Bell MT", Font.PLAIN, 18));
		btn_Register.setBounds(193, 317, 141, 35);
		contentPane.add(btn_Register);
		btn_Register.addActionListener(this);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 120, 527, 167);
		contentPane.add(panel);
		panel.setLayout(null);
		
		btn_Login = new JButton("Login");
		btn_Login.setBounds(193, 134, 141, 33);
		panel.add(btn_Login);
		btn_Login.setFont(new Font("Bell MT", Font.PLAIN, 18));
		btn_Login.addActionListener(this);
		
		txt_LoginUsername = new JTextField();
		txt_LoginUsername.setBounds(168, 14, 248, 35);
		panel.add(txt_LoginUsername);
		txt_LoginUsername.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txt_LoginUsername.setHorizontalAlignment(SwingConstants.CENTER);
		txt_LoginUsername.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		passwordField.setBounds(168, 68, 248, 33);
		passwordField.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(passwordField);
		
		JLabel lbl_Username = new JLabel("Username:");
		lbl_Username.setHorizontalAlignment(SwingConstants.TRAILING);
		lbl_Username.setFont(new Font("Bell MT", Font.PLAIN, 20));
		lbl_Username.setBounds(57, 14, 99, 34);
		panel.add(lbl_Username);
		
		JLabel lbl_Password = new JLabel("Password:");
		lbl_Password.setHorizontalAlignment(SwingConstants.TRAILING);
		lbl_Password.setFont(new Font("Bell MT", Font.PLAIN, 20));
		lbl_Password.setBounds(57, 66, 99, 33);
		panel.add(lbl_Password);
		
		JLabel label = new JLabel(new ImageIcon("img/logo1.png"));
		label.setBackground(SystemColor.desktop);
		label.setForeground(SystemColor.desktop);
		label.setHorizontalAlignment(SwingConstants.TRAILING);
		label.setFont(new Font("Bell MT", Font.PLAIN, 20));
		label.setBounds(80, 13, 127, 94);
		contentPane.add(label);
		
		JLabel lblCreatePollVote = new JLabel("Create poll, vote and get connected");
		lblCreatePollVote.setHorizontalAlignment(SwingConstants.CENTER);
		lblCreatePollVote.setFont(new Font("Bell MT", Font.PLAIN, 16));
		lblCreatePollVote.setBounds(198, 58, 269, 25);
		contentPane.add(lblCreatePollVote);
		
		JLabel lblEasyAs = new JLabel("Easy as 1,2,3");
		lblEasyAs.setHorizontalAlignment(SwingConstants.CENTER);
		lblEasyAs.setFont(new Font("Bell MT", Font.PLAIN, 16));
		lblEasyAs.setBounds(176, 82, 103, 25);
		contentPane.add(lblEasyAs);
		
		passwordField.getPassword();
	}
	
	public void refresh(){
		txt_LoginUsername.setText("");
		passwordField.setText("");
	}
	
	public void actionPerformed(ActionEvent e){
			Object obj = e.getSource();
			
			if(obj == btn_Register){
				this.setVisible(false);
				refresh();
				new Account_Registration(this, system);
			}
			else{
				String username = txt_LoginUsername.getText();
				String password = passwordField.getText();
				
				if (system.isValidMember(username, password)){
					Member member = system.getMember(username, password);
					this.dispose();
					new MainMenuForm(system, member, this);
					
				}else if (system.isValidAdmin(username,password)){
					Admin admin = system.getAdmin(username, password);
					this.dispose();
					new AdminMainmenu(system, admin);
					
				}else if(system.isPendingforVerification(username, password))
					JOptionPane.showMessageDialog(this, "Your account is still pending for admin verification."  ,"Error", JOptionPane.ERROR_MESSAGE);
				
				else
					JOptionPane.showMessageDialog(this, "Invalid ID or password"  ,"Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}



