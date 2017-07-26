import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.mysql.jdbc.Connection;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import java.sql.*;

public class Bidbox {

	private JFrame frmItemid;
	private JTextField textField;
	private int buyerID,itemID;

	public Bidbox(int buyerID,int itemID) {
		this.buyerID=buyerID;
		this.itemID=itemID;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmItemid = new JFrame();
		frmItemid.setTitle("Bid item");
		frmItemid.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 17));
		frmItemid.getContentPane().setBackground(Color.CYAN);
		frmItemid.setBounds(100, 100, 450, 300);
		frmItemid.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmItemid.getContentPane().setLayout(null);
		
		JLabel lblBidAmount = new JLabel("Bid Amount");
		lblBidAmount.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblBidAmount.setHorizontalAlignment(SwingConstants.CENTER);
		lblBidAmount.setBounds(94, 11, 231, 64);
		frmItemid.getContentPane().add(lblBidAmount);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setBounds(94, 101, 231, 54);
		frmItemid.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Bid!");
		//btnNewButton.setAction(action);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int bidAmount=Integer.parseInt(textField.getText());
				int checker=checkBid(bidAmount);
				if(checker==1)
				{
					
					Mysql1 abc=new Mysql1();
					abc.setBid(bidAmount,itemID,buyerID);
					frmItemid.setVisible(false);
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton.setBounds(165, 207, 89, 43);
		frmItemid.getContentPane().add(btnNewButton);
		frmItemid.setVisible(true);
		
		
	}
	
	
	int checkBid(int bid)
	{
		
		Mysql1 abc=new Mysql1();
		return abc.checkBid(bid,this.itemID,this.buyerID);
	}

}
