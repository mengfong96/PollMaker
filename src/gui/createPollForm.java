package gui;
import logic.Member;
import logic.System;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;


@SuppressWarnings("serial")
public class createPollForm extends JFrame implements ActionListener{
	
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd:MM:yyyy HH:mm:ss");
	private System system; 
	private ArrayList<String> pollDetails;
	private Member member;
	private MainMenuForm prevScreen;
	
	private JPanel contentPane;
	
	private JTextField txt_pollTitle;
	
	private JButton btnNext; 
	private JButton btnCancel;
	
	private JSpinner num_option; 
	private JSpinner spi_day;
	private JSpinner spi_hour;
	private JSpinner spi_min;
	private JSpinner spinner_votelimit;
	
	private JLabel lblVoteLimit;
	private JLabel lbl_dateTime;
	private JLabel lblDays;
	private JLabel lblHours;
	private JLabel lblMins;

	
	public createPollForm(System system, Member member, MainMenuForm prevScreen) {
		setTitle("Create Poll");
		
		this.system = system;
		this.member = member;
		this.prevScreen = prevScreen;
		
		ImageIcon img = new ImageIcon("img/logo1.png");
		setIconImage(img.getImage());
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 680, 370);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(0, 51, 51));
		contentPane.setForeground(Color.LIGHT_GRAY);
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Create Poll");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Trajan Pro", Font.PLAIN, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(219, 13, 235, 45);
		contentPane.add(lblNewLabel);
		
		JLabel lblPollTitle = new JLabel("Poll Title (Question): ");
		lblPollTitle.setForeground(Color.WHITE);
		lblPollTitle.setBackground(Color.WHITE);
		lblPollTitle.setFont(new Font("Tw Cen MT", Font.PLAIN, 20));
		lblPollTitle.setBounds(70, 71, 180, 28);
		contentPane.add(lblPollTitle);
			
		JLabel lblPollDuration = new JLabel("Poll Duration: ");
		lblPollDuration.setForeground(Color.WHITE);
		lblPollDuration.setFont(new Font("Tw Cen MT", Font.PLAIN, 20));
		lblPollDuration.setBounds(113, 112, 111, 28);
		contentPane.add(lblPollDuration);
		
		JLabel lblNumberOfOption = new JLabel("No. of Option: ");
		lblNumberOfOption.setForeground(Color.WHITE);
		lblNumberOfOption.setFont(new Font("Tw Cen MT", Font.PLAIN, 20));
		lblNumberOfOption.setBounds(113, 153, 150, 28);
		contentPane.add(lblNumberOfOption);
		
		txt_pollTitle = new JTextField();
		txt_pollTitle.setHorizontalAlignment(SwingConstants.CENTER);
		txt_pollTitle.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 16));
		txt_pollTitle.setColumns(10);
		txt_pollTitle.setBounds(236, 71, 160, 28);
		contentPane.add(txt_pollTitle);
		
		btnNext = new JButton("Next");
		btnNext.setFont(new Font("Trajan Pro", Font.PLAIN, 18));
		btnNext.setBounds(348, 258, 138, 35);
		contentPane.add(btnNext);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Trajan Pro", Font.PLAIN, 18));
		btnCancel.setBounds(204, 258, 138, 35);
		contentPane.add(btnCancel);

		num_option = new JSpinner();
		num_option.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 16));
		num_option.setModel(new SpinnerNumberModel(new Integer(2), new Integer(2), null, new Integer(1)));
		num_option.setBounds(236, 153, 104, 28);
		contentPane.add(num_option);
		
		lbl_dateTime = new JLabel("");
		lbl_dateTime.setForeground(Color.WHITE);
		lbl_dateTime.setFont(new Font("Tw Cen MT", Font.PLAIN, 15));
		lbl_dateTime.setBounds(12, 306, 138, 16);
		updateClock(); 
		new Timer(1000, this).start();
		contentPane.add(lbl_dateTime);
		
		lblVoteLimit = new JLabel("Number of Vote:");
		lblVoteLimit.setForeground(Color.WHITE);
		lblVoteLimit.setFont(new Font("Tw Cen MT", Font.PLAIN, 20));
		lblVoteLimit.setBounds(94, 194, 150, 28);
		contentPane.add(lblVoteLimit);
		
		spi_day = new JSpinner();
		spi_day.setModel(new SpinnerNumberModel(0, 0, 20, 1));
		spi_day.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 16));
		spi_day.setBounds(236, 112, 47, 28);
		contentPane.add(spi_day);
		
		lblDays = new JLabel("Day(s)");
		lblDays.setForeground(Color.WHITE);
		lblDays.setFont(new Font("Tw Cen MT", Font.PLAIN, 20));
		lblDays.setBackground(Color.WHITE);
		lblDays.setBounds(286, 112, 54, 28);
		contentPane.add(lblDays);
		
		spi_hour = new JSpinner();
		spi_hour.setModel(new SpinnerNumberModel(0, 0, 480, 1));
		spi_hour.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 16));
		spi_hour.setBounds(338, 112, 47, 28);
		contentPane.add(spi_hour);
		
		lblHours = new JLabel("Hour(s)");
		lblHours.setForeground(Color.WHITE);
		lblHours.setFont(new Font("Tw Cen MT", Font.PLAIN, 20));
		lblHours.setBackground(Color.WHITE);
		lblHours.setBounds(389, 112, 65, 28);
		contentPane.add(lblHours);
		
		spi_min = new JSpinner();
		spi_min.setModel(new SpinnerNumberModel(0, 0, 28800, 1));
		spi_min.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 16));
		spi_min.setBounds(451, 112, 47, 28);
		contentPane.add(spi_min);
		
		lblMins = new JLabel("Min(s)");
		lblMins.setForeground(Color.WHITE);
		lblMins.setFont(new Font("Tw Cen MT", Font.PLAIN, 20));
		lblMins.setBackground(Color.WHITE);
		lblMins.setBounds(504, 112, 65, 28);
		contentPane.add(lblMins);
		
		spinner_votelimit = new JSpinner();
		spinner_votelimit.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		spinner_votelimit.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 16));
		spinner_votelimit.setBounds(236, 194, 104, 28);
		contentPane.add(spinner_votelimit);
		
		btnNext.addActionListener(this);
		btnCancel.addActionListener(this);
		
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
	
	public void resetField(){ 
			
			txt_pollTitle.setText("");
			spi_day.setValue(0);
			spi_hour.setValue(0);
			spi_min.setValue(0);
			num_option.setValue(2);
			spinner_votelimit.setValue(1);	
			updateClock();
		}
	
	public void actionPerformed(ActionEvent e) { 
		
		updateClock(); 
		Object obj = e.getSource(); 
		
		if(obj == btnNext) { 
			
			int spinnerDay = (int) spi_day.getValue(); 
			int spinnerHour = (int) spi_hour.getValue(); 
			int spinnerMin = (int) spi_min.getValue(); 
			
			if(txt_pollTitle.getText().isEmpty() || spinnerDay ==0 && spinnerHour ==0 && spinnerMin ==0 )
				JOptionPane.showMessageDialog(null, "Please input required field.","Error",JOptionPane.INFORMATION_MESSAGE);
			else{ 
				
				String tempPollTitle = txt_pollTitle.getText(); 
				String tempNoOfOption = String.valueOf(num_option.getValue()); 
				String tempVoteLimit =String.valueOf(spinner_votelimit.getValue());
				
				pollDetails = new ArrayList<String>(); 
				pollDetails.add(tempPollTitle); 
				pollDetails.add(String.valueOf(spinnerDay));
				pollDetails.add(String.valueOf(spinnerHour));
				pollDetails.add(String.valueOf(spinnerMin));
				pollDetails.add(tempNoOfOption);
				pollDetails.add(tempVoteLimit);
				
				this.setVisible(false);
				new createPoll2Form(system, member, prevScreen, this, pollDetails); 
			}
		}
		else if(obj == btnCancel){ 
			this.dispose(); 
			prevScreen.setVisible(true);
		}
		
	}
}