import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;

public class Item extends JPanel {

	private String name, descrption, modelID,  status;
	private int auctionPrice, sellingPrice, id, sellerID, buyerID;
	
	private JButton BidNowBtn;
	private JLabel lblBidPrice;
	private JLabel itemName;
	private JLabel bidPrice;
	private final Action action = new SwingAction();
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescrption() {
		return descrption;
	}

	public void setDescrption(String descrption) {
		this.descrption = descrption;
	}

	public String getModelID() {
		return modelID;
	}

	public void setModelID(String modelID) {
		this.modelID = modelID;
	}

	public int getAuctionPrice() {
		return auctionPrice;
	}

	public void setAuctionPrice(int auctionPrice) {
		this.auctionPrice = auctionPrice;
	}

	public int getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(int sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSellerID() {
		return sellerID;
	}

	public void setSellerID(int sellerID) {
		this.sellerID = sellerID;
	}

	public int getBuyerID() {
		return buyerID;
	}

	public void setBuyerID(int buyerID) {
		this.buyerID = buyerID;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Create the panel.
	 */


	public Item(String name, String descrption, String modelID, String status, int auctionPrice, int sellingPrice, int id,
			int sellerID, int buyerID) {
		this.name = name;
		this.descrption = descrption;
		this.modelID = modelID;
		this.auctionPrice = auctionPrice;
		this.sellingPrice = sellingPrice;
		this.id = id;
		this.sellerID = sellerID;
		this.buyerID = buyerID;
		this.status = status;
	
		setLayout(null);
		
		itemName = new JLabel(this.name);
		itemName.setBounds(12, 12, 125, 15);
		add(itemName);
		
		lblBidPrice = new JLabel("Bid price");
		lblBidPrice.setBounds(12, 51, 70, 15);
		add(lblBidPrice);
		
		bidPrice = new JLabel(String.valueOf(this.sellingPrice));
		bidPrice.setBounds(69, 51, 70, 15);
		add(bidPrice);
		
		BidNowBtn = new JButton("Bid Now!");
		//BidNowBtn.setAction(action);
		BidNowBtn.setBounds(12, 122, 125, 25);
		add(BidNowBtn);
		BidNowBtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
				{
				//System.out.println("Inside action Listener!");

					new Bidbox(buyerID,id);
						
				}
		});
		

	}
	
	public void sellItem()
	{
		
	}
	
	
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			
		}
	}
}

