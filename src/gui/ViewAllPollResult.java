package gui;

import java.awt.SystemColor;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import logic.Member;
import logic.Poll;
import logic.System;
import logic.VotingOption;

import javax.swing.JTabbedPane;
import java.awt.GridLayout;
import javax.swing.JList;

import java.awt.Font;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class ViewAllPollResult extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTabbedPane tabbedPane;
	private JList<?> list_votedPoll, list_myPoll;
	private TextArea textArea;
	private JButton btnBack;
	private MainMenuForm prevScreen;
	private ArrayList<Poll> voted, myPoll;
	/**
	 * Create the frame.
	 */
	public ViewAllPollResult(MainMenuForm prevScreen, System system, Member member) {
		this.prevScreen = prevScreen;
		ImageIcon img = new ImageIcon("img/logo1.png");
		setIconImage(img.getImage());
		setTitle("View Poll Result");
		setResizable(false);
		setBounds(100, 100, 857, 600);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("List of Poll Result");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Berlin Sans FB", Font.PLAIN, 24));
		lblNewLabel.setBounds(328, 13, 222, 42);
		contentPane.add(lblNewLabel);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tabbedPane.setBounds(12, 68, 827, 192);
		contentPane.add(tabbedPane);
		
		JPanel panel_myPoll = new JPanel();
		tabbedPane.addTab("My Poll Result", null, panel_myPoll, null);
		panel_myPoll.setLayout(new GridLayout(1, 0, 0, 0));
		
		//View Poll created 
		myPoll = system.getMyPoll(member.getMemberID());
		if(myPoll.size() > 0){
			String[] values = new String[myPoll.size()];
			for( int i=0; i<myPoll.size(); i++){
				Poll poll = myPoll.get(i);
				values[i] = "("+poll.getPollID() +") 	<"+poll.getPollTitle()+"> 	expired at "+poll.getExpiredDate();	
			}
			list_myPoll = new JList(values);
			list_myPoll.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			list_myPoll.setFont(new Font("Bell MT", Font.PLAIN, 18));
		
			JScrollPane scroll = new JScrollPane(list_myPoll);
			scroll.setAutoscrolls(true);
			panel_myPoll.add(scroll);
			
			list_myPoll.addListSelectionListener(new ListSelectionListener() {
			      public void valueChanged(ListSelectionEvent evt) {
			    	  list_myPollValueChanged(evt);
			      }
			     });
		}else{
			String result ="There is no available poll currently.";
			JLabel empty = new JLabel(result);
			empty.setFont(new Font("Bell MT", Font.PLAIN, 40));
			panel_myPoll.add(empty);
		}
	
		//Poll voted
		JPanel panel_votedPoll = new JPanel();
		tabbedPane.addTab("Voted Poll Result", null, panel_votedPoll, null);
		panel_votedPoll.setLayout(new GridLayout(1, 0, 0, 0));
		tabbedPane.setForegroundAt(0, SystemColor.controlDkShadow);
		tabbedPane.setForegroundAt(1, SystemColor.controlDkShadow);
		
		//View voted poll result
		voted = system.getVotedPoll(member.getMemberID());
		if(voted.size() > 0){
			String[] result = new String[voted.size()];
			for( int i=0; i<voted.size(); i++){
				Poll poll = voted.get(i);
				result[i] = "("+poll.getPollID() +") 	<"+poll.getPollTitle()+">";	
				}
			list_votedPoll = new JList(result);
			list_votedPoll.setFont(new Font("Bell MT", Font.PLAIN, 18));
			list_votedPoll.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			JScrollPane scroll1 = new JScrollPane(list_votedPoll);
			scroll1.setAutoscrolls(true);
			panel_votedPoll.add(scroll1);
			
			list_votedPoll.addListSelectionListener(new ListSelectionListener() {
			      public void valueChanged(ListSelectionEvent evt) {
			    	  list_votedPollValueChanged(evt);
			        }
			      });
		}else{
			String result ="There is no available poll currently.";
			JLabel empty = new JLabel(result);
			empty.setFont(new Font("Bell MT", Font.PLAIN, 40));
			panel_votedPoll.add(empty);
		}
		
		textArea = new TextArea();
		textArea.setColumns(2);
		textArea.setBackground(SystemColor.text);
		textArea.setForeground(SystemColor.activeCaptionText);
		textArea.setFont(new Font("Bell MT", Font.PLAIN, 18));
		textArea.setEditable(false);
		textArea.setBounds(12, 264, 827, 252);
		contentPane.add(textArea);
		
		btnBack = new JButton("Back");
		btnBack.setFont(new Font("Bell MT", Font.PLAIN, 18));
		btnBack.setBounds(375, 522, 100, 30);
		contentPane.add(btnBack);

		btnBack.addActionListener(this);	
	}
	
	 private void list_votedPollValueChanged(ListSelectionEvent evt) {
	
		    if (!list_votedPoll.getValueIsAdjusting()) {
		    	int index = list_votedPoll.getSelectedIndex();
		    	ArrayList<VotingOption> votes = voted.get(index).getAllVoteOptions();
		
		    	textArea.setColumns(2);
		    	textArea.setRows(votes.size()+2);
		    	textArea.setText("");
		    	textArea.append(" ("+voted.get(index).getPollID()+") Title: "+voted.get(index).getPollTitle()+"\n");
		    	for(int i=0;i<votes.size();i++){
		    		textArea.append(" Option "+(i+1)+": "+votes.get(i).getDescription()+" | Number of Vote = "+votes.get(i).getVoteCount()+"\n");
		    	}
		    	String total = "    Total Votes = "+String.valueOf(voted.get(index).getCurrentTotalVoteCount());
		    	textArea.append(total);
		    }
	 }
	 
	 private void list_myPollValueChanged(ListSelectionEvent evt) {
		
			if (!list_myPoll.getValueIsAdjusting()) {
		    	int index = list_myPoll.getSelectedIndex();
		    	ArrayList<VotingOption> votes = myPoll.get(index).getAllVoteOptions();
		
		    	textArea.setColumns(2);
		    	textArea.setRows(votes.size()+2);
		    	textArea.setText("");
		    	textArea.append(" ("+myPoll.get(index).getPollID()+") Title: "+myPoll.get(index).getPollTitle()+"\n");
		    	
		    	for(int i=0;i<votes.size();i++){
		    		textArea.append(" Option "+(i+1)+": "+votes.get(i).getDescription()+" | Number of Vote is "+votes.get(i).getVoteCount()+"\n");
		    	}
		    	
		    	String total = "    Total Votes = "+String.valueOf(myPoll.get(index).getCurrentTotalVoteCount());
		    	textArea.append(total);
		    }

		}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == btnBack){
			this.dispose();
			prevScreen.setVisible(true);
		}	
	}
}
