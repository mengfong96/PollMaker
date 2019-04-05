package gui;
import logic.Member;
import logic.System;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;

@SuppressWarnings("serial")
public class createPoll2Form extends JFrame implements ActionListener{
	
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd:MM:yyyy HH:mm:ss");
	private JPanel contentPane;
	private JButton btnBack; 
	private JButton btnSubmit;
	private JTextField[] OptionList; 
	private JLabel lbl_dateTime;
	
	private MainMenuForm mainmenu;
	private createPollForm prevScreen; 
	private System system; 
	private Member member;
	private ArrayList<String> pollDetails; 
	private ArrayList<String> pollOptions = new ArrayList<String>(); 

	public createPoll2Form(System system, Member member, MainMenuForm mainmenu, createPollForm prevScreen, ArrayList<String> pollDetails) {
		setTitle("Create Poll");
		
		this.system = system;
		this.member = member;
		this.mainmenu = mainmenu;
		this.prevScreen = prevScreen; 
		this.pollDetails = pollDetails; 
		
		ImageIcon img = new ImageIcon("img/logo1.png");
		setIconImage(img.getImage());
		setResizable(false);
		setBackground(new Color(0, 51, 51));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 680, 370);
		setVisible(true);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());
		
		JLabel label = new JLabel("Create Poll");
		label.setBackground(new Color(0, 51, 51));
		label.setForeground(Color.WHITE);
		label.setBounds(55, 38, 150, 21);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Trajan Pro", Font.PLAIN, 20));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel p1 = new JPanel(new BorderLayout()); 
		p1.setBackground(new Color(0, 51, 51));
		p1.add(label, BorderLayout.NORTH);
		contentPane.add(p1, BorderLayout.NORTH);
		
		JLabel lblPleaseAddYour = new JLabel("Please Add Your Poll Option: ");
		lblPleaseAddYour.setForeground(Color.WHITE);
		lblPleaseAddYour.setBackground(new Color(0, 51, 51));
		lblPleaseAddYour.setFont(new Font("Tw Cen MT", Font.BOLD, 20));
		p1.add(lblPleaseAddYour, BorderLayout.CENTER);
		
		lbl_dateTime = new JLabel("");
		lbl_dateTime.setForeground(Color.WHITE);
		lbl_dateTime.setFont(new Font("Tw Cen MT", Font.PLAIN, 15));
		updateClock(); 
		new Timer(1000, this).start();
		
		JPanel p2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		p2.setBackground(new Color(0, 51, 51));
		
		btnBack = new JButton("Back");
		btnBack.setFont(new Font("Trajan Pro", Font.PLAIN, 18));
		btnBack.setBounds(324, 347, 97, 25);
		
		
		btnSubmit = new JButton("Submit");
		btnSubmit.setFont(new Font("Trajan Pro", Font.PLAIN, 18));
		btnSubmit.setBounds(215, 347, 97, 25);
		
		p2.add(btnBack); 
		p2.add(btnSubmit);
		p2.add(lbl_dateTime);
		contentPane.add(p2, BorderLayout.SOUTH); 
		
		btnBack.addActionListener(this);
		btnSubmit.addActionListener(this);
		
		int option = Integer.parseInt(pollDetails.get(4));
		
		OptionList = new JTextField[option];
		
		JPanel p4 = new JPanel(new GridLayout(option, 1));
		p4.setBackground(new Color(0, 51, 51));
		
		for(int i=0; i<option; i++) { 
			
			JPanel p3 = new JPanel(new FlowLayout(FlowLayout.LEFT)); 
			p3.setBackground(new Color(0, 51, 51));
			JTextField textField = new JTextField(40); 
			textField.setSize(160, 50);
			
			OptionList[i] = textField; 
			
			JLabel num = new JLabel("	"+(i+1) + ".");
			num.setForeground(Color.WHITE);
			num.setFont(new Font("Tw Cen MT", Font.BOLD, 18));
			p3.add(num); 
			p3.add(textField);
			p4.add(p3); 
		}

		if(option > 5){
			setSize(680,450);
			JScrollPane scroll = new JScrollPane(p4); 
			scroll.setAutoscrolls(true);
			contentPane.add(scroll, BorderLayout.CENTER);
		}
		else{
			int size = option*70;
			setSize(680,size+100);
			contentPane.add(p4, BorderLayout.CENTER);
		}
		 
	}
	
	private void updateClock() {
		
		lbl_dateTime.setText(dateFormat.format(Calendar.getInstance().getTime()));
	}
	
	public String dateIncremental(int days, int hours, int min) { 
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"); 
		LocalDateTime expiredDate;
		
		if(days==0 && hours ==0 && min==0){
			return null; 
		}
		else{ 
			if(days==0 && hours==0) {
				expiredDate =LocalDateTime.now().plusMinutes(min);
				return dtf.format(expiredDate).toString(); 
			}
			else if(days==0 && min==0){
				expiredDate =LocalDateTime.now().plusHours(hours);
				return dtf.format(expiredDate).toString(); 
			}
			else if(hours==0 && min==0){
				expiredDate =LocalDateTime.now().plusDays(days);
				return dtf.format(expiredDate).toString(); 
			}
			else if(days==0){
				expiredDate =LocalDateTime.now().plusHours(hours).plusMinutes(min);
				return dtf.format(expiredDate).toString(); 
			}
			else if(hours==0){
				expiredDate =LocalDateTime.now().plusDays(days).plusMinutes(min);
				return dtf.format(expiredDate).toString(); 
			}
			else if(min==0){
				expiredDate =LocalDateTime.now().plusDays(days).plusHours(hours);
				return dtf.format(expiredDate).toString(); 
			}
			else{
				expiredDate =LocalDateTime.now().plusDays(days).plusHours(hours).plusMinutes(min);
				return dtf.format(expiredDate).toString(); 
			}
		}
}
	
	public void actionPerformed(ActionEvent e) { 
		updateClock();
		int option = Integer.parseInt(pollDetails.get(4));
		
		Object obj = e.getSource(); 
	
		if(obj == btnBack) { 
			this.setVisible(false);
				prevScreen.setVisible(true);	
			}
		else if(obj == btnSubmit) { 
				boolean status = true; 
				
			for(int i=0; i<option; i++) { 
				
				if(OptionList[i].getText().isEmpty()){ 
					JOptionPane.showMessageDialog(null, "Please fill in required field. ","Error",JOptionPane.INFORMATION_MESSAGE);
					status = false; 
					break; 
				}
			}
			
			if(status==true) { 
				
				for(int i=0; i<option; i++) { 
					String input = OptionList[i].getText(); 
					
					for(int j=0; j<option; j++) {
						if(input.equals(OptionList[j].getText()) && i != j){ 
							JOptionPane.showMessageDialog(null, "Repeated Options were used! ","Error",JOptionPane.INFORMATION_MESSAGE);
							status = false; 
							break; 
						}
					}
					break; 
				}
			}
			
			if(status == true) { 
				
				for(int i=0; i<option; i++) { 
					pollOptions.add(OptionList[i].getText()); 
				}
				//Create poll
				int spinnerDay = Integer.parseInt(pollDetails.get(1));
				int spinnerHour = Integer.parseInt(pollDetails.get(2));
				int spinnerMin = Integer.parseInt(pollDetails.get(3));
				String tempExpiredDate = dateIncremental(spinnerDay, spinnerHour, spinnerMin); 

				system.CreatePoll(pollDetails.get(0),tempExpiredDate ,pollDetails.get(5),pollOptions, member);
				JOptionPane.showMessageDialog(null, 
						"Your Poll Has been Created " + "\nPoll ID: "+ system.getPollID() + "\nExpired Date: " + system.getExpiredDate(),
						"Poll Created",JOptionPane.INFORMATION_MESSAGE);
				
				this.dispose();
				mainmenu.setVisible(true);
			}
		}
	}
}
