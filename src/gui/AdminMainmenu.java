package gui;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import logic.Admin;
import logic.System;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class AdminMainmenu extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JButton btnViewPendingUser, btnManageAccount, btnLogout;
	
	private System system;
	private Admin admin;

	public AdminMainmenu(System system, Admin admin) {
		this.system = system;
		this.admin = admin;
		
		ImageIcon img = new ImageIcon("img/logo1.png");
		setIconImage(img.getImage());
		setTitle("Admin MainMenu");
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 525, 338);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel p1 = new JPanel();
		contentPane.add(p1, BorderLayout.CENTER);
		p1.setLayout(null);
		
		btnViewPendingUser = new JButton("View Pending User");
		btnViewPendingUser.setFont(new Font("Bell MT", Font.PLAIN, 18));
		btnViewPendingUser.setBounds(149, 137, 208, 39);
		p1.add(btnViewPendingUser);
		
		btnManageAccount = new JButton("Manage Account");
		btnManageAccount.setFont(new Font("Bell MT", Font.PLAIN, 18));
		btnManageAccount.setBounds(149, 187, 208, 39);
		p1.add(btnManageAccount);
		
		btnLogout = new JButton("Logout");
		btnLogout.setForeground(Color.RED);
		btnLogout.setFont(new Font("Bell MT", Font.PLAIN, 14));
		btnLogout.setBounds(404, 248, 93, 32);
		p1.add(btnLogout);
		
		String name = admin.getAdminUsername();
		JLabel label = new JLabel("Welcome back, "+name);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Trajan Pro", Font.PLAIN, 16));
		label.setBounds(12, 93, 485, 32);
		p1.add(label);
		
		JLabel label_1 = new JLabel(new ImageIcon("img/logo1.png"));
		label_1.setHorizontalAlignment(SwingConstants.TRAILING);
		label_1.setForeground(Color.BLACK);
		label_1.setFont(new Font("Bell MT", Font.PLAIN, 20));
		label_1.setBackground(Color.BLACK);
		label_1.setBounds(191, 0, 127, 94);
		p1.add(label_1);
		
		btnViewPendingUser.addActionListener(this);
		btnManageAccount.addActionListener(this);
		btnLogout.addActionListener(this);
		
	}

	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == btnViewPendingUser){
			this.setVisible(false);
			new AdminApprove(this, system, admin);
		}
		else if(obj == btnManageAccount){
			this.setVisible(false);
			new AdminManageAccount(system, admin, this);
		}
		else {
			int dialogButton = JOptionPane.YES_NO_OPTION;
			int dialogResult = JOptionPane.showConfirmDialog(this, "Are you sure you want to logout?", "Logout Confirmation", dialogButton);
			if(dialogResult == 0) {
				this.dispose();
				new LoginForm(system);
			}
		}
		
	}
}
