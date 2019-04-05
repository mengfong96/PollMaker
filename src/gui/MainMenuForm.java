package gui;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logic.Member;
import logic.System;
import gui.DeletePollForm;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
 
import java.awt.Font;
import java.awt.Image;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Color;

@SuppressWarnings("serial")
public class MainMenuForm extends JFrame implements ActionListener {
	MainMenuForm frame;
	private LoginForm prevScreen;
	private JPanel contentPane;
	private JButton btnGiveVote, btnCreatePoll,  btnDeletePoll, btnViewPollResult, btnLogout, btn_manage;
	private JLabel lblWelcome,label,label_1,lblName;
	
	private System system;
	private Member member;

	public MainMenuForm(System system, Member member,LoginForm prevScreen) {
		this.system = system;
		this.prevScreen = prevScreen;
		this.member = member;
		
		ImageIcon img = new ImageIcon("img/logo1.png");
		setIconImage(img.getImage());
		setTitle("Member Main Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 574, 668);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnCreatePoll = new JButton("Create My Own Poll");
		btnCreatePoll.setFont(new Font("Bell MT", Font.PLAIN, 18));
		btnCreatePoll.setBounds(177, 119, 200, 40);
		contentPane.add(btnCreatePoll);
		
		
		btnDeletePoll = new JButton("Delete My Poll");
		btnDeletePoll.setFont(new Font("Bell MT", Font.PLAIN, 18));
		btnDeletePoll.setBounds(177, 165, 200, 40);
		contentPane.add(btnDeletePoll);
		
	
		btnGiveVote = new JButton("VOTE");
		btnGiveVote.setFont(new Font("Bell MT", Font.PLAIN, 18));
		btnGiveVote.setBounds(227, 348, 102, 34);
		contentPane.add(btnGiveVote);
	
		
		btnViewPollResult = new JButton("Poll Result");
		btnViewPollResult.setFont(new Font("Bell MT", Font.PLAIN, 18));
		btnViewPollResult.setBounds(177, 441, 200, 40);
		contentPane.add(btnViewPollResult);
		
		btnLogout = new JButton("Logout");
		btnLogout.setForeground(Color.RED);
		btnLogout.setFont(new Font("Bell MT", Font.PLAIN, 18));
		btnLogout.setBounds(177, 557, 200, 40);
		contentPane.add(btnLogout);
		
		
		lblWelcome = new JLabel("Welcome back, ");
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setFont(new Font("Trajan Pro", Font.PLAIN, 22));
		lblWelcome.setBounds(177, 13, 217, 32);
		contentPane.add(lblWelcome);
		
		String name = member.getMemberFirstName() +" "+ member.getMemberLastName();
		lblName = new JLabel(name);
		lblName.setForeground(new Color(30, 144, 255));
		lblName.setFont(new Font("Trajan Pro", Font.PLAIN, 22));
		lblName.setBounds(198, 56, 346, 25);
		contentPane.add(lblName);
		
		btn_manage = new JButton("Manage My Account");
		btn_manage.setForeground(Color.BLUE);
		btn_manage.setFont(new Font("Bell MT", Font.PLAIN, 18));
		btn_manage.setBounds(177, 510, 200, 40);
		contentPane.add(btn_manage);
		
		label = new JLabel(new ImageIcon("img/logo1.png"));
		label.setHorizontalAlignment(SwingConstants.TRAILING);
		label.setForeground(Color.BLACK);
		label.setFont(new Font("Bell MT", Font.PLAIN, 20));
		label.setBackground(Color.BLACK);
		label.setBounds(51, 12, 127, 94);
		contentPane.add(label);
		
		ImageIcon imageIcon = new ImageIcon(new ImageIcon("img/vote.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
		label_1 = new JLabel(imageIcon);
		label_1.setHorizontalAlignment(SwingConstants.TRAILING);
		label_1.setForeground(Color.BLACK);
		label_1.setFont(new Font("Bell MT", Font.PLAIN, 20));
		label_1.setBackground(Color.BLACK);
		label_1.setBounds(177, 228, 200, 200);
		contentPane.add(label_1);
		
		btnCreatePoll.addActionListener(this);
		btnDeletePoll.addActionListener(this);
		btnLogout.addActionListener(this);
		btnGiveVote.addActionListener(this);
		btn_manage.addActionListener(this);
		btnViewPollResult.addActionListener(this);
	}

	
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj==btnGiveVote){
			this.setVisible(false);
			new VoteForm(system, member, this);
		}
		else if(obj==btnLogout){
			int dialogresult = JOptionPane.showConfirmDialog(null, "Are you sure you want to Logout?", "Confirmation", JOptionPane.YES_NO_OPTION);
			if(dialogresult==0){
				this.setVisible(false);
				prevScreen.setVisible(true);
				prevScreen.refresh();
			}
		}
		else if(obj==btn_manage){
			this.setVisible(false);
			new Account_Managing(system, member, this);
		}
		else if(obj == btnCreatePoll){
			this.setVisible(false);
			new createPollForm(system, member, this);
		}else if(obj == btnViewPollResult){
			this.setVisible(false);
			new ViewAllPollResult(this,system,member);
		}else if(obj == btnDeletePoll) { 
			this.setVisible(false);
			new DeletePollForm(system, member, this);
		}
	}
}

