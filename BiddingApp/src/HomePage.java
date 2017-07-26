import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import java.awt.ScrollPane;
import java.awt.BorderLayout;
import java.awt.Color;
//import java.awt.List;
import javax.swing.UIManager;
import java.awt.FlowLayout;
import java.awt.Dimension;

public class HomePage {

	private JFrame homepageFrame;
	private JTextField searchField;
	private JScrollPane scrollPane;
	private Timer timer,timer2;
	private JPanel CurItemsPane;
	private JScrollPane searchScrollPane;
	private JPanel searchPanel;
	private JLabel lblCredit;
	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://localhost:3306/test";
	   static final String USER = "root";
	   static final String PASS = "root";
	   
	    String name, username, password, securityQ, answer;
	    int credits, userID, contactNo;
	    
	    ArrayList<Item> itemList;
	    ArrayList<Item> SearchList;
		JTextField buyCredTestField;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
				try{
					HomePage window = new HomePage(0);
					window.homepageFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		
	

	/**
	 * Create the application.
	 * @throws SQLException 
	 */

	public HomePage(int userID) throws SQLException{
				
		itemList = new ArrayList<Item>();
		SearchList = new ArrayList<Item>();
		Mysql1 abc=new Mysql1();
		ResultSet rs=abc.getUserDetails(userID);
		
		
				Connection conn = null;
			      Statement stmt = null;
			      
				try {
					
					rs.next();
					this.userID=rs.getInt("userID");
					this.name=rs.getString("name");
					this.username=rs.getString("username");
					this.securityQ = rs.getString("question");
					this.answer = rs.getString("anwer");
					this.credits = rs.getInt("credits");
					this.contactNo = rs.getInt("contact");
				}
				catch (Exception e) {
					System.out.println("Exceptionhomepage is " + e);
				}
				
				homepageFrame = new JFrame();
				CurItemsPane = new JPanel();
				scrollPane = new JScrollPane();
				
				refreshItemPanel();
				
				initialize();
				
				int INTERVAL = 5000;

				timer = new Timer(INTERVAL, new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					refreshItemPanel(); 
				    }    
				});
				
					int INTERVAL2 = 2000;
				
				timer2 = new Timer(INTERVAL2, new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						//Mysql1 abc=new Mysql1();
						try {
							
							ResultSet rs=abc.getUserDetails(userID);
							rs.next();
							credits = rs.getInt("credits");
							//System.out.println("credits in timer:" + credits);
							lblCredit.setText(String.valueOf(credits));
							homepageFrame.revalidate();
						} catch (SQLException e) {
							e.printStackTrace();
						}

					    }    
					});
				timer2.start();
				timer.start();
				
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		homepageFrame = new JFrame();
		homepageFrame.getContentPane().setBackground(new Color(240, 255, 240));
		homepageFrame.setBounds(100, 100, 870, 600);
		homepageFrame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(32, 101, 106, -71);
		homepageFrame.getContentPane().add(lblNewLabel);
		
		JLabel welcomeLabel = new JLabel("Welcome");
		welcomeLabel.setFont(new Font("Coffee At Midnight Demo", Font.BOLD | Font.ITALIC, 24));
		welcomeLabel.setBounds(472, 109, 106, 38);
		homepageFrame.getContentPane().add(welcomeLabel);
		
		JLabel TitleLabel = new JLabel("Encheres");
		TitleLabel.setBackground(new Color(240, 240, 240));
		TitleLabel.setForeground(new Color(139, 0, 0));
		TitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		TitleLabel.setFont(new Font("Coffee At Midnight Demo", Font.BOLD | Font.ITALIC, 76));
		TitleLabel.setBounds(217, 11, 486, 86);
		homepageFrame.getContentPane().add(TitleLabel);
		
		searchField = new JTextField();
		searchField.setBackground(new Color(173, 255, 47));
		searchField.setBounds(70, 116, 221, 26);
		homepageFrame.getContentPane().add(searchField);
		searchField.setColumns(10);
		
		JButton SearchBtn = new JButton("Search");
		SearchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
				searchPanel.removeAll();
				
				//CurItemsPane.removeAll();
				String search=searchField.getText();
				String itemName, description, modelID,  status;
				int auctionPrice, sellingPrice, id, sellerID, buyerID;
				try {
					
					SearchList.clear();
					Mysql1 abc=new Mysql1();
					ResultSet rs=abc.getItemList();
					  
				           
				    while (rs.next()) {
				    	id = rs.getInt("id");
				    	sellerID = rs.getInt("sellerID");
				    	buyerID = rs.getInt("buyerID");
				    	auctionPrice = rs.getInt("auctionPrice");
				    	sellingPrice = rs.getInt("sellingPrice");
				    	status = rs.getString("status");
				    	modelID = rs.getString("modelID");
				    	description = rs.getString("description");
				    	itemName = rs.getString("name");
				        
				              
				    if (status.equalsIgnoreCase("Available") && sellerID != userID) 
				    	{
				    	StringTokenizer st = new StringTokenizer(description);
				        while (st.hasMoreTokens()) {
				            if(st.nextToken().equals(search))
				            	SearchList.add(new Item(itemName, description, modelID,  status, auctionPrice, sellingPrice, id, sellerID, userID));
				        }
				    		//itemList.add(new Item(itemName, description, modelID,  status, auctionPrice, sellingPrice, id, sellerID, userID));
				    	} 
				    
				    for(int i=0; i<SearchList.size(); i++)
					{
						SearchList.get(i).setPreferredSize(new Dimension(150,150));
						searchPanel.add(SearchList.get(i));
					}
				    
					//System.out.println(itemList.size());
					searchPanel.revalidate();
					searchScrollPane.setViewportView(searchPanel);
					homepageFrame.getContentPane().add(searchScrollPane);
				    homepageFrame.revalidate();
				    
				    }
				}catch (Exception e1) {
					System.out.println("ExceptionAddItemPanel is " + e1);
				}	
				
				
			}
		});
		SearchBtn.setBounds(297, 118, 89, 23);
		homepageFrame.getContentPane().add(SearchBtn);
		
		JButton btnSellItem = new JButton("Sell Item");
		btnSellItem.setBounds(535, 240, 146, 23);
		homepageFrame.getContentPane().add(btnSellItem);
		btnSellItem.addActionListener(new ActionListener()     //sellItem button action listner
				{
					public void actionPerformed(ActionEvent event)     
						{
						//System.out.println("user ID:"+userID);
						
							new ItemReg(userID);
							//refreshItemPanel();
						}
				});
		
		JButton btnNewButton = new JButton("Change Password");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ChangePassword(userID);
			}
		});
		btnNewButton.setBounds(535, 274, 146, 23);
		homepageFrame.getContentPane().add(btnNewButton);
		
		JLabel lblYouHave = new JLabel("You have ");
		lblYouHave.setForeground(new Color(255, 0, 0));
		lblYouHave.setFont(new Font("Coffee At Midnight Demo", Font.BOLD | Font.ITALIC, 24));
		lblYouHave.setBounds(457, 158, 122, 38);
		homepageFrame.getContentPane().add(lblYouHave);
		
		JLabel welcomeName = new JLabel(name);
		welcomeName.setFont(new Font("Coffee At Midnight Demo", Font.BOLD | Font.ITALIC, 24));
		welcomeName.setBackground(UIManager.getColor("Button.background"));
		welcomeName.setBounds(633, 114, 191, 26);
		//welcomeName.setText(this.name);
		homepageFrame.getContentPane().add(welcomeName);
		
		
		
		lblCredit = new JLabel(String.valueOf(this.credits));
		lblCredit.setForeground(new Color(255, 0, 0));
		lblCredit.setFont(new Font("Coffee At Midnight Demo", Font.BOLD | Font.ITALIC, 24));
		lblCredit.setHorizontalAlignment(SwingConstants.CENTER);
		lblCredit.setBounds(569, 164, 159, 26);
		homepageFrame.getContentPane().add(lblCredit);
		
		JLabel Credits = new JLabel("Credits!");
		Credits.setForeground(new Color(255, 0, 0));
		Credits.setFont(new Font("Coffee At Midnight Demo", Font.BOLD | Font.ITALIC, 24));
		Credits.setBounds(738, 165, 106, 25);
		homepageFrame.getContentPane().add(Credits);
		
		JButton btnBuyCredits = new JButton("Buy Credits");
		btnBuyCredits.setBounds(535, 206, 146, 23);
		homepageFrame.getContentPane().add(btnBuyCredits);
		
		btnBuyCredits.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{
					Mysql1 abc=new Mysql1();
					try {
						abc.buyCredits(Integer.parseInt(lblCredit.getText())+Integer.parseInt(buyCredTestField.getText()),userID);
					} catch (NumberFormatException e1) {
						e1.printStackTrace();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				
					lblCredit.setText(String.valueOf(Integer.parseInt(lblCredit.getText())+Integer.parseInt(buyCredTestField.getText())));
					
				}
				
		});
		
		
		buyCredTestField = new JTextField();
		buyCredTestField.setBackground(new Color(173, 255, 47));
		buyCredTestField.setBounds(718, 207, 86, 20);
		homepageFrame.getContentPane().add(buyCredTestField);
		buyCredTestField.setColumns(10);
		
		
		scrollPane.setBounds(0, 380, 854, 181);
		
		//CurItemsPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5))
		
		for(int i=0; i<itemList.size(); i++)
		{
			itemList.get(i).setPreferredSize(new Dimension(150,150));
			CurItemsPane.add(itemList.get(i));
		}
		
		CurItemsPane.revalidate();
		scrollPane.setViewportView(CurItemsPane);
		homepageFrame.getContentPane().add(scrollPane);
		
		searchScrollPane = new JScrollPane();
		searchScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		searchScrollPane.setBounds(10, 153, 437, 216);
		homepageFrame.getContentPane().add(searchScrollPane);
		
		searchPanel = new JPanel();
		searchScrollPane.setViewportView(searchPanel);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				homepageFrame.setVisible(false);
				new Login();
			}
		});
		btnLogout.setBounds(535, 346, 146, 23);
		homepageFrame.getContentPane().add(btnLogout);
		
		JButton btnAcoountDetails = new JButton("Account Details");
		btnAcoountDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Account(name, username, userID, contactNo);
			}
		});
		btnAcoountDetails.setBounds(535, 312, 146, 23);
		homepageFrame.getContentPane().add(btnAcoountDetails);
		
		
		homepageFrame.setVisible(true);
	}
	

	
	public void refreshItemPanel()
	{
		
		/*
		 * access the item data table
		 * while(rs.next())
		 * {
		 * 		if(status of item is available)
		 * 		{
		 * 			Item item = new Item(all para);
		 * 			scrollPane.setViewportView(Item);
		 * 		}
		 * }
		 */
		
		CurItemsPane.removeAll();
		
		String itemName, description, modelID,  status;
		int auctionPrice, sellingPrice, id, sellerID, buyerID;
		
		  Connection conn = null;
	      Statement stmt = null;
		try {
			
			itemList.clear();
			Mysql1 abc=new Mysql1();
			ResultSet rs=abc.getItemList();
			  
		            // Check Username and Password
		    while (rs.next()) {
		    	id = rs.getInt("id");
		    	sellerID = rs.getInt("sellerID");
		    	buyerID = rs.getInt("buyerID");
		    	auctionPrice = rs.getInt("auctionPrice");
		    	sellingPrice = rs.getInt("sellingPrice");
		    	status = rs.getString("status");
		    	modelID = rs.getString("modelID");
		    	description = rs.getString("description");
		    	itemName = rs.getString("name");
		        
		              
		    if (status.equalsIgnoreCase("Available") && sellerID != userID) 
		    	{
		    		itemList.add(new Item(itemName, description, modelID,  status, auctionPrice, sellingPrice, id, sellerID, userID));
		    	} 
		    
		    for(int i=0; i<itemList.size(); i++)
			{
				itemList.get(i).setPreferredSize(new Dimension(150,150));
				CurItemsPane.add(itemList.get(i));
			}
			
			//System.out.println(itemList.size());
			CurItemsPane.revalidate();
			scrollPane.setViewportView(CurItemsPane);
			homepageFrame.getContentPane().add(scrollPane);
		    homepageFrame.revalidate();
		    
		    }
		}catch (Exception e) {
			System.out.println("ExceptionAddItemPanel is " + e);
		}	
	}

}
