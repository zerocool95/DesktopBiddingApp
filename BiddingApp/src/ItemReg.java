import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;

public class ItemReg {

	private JFrame frame;
	private JTextField tfName;
	private JTextField tfDescription;
	private JTextField tfModelID;
	private JTextField tfAuctPrice;

	private String name, description, modelID, status="available";
	private int auctionPrice, sellingPrice, id, buyerID;// 0 for not sold; 1 for available; 2 for sold;
	 int sellerID;

	public ItemReg(int sellerID) {
		this.sellerID=sellerID;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.CYAN);
		frame.getContentPane().setLayout(null);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblName.setBounds(32, 56, 138, 47);
		frame.getContentPane().add(lblName);
		
		tfName = new JTextField();
		tfName.setBounds(229, 65, 163, 28);
		frame.getContentPane().add(tfName);
		tfName.setColumns(10);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblDescription.setBounds(22, 161, 163, 47);
		frame.getContentPane().add(lblDescription);
		
		tfDescription = new JTextField();
		tfDescription.setBounds(229, 150, 163, 80);
		frame.getContentPane().add(tfDescription);
		tfDescription.setColumns(10);
		
		tfModelID = new JTextField();
		tfModelID.setBounds(229, 252, 163, 20);
		frame.getContentPane().add(tfModelID);
		tfModelID.setColumns(10);
		
		JLabel lblModelId = new JLabel("Model ID");
		lblModelId.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblModelId.setBounds(35, 242, 157, 40);
		frame.getContentPane().add(lblModelId);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(239, 362, 89, 23);
		frame.getContentPane().add(btnSubmit);
				btnSubmit.addActionListener(new ActionListener()     //submit button action listner
				{
					public void actionPerformed(ActionEvent event)     
						{
							getItemInformation();
							try {
								registerItemData(sellerID);
								frame.setVisible(false);
								System.out.println("item "+id);
								
								Thread.sleep(30000);    //sleep main thread
								
								Mysql1 abc=new Mysql1();
								abc.setStatus(id);//set the item sold; get the payment done
								
					
							} catch (SQLException e) {
								e.printStackTrace();
							} 
							/*catch(InterruptedException ex) {
							    Thread.currentThread().interrupt();
							}*/
							//refreshItemData;
							catch (InterruptedException e) {
								e.printStackTrace();
							}
							
							
							/*Timer timer = new Timer(10 * 3000, new ActionListener() {
								  @Override
								  public void actionPerformed(ActionEvent arg0) {
								  }
								});
								timer.setRepeats(false); // Only execute once
								timer.start(); // Go go go!
								*/
							
						
								
						}
				});
		
		JButton btnClear = new JButton("Clear");
		btnClear.setBounds(349, 362, 89, 23);
		frame.getContentPane().add(btnClear);
		
		JLabel AuctionPricelbl = new JLabel("Auction Price");
		AuctionPricelbl.setFont(new Font("Tahoma", Font.PLAIN, 24));
		AuctionPricelbl.setBounds(22, 293, 168, 40);
		frame.getContentPane().add(AuctionPricelbl);
		
		tfAuctPrice = new JTextField();
		tfAuctPrice.setBounds(229, 303, 163, 20);
		frame.getContentPane().add(tfAuctPrice);
		tfAuctPrice.setColumns(10);
		frame.setBounds(100, 100, 579, 447);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 tfName.setText("");
				 tfModelID.setText("");
				 tfDescription.setText("");
				 tfAuctPrice.setText("");
			}
		});
		
		frame.setVisible(true);
	}
	public void getItemInformation() {
		 name = tfName.getText();
		 modelID = tfModelID.getText();
		 description = tfDescription.getText();
		 auctionPrice = Integer.parseInt(tfAuctPrice.getText());
		 System.out.println(name+ " "+ modelID +" "+description+" "+" ");
		 
	}
	
	public void registerItemData(int userID) throws SQLException {
		
		Mysql1 abc=new Mysql1();
		
		this.id=abc.addItem(name, description, modelID, status, auctionPrice, sellingPrice, id, userID);
		
		//System.out.println("item "+this.id);
	}
}
