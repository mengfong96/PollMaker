package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import logic.Poll;
import logic.System;
import gui.MainMenuForm;
import logic.Member;

public class DeletePollForm extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ArrayList<Poll> pollList = new ArrayList<Poll>();
	private System system;
	private Member member; 
	private JCheckBox[] cboxList; 
	private JButton btnBack, btnDelete; 
	private MainMenuForm prevScreen; 
	
	public DeletePollForm(System system, Member member, MainMenuForm prevScreen) {
		
		this.prevScreen = prevScreen; 
		this.system = system; 
		this.member = member; 
		
		
		ImageIcon img = new ImageIcon("img/logo1.png");
		setIconImage(img.getImage());
		setTitle("Delete Poll");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 680, 372);
		setVisible(true);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel titlePanel = new JPanel();
		contentPane.add(titlePanel, BorderLayout.NORTH);
		
		JLabel lblDeletePoll = new JLabel("Delete Poll");
		lblDeletePoll.setFont(new Font("Tw Cen MT", Font.BOLD, 30));
		titlePanel.add(lblDeletePoll);
		
		//Button
		JPanel p5 = new JPanel(new FlowLayout(FlowLayout.CENTER)); 
		btnBack = new JButton("Back to Main Menu"); 
		btnBack.setFont(new Font("Bell MT", Font.PLAIN, 18));
		btnDelete = new JButton("Delete Poll"); 
		btnDelete.setFont(new Font("Bell MT", Font.PLAIN, 18));
		
		p5.add(btnBack); 
		p5.add(btnDelete);	
		contentPane.add(p5, BorderLayout.SOUTH); 
		
		pollList = system.getMyPoll(member.getMemberID()); 
		
		JPanel p4 = new JPanel(new GridLayout(pollList.size(), 1));
		cboxList = new JCheckBox[pollList.size()]; 
		JScrollPane scroll= new JScrollPane(p4);
		scroll.setAutoscrolls(true);
		
		if(pollList.isEmpty()){
			JOptionPane.showMessageDialog(null, "You have to create your own poll to perform delete poll action. ", "Message" ,JOptionPane.INFORMATION_MESSAGE);
		}else{
			for(int i=0; i<pollList.size(); i++) { 
				
				JPanel p3 = new JPanel(new FlowLayout(FlowLayout.LEFT)); 
				
				Poll poll = pollList.get(i);
				String label = " (" + poll.getPollID()+") " + poll.getPollTitle(); 
				
				JCheckBox cboxAlone = new JCheckBox(label, false);
				cboxAlone.setFont(new Font("Bell MT", Font.PLAIN, 16));
				cboxList[i] = cboxAlone; 
				
				JLabel num = new JLabel((i+1) + "."); 
				num.setFont(new Font("Bell MT", Font.BOLD, 16));
				
				p3.add(num); 
				p3.add(cboxAlone); 
				p4.add(p3);  	
			}
			contentPane.add(scroll, BorderLayout.CENTER);
		}
		btnBack.addActionListener(this);
		btnDelete.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) { 
		Object obj = e.getSource(); 
		
		if(obj == btnBack) { 
			this.setVisible(false);
			prevScreen.setVisible(true);
			
		}else if(obj == btnDelete) { 
			//Check if member selected any option to delete
			boolean status = false;
			for(int i=0; i<cboxList.length; i++) { 
				if(cboxList[i].isSelected()){ 
					status = true;
					break; 
				}
			}
			
			if(status == false){
				JOptionPane.showMessageDialog(null, "Please select at least one option. ", "Message" ,JOptionPane.INFORMATION_MESSAGE);
			}else if(status == true){

				for(int i=0; i<cboxList.length; i++){
					 if(cboxList[i].isSelected()){
						system.removePollfromMember(member.getMemberID(),pollList.get(i).getPollID());
						JOptionPane.showMessageDialog(null, "Poll ("+pollList.get(i).getPollID()+") delete succesfully!", "Message" ,JOptionPane.INFORMATION_MESSAGE);
						}
					
				}
				this.dispose(); 
				new DeletePollForm(system, member, prevScreen); 	
			}
		}
	}
}
