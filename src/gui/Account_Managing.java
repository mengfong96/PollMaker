package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logic.Member;
import logic.System;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.SystemColor;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class Account_Managing extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField txt_newemail;
	private JPasswordField txt_reenterpassword;
	private JPasswordField txt_newpassword;
	private JPasswordField txt_reenterpassword2;
	private JButton btn_cancel, btn_submit;
	private JTabbedPane tab_managing;
	
	private System system;
	private Member member;
	private MainMenuForm prevScreen;
	

	/**
	 * Create the frame.
	 */
	public Account_Managing(System system,  Member member, MainMenuForm prevScreen ) {
		setTitle("Manage My Account");
		this.system = system;
		this.member = member;
		this.prevScreen = prevScreen;
		
		ImageIcon img = new ImageIcon("img/logo1.png");
		setIconImage(img.getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 401);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl_manageaccount = new JLabel("Manage My Account");
		lbl_manageaccount.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_manageaccount.setFont(new Font("Bell MT", Font.BOLD, 24));
		lbl_manageaccount.setBounds(184, 11, 225, 37);
		contentPane.add(lbl_manageaccount);
		
		tab_managing = new JTabbedPane(JTabbedPane.TOP);
		tab_managing.setBounds(12, 61, 560, 237);
		contentPane.add(tab_managing);
		
		JPanel panel_changepassword = new JPanel();
		tab_managing.addTab("Change Password", null, panel_changepassword, null);
		tab_managing.setForegroundAt(0, SystemColor.controlDkShadow);
		panel_changepassword.setLayout(null);
		
		JLabel lbl_newpassword = new JLabel("Please Enter Your New Password :");
		lbl_newpassword.setFont(new Font("Bell MT", Font.PLAIN, 20));
		lbl_newpassword.setBounds(12, 13, 324, 23);
		panel_changepassword.add(lbl_newpassword);
		
		JLabel lbl_reenter = new JLabel("Please Re-enter Your New Password :");
		lbl_reenter.setFont(new Font("Bell MT", Font.PLAIN, 20));
		lbl_reenter.setBounds(12, 113, 324, 23);
		panel_changepassword.add(lbl_reenter);
		
		txt_reenterpassword = new JPasswordField();
		txt_reenterpassword.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txt_reenterpassword.setBounds(106, 150, 344, 30);
		panel_changepassword.add(txt_reenterpassword);
		
		txt_newpassword = new JPasswordField();
		txt_newpassword.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txt_newpassword.setBounds(106, 52, 344, 30);
		panel_changepassword.add(txt_newpassword);
		
		JPanel panel_changeemail = new JPanel();
		panel_changeemail.setForeground(SystemColor.activeCaptionText);
		tab_managing.addTab("Change Email", null, panel_changeemail, null);
		tab_managing.setForegroundAt(1, SystemColor.controlDkShadow);
		panel_changeemail.setLayout(null);
		
		JLabel lbl_newemail = new JLabel("Please Enter Your New Email :");
		lbl_newemail.setFont(new Font("Bell MT", Font.PLAIN, 20));
		lbl_newemail.setBounds(12, 13, 324, 23);
		panel_changeemail.add(lbl_newemail);
		
		txt_newemail = new JTextField();
		txt_newemail.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txt_newemail.setBounds(106, 52, 344, 30);
		panel_changeemail.add(txt_newemail);
		txt_newemail.setColumns(10);
		
		JLabel lbl_reenter2 = new JLabel("Please Enter Your Password :");
		lbl_reenter2.setFont(new Font("Bell MT", Font.PLAIN, 20));
		lbl_reenter2.setBounds(12, 113, 324, 23);
		panel_changeemail.add(lbl_reenter2);
		
		txt_reenterpassword2 = new JPasswordField();
		txt_reenterpassword2.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txt_reenterpassword2.setBounds(106, 150, 344, 30);
		panel_changeemail.add(txt_reenterpassword2);
		
		btn_submit = new JButton("Submit");
		btn_submit.setForeground(SystemColor.textHighlight);
		btn_submit.setFont(new Font("Bell MT", Font.PLAIN, 18));
		btn_submit.setBounds(187, 311, 100, 30);
		contentPane.add(btn_submit);
		
		btn_cancel = new JButton("Cancel");
		btn_cancel.setForeground(SystemColor.controlDkShadow);
		btn_cancel.setFont(new Font("Bell MT", Font.PLAIN, 18));
		btn_cancel.setBounds(309, 311, 100, 30);
		contentPane.add(btn_cancel);
		
		btn_submit.addActionListener(this);
		btn_cancel.addActionListener(this);
	}
	
	public void refresh(){
		txt_newpassword.setText("");
		txt_reenterpassword.setText("");
		txt_newemail.setText("");
		txt_reenterpassword2.setText("");
	}

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj == btn_submit){
			if(tab_managing.getSelectedIndex() == 0){
				
				if(txt_newpassword.getText().equals("") || txt_reenterpassword.getText().equals(""))
					JOptionPane.showMessageDialog(null, "Please fill in required field.","Error",JOptionPane.INFORMATION_MESSAGE);
				
				else if(!txt_newpassword.getText().equals(txt_reenterpassword.getText()))
					JOptionPane.showMessageDialog(null, "Password does not match! Please try again!","Error",JOptionPane.INFORMATION_MESSAGE);
		
				else if (txt_newpassword.getText().equals(member.getPassword()))
					JOptionPane.showMessageDialog(null, "New Password are same with Old Password! Please re-enter new Password!","Error",JOptionPane.INFORMATION_MESSAGE);
			
				else {
					String new_password = String.valueOf(txt_newpassword.getPassword());
					if(system.ChangeMemberPassword(member,new_password)){
					JOptionPane.showMessageDialog(null, "Your password was changed successfully.","Succesful",JOptionPane.INFORMATION_MESSAGE);
					this.refresh();
					}
				}
			}
			if(tab_managing.getSelectedIndex() == 1){
				
				if(txt_newemail.getText().equals("") || txt_reenterpassword2.getText().equals(""))
					JOptionPane.showMessageDialog(null, "Please fill in required field.","Error",JOptionPane.INFORMATION_MESSAGE);
			
				else if(!txt_reenterpassword2.getText().equals(member.getPassword()))
					JOptionPane.showMessageDialog(null, "Password does not match! Please try again!","Error",JOptionPane.INFORMATION_MESSAGE);
				
				else if (txt_newemail.getText().equals(member.getMemberEmail()))
					JOptionPane.showMessageDialog(null, "New Email are same with Old Email! Please re-enter new Email!","Error",JOptionPane.INFORMATION_MESSAGE);
			
				else if(system.verifyMemberEmail(txt_newemail.getText()))
					JOptionPane.showMessageDialog(null, "Please re-enter a valid email.","Invalid Email",JOptionPane.INFORMATION_MESSAGE);
				
				else {
					String new_email = txt_newemail.getText();
					if(system.ChangeMemberEmail(member,new_email)){
						JOptionPane.showMessageDialog(null, "Your email was changed successfully.","Succesful",JOptionPane.INFORMATION_MESSAGE);
						this.refresh();
					}
				}
			}
		}
		else if(obj == btn_cancel){
			//previous screen
			this.setVisible(false);
			this.refresh();
			prevScreen.setVisible(true);
		}
	}
}
	
