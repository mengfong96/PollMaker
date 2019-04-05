package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import logic.Member;
import logic.Poll;
import logic.System;
import logic.VotingOption;

public class VoteForm extends JFrame implements ActionListener, ItemListener {
	
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd:MM:yyyy HH:mm:ss");
	private JPanel contentPane;
	private JTextField txt_id;
	private JButton btnConfirm, btnCancel, btnVote;
	private JLabel lblEnterPollId, totalvote,lbl_dateTime;
	private JScrollPane scroll;
	private JRadioButton[] optionlist;
	private int votelimit = 0;
	
	private System system;
	private Member member;
	private MainMenuForm prevScreen;
	private Poll poll;
	
	public VoteForm(System system, Member member, MainMenuForm prevScreen) {
	
		this.system = system;
		this.member = member;
		this.prevScreen = prevScreen;
		
		ImageIcon img = new ImageIcon("img/logo1.png");
		setIconImage(img.getImage());
		setTitle("Vote");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 574, 100);
		setLocationRelativeTo(null);
		setVisible(true);
	
		contentPane = new JPanel(new BorderLayout());
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
	
		txt_id = new JTextField();
		txt_id.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txt_id.setColumns(15);
		
		lbl_dateTime = new JLabel("");
		lbl_dateTime.setFont(new Font("Tw Cen MT", Font.PLAIN, 15));
		updateClock(); 
		new Timer(1000, this).start();
		
		lblEnterPollId = new JLabel("Enter Poll ID:");
		lblEnterPollId.setFont(new Font("Bell MT", Font.PLAIN, 18));
		
		btnConfirm = new JButton("Confirm");
		btnConfirm.setFont(new Font("Bell MT", Font.PLAIN, 16));
		
		btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Bell MT", Font.PLAIN, 16));
		
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panel.add(lblEnterPollId);
		panel.add(txt_id);
		panel.add(btnConfirm);
		panel.add(btnCancel);
		contentPane.add(panel, BorderLayout.NORTH);
		
		btnVote = new JButton("Vote");
		btnVote.setFont(new Font("Bell MT", Font.BOLD, 15));
		btnVote.setForeground(Color.blue);
		
		totalvote = new JLabel();
		totalvote.setFont(new Font("Tw Cen MT", Font.BOLD, 15));
		
		btnConfirm.addActionListener(this);
		btnCancel.addActionListener(this);
		btnVote.addActionListener(this);
	}
	
	public void showPoll(ArrayList<VotingOption> option, Poll poll){
		
		JLabel lbl_Title = new JLabel(poll.getPollTitle());
		lbl_Title.setBounds(230, 72, 78, 25);
		lbl_Title.setFont(new Font("Bell MT", Font.BOLD, 20));
		lbl_Title.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel p2 = new JPanel(new BorderLayout());
		p2.add(lbl_Title,BorderLayout.NORTH);
		
		JPanel panel = new JPanel((new GridLayout(option.size(), 0)));
		panel.setBounds(12, 110, 532, 337);
		contentPane.add(panel);
		optionlist = new JRadioButton[option.size()];
		ButtonGroup group = new ButtonGroup();
		
		for(int i=0; i<option.size(); i++) { 
		
			JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT)); 
			JRadioButton radio = new JRadioButton(option.get(i).getDescription()); 
			radio.setFont(new Font("Bell MT", Font.PLAIN, 14));
			optionlist[i] = radio; 
			radio.addItemListener(this);
			radio.addActionListener(this);
			
			p.add(radio);
			panel.add(p,BorderLayout.CENTER); 
			group.add(radio);
			
		}
		
		if(option.size() > 15){
			setSize(574,600);
			p2.add(panel);
			scroll= new JScrollPane(p2);
			scroll.setAutoscrolls(true);
			contentPane.add(scroll, BorderLayout.CENTER);
		}
		else{
			int size = option.size()*50;
			setSize(574,size+100);
			p2.add(panel);
			contentPane.add(p2, BorderLayout.CENTER);
		}
		totalvote.setText("Vote left: "+String.valueOf(votelimit));
		
		JPanel p5 = new JPanel(new FlowLayout());
		p5.add(lbl_dateTime,FlowLayout.LEFT);
		p5.add(btnVote,FlowLayout.CENTER);
		p5.add(totalvote,FlowLayout.RIGHT);
		
		contentPane.add(p5, BorderLayout.SOUTH);
	}
	
	private void updateClock() {
			lbl_dateTime.setText(dateFormat.format(Calendar.getInstance().getTime()));
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		updateClock();
		
		if(obj.equals(btnConfirm)){
			String pollID = txt_id.getText();
			
			if(pollID == "")
				JOptionPane.showMessageDialog(null, "Please enter Poll ID.", "Error ", JOptionPane.INFORMATION_MESSAGE);
			else{
				if(!system.isPollIDvalid(pollID)){
					JOptionPane.showMessageDialog(null, "Invalid Poll ID. Please try again.", "Error ", JOptionPane.INFORMATION_MESSAGE);
				}else {
					poll = system.getPoll(pollID);
					String expiredDate = poll.getExpiredDate();
					votelimit = poll.getvoteLimit() - poll.getCurrentTotalVoteCount();
					member = system.getMember(member.getMemberUsername(), member.getPassword());
					
					if(member.pollOwned(poll.getPollID())){
						JOptionPane.showMessageDialog(null, "You are not egligible to vote.", "Error ", JOptionPane.INFORMATION_MESSAGE);
					}else if(system.isPollExpired(expiredDate)){
						JOptionPane.showMessageDialog(null, "This poll has expired.", "Error ", JOptionPane.INFORMATION_MESSAGE);
					}else if(poll.getvoteLimit() == poll.getCurrentTotalVoteCount()){
						JOptionPane.showMessageDialog(null, "This poll vote limit has achieved.", "Error ", JOptionPane.INFORMATION_MESSAGE);
					}else if(poll.hasVoted(member.getMemberID())){
						JOptionPane.showMessageDialog(null, "You have voted.", "Error ", JOptionPane.INFORMATION_MESSAGE);
					}else{
						showPoll(poll.getAllVoteOptions(),poll);
					}
				}
			}
		}
		else if(obj.equals(btnCancel)){
			this.dispose();
			prevScreen.setVisible(true);
		}
		else if(obj.equals(btnVote)){
			boolean status = false;
			for(int i=0; i<optionlist.length; i++ ){
				if(optionlist[i].isSelected())
					status = true;
			}
			if(status == false){
				JOptionPane.showMessageDialog(null, "Please select one option before voting.", "Error ", JOptionPane.INFORMATION_MESSAGE);
			}
			if(status == true){
				int dialogresult = JOptionPane.showConfirmDialog(null, "Are you that you want to vote this option?", "Confirmation", JOptionPane.YES_NO_OPTION);
			
				 if(dialogresult == 0){
					 for(int i=0; i<optionlist.length; i++ ){
						 
						 if(optionlist[i].isSelected()){
							 system.addVote(poll, poll.getAllVoteOptions().get(i));
							 votelimit = votelimit - system.getAllVoteCount(poll);
							 totalvote.setText(String.valueOf(votelimit));
							 system.recordVoter(member,poll);
							 JOptionPane.showMessageDialog(null, "Your vote are recorded successfully", "Vote Recorded", JOptionPane.INFORMATION_MESSAGE);
							 this.dispose();
							 prevScreen.setVisible(true);
						 }
					 }	
				 }
			}
		}	 
	}
	@Override
	public void itemStateChanged(ItemEvent e) {
	}
}

