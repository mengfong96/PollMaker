package gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import logic.pendingMember;
import logic.Admin;
import logic.System;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.SystemColor;

@SuppressWarnings("serial")
public class AdminApprove extends JFrame implements ActionListener{
	
	//private AdminMainmenu MainMenu;
	//private LoginForm prevScreen;
	private AdminMainmenu prevScreen;
	private System system;
	private Admin admin;
	
	private JPanel contentPane,p1,p2,p4;
	private JButton[] btnList, btnAcceptList, btnRejectList;
	private JButton btnBack = new JButton("Back");
	private JScrollPane scroll;
	
	private ArrayList<pendingMember> pendingList;

	public AdminApprove(AdminMainmenu prevScreen, System system, Admin admin) {
		this.prevScreen = prevScreen;
		this.system = system;
		this.admin = admin;
		
		ImageIcon img = new ImageIcon("img/logo1.png");
		setIconImage(img.getImage());
		setTitle("Admin Approve Account");
		setResizable(false);		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		setExtendedState(this.getExtendedState() | JFrame.NORMAL);
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		pendingList = system.getAllpendingMember();
		btnList = new JButton[pendingList.size()];
		btnAcceptList = new JButton[pendingList.size()];
		btnRejectList = new JButton[pendingList.size()];
		
		p1 = new JPanel();
		contentPane.add(p1, BorderLayout.NORTH);
		p1.setLayout(new FlowLayout(FlowLayout.LEFT, 3, 7));
		
		JLabel lblListOfUser = new JLabel("List of User Registration:");
		lblListOfUser.setHorizontalAlignment(SwingConstants.CENTER);
		lblListOfUser.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
		p1.add(lblListOfUser);
		
		p2 = new JPanel();
		contentPane.add(p2, BorderLayout.CENTER);
		p2.setLayout(new GridLayout(pendingList.size()*3, 2, 5,5));
		
		p4 = new JPanel();
		contentPane.add(p4, BorderLayout.SOUTH);
		p4.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		btnBack.setForeground(SystemColor.activeCaptionText);
		btnBack.setFont(new Font("Times New Roman", Font.BOLD, 16));
		p4.add(btnBack);
		
		btnBack.addActionListener(this);
	
		//check number of account
				if(pendingList.size() == 0){
					JOptionPane.showMessageDialog(null, "There is no account in the pending list currently.", "Message" ,JOptionPane.INFORMATION_MESSAGE);
					this.dispose();
					prevScreen.setVisible(true);
				}
				else{
					//for loop for pending user view	
					for(int i=0; i<pendingList.size(); i++){
						pendingMember pendinguser = pendingList.get(i);
						String label = pendinguser.getPendingUsername()+" ("+pendinguser.getPendingID()+")";
						String details =  "Name: "+pendinguser.getPendingFirstName()+" "+pendinguser.getPendingLastName();
						String emails = "Emails: "+pendinguser.getPendingEmail();
						
						//view user detail button
						JLabel lblView = new JLabel(label);
						lblView.setFont(new Font("Bell MT", Font.BOLD, 18));
						lblView.setForeground(new Color(30, 144, 255));
						JLabel lblDetails = new JLabel(details);
						lblDetails.setFont(new Font("Bell MT", Font.PLAIN, 17));
						JLabel lblEmails = new JLabel(emails);
						lblEmails.setFont(new Font("Bell MT", Font.PLAIN, 17));
						
						JButton btnAccept = new JButton("Accept");
						btnAccept.setFont(new Font("Times New Roman", Font.BOLD, 16));
						btnAccept.setPreferredSize(new Dimension(20,20));
						btnAccept.setForeground(new Color(50, 205, 50));
						
						JButton btnReject = new JButton("Reject");
						btnReject.setFont(new Font("Times New Roman", Font.BOLD, 16));
						btnReject.setPreferredSize(new Dimension(20,20));
						btnReject.setForeground(new Color(255, 0, 0));
						
						btnAcceptList[i] = btnAccept;
						btnRejectList[i] = btnReject;
						
						ButtonGroup group = new ButtonGroup();
						group.add(btnAccept);
						group.add(btnReject);
							
						JPanel p3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
						p3.add(new JLabel((i+1)+"."));
						p3.add(lblView);
						
						JPanel p3a = new JPanel(new FlowLayout(FlowLayout.LEFT));
						p3a.add(new JLabel("   "));
						p3a.add(lblDetails);
						
						JPanel p3b = new JPanel(new FlowLayout(FlowLayout.LEFT));
						p3b.add(new JLabel("   "));
						p3b.add(lblEmails);

						JPanel panelButton = new JPanel(new GridLayout(1,2, 10,4));
						panelButton.add(btnAccept);
						panelButton.add(btnReject);
						
						p2.add(p3,BorderLayout.CENTER);
						p2.add(new JLabel(" "));
						
						p2.add(p3a,BorderLayout.CENTER);
						p2.add(new JLabel(" "));
									
						p2.add(p3b,BorderLayout.CENTER);
						p2.add(panelButton);
			
						btnAccept.addActionListener(this);
						btnReject.addActionListener(this);
					}
					
					if(pendingList.size()<4 && pendingList.size()>0){
						int size = pendingList.size()*100;
						setSize(600,size+120);
						//pack();
						contentPane.add(p2, BorderLayout.CENTER);
					}else if(pendingList.size()>4){
						scroll= new JScrollPane(p2);
						scroll.setAutoscrolls(true);
						contentPane.add(scroll, BorderLayout.CENTER);
						}
				}
		}
		
		public void actionPerformed(ActionEvent e) {
			Object obj = e.getSource();
			
			if (obj == btnBack) {
				this.dispose();
				prevScreen.setVisible(true);
			}
			else {
				for (int j=0; j<btnList.length; j++) {
					if (obj == btnAcceptList[j]) {
						String memberUsername = pendingList.get(j).getPendingUsername();
						String Email = pendingList.get(j).getPendingEmail();
						String FirstName = pendingList.get(j).getPendingFirstName();
						String LastName = pendingList.get(j).getPendingLastName();
						String password = pendingList.get(j).getPassword();
						
						system.AddToMemberRecord(memberUsername, Email, FirstName, LastName, password);
						system.removeFromPendinglist(pendingList.get(j).getPendingID());
						
						JOptionPane.showMessageDialog(null, "Member account ("+pendingList.get(j).getPendingID()+") approved succesfully!", "Message" ,JOptionPane.INFORMATION_MESSAGE);
						this.dispose();
						new AdminApprove(prevScreen, system, admin);
					}
					if (obj == btnRejectList[j]) {
						system.removeFromPendinglist(pendingList.get(j).getPendingID());
						
						JOptionPane.showMessageDialog(null, "Member account ("+pendingList.get(j).getPendingID()+") rejected succesfully!", "Message" ,JOptionPane.INFORMATION_MESSAGE);
						this.dispose();
						new AdminApprove(prevScreen, system, admin);
					}
				}
			}
	}
}
