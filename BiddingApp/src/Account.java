
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;

public class Account {

	private JFrame frame;
	private String name, username;
	private int userID, contact;
	
	public Account(String name, String username, int userID, int contact) {
		this.name = name;
		this.username = username;
		this.userID = userID;
		this.contact = contact;
		initialize();
		frame.setVisible(true);
		
	}

	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.CYAN);
		frame.setBackground(Color.CYAN);
		frame.setBounds(100, 100, 452, 303);
		frame.getContentPane().setLayout(null);
		
		JLabel lblContact = new JLabel("Contact No");
		lblContact.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblContact.setBounds(47, 169, 139, 20);
		frame.getContentPane().add(lblContact);
		
		JLabel label = new JLabel("Name");
		label.setFont(new Font("Tahoma", Font.BOLD, 14));
		label.setBounds(47, 76, 139, 20);
		frame.getContentPane().add(label);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblUsername.setBounds(47, 107, 139, 20);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblUserId = new JLabel("User ID");
		lblUserId.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblUserId.setBounds(47, 138, 139, 20);
		frame.getContentPane().add(lblUserId);
		
		JLabel lblAccountDetails = new JLabel("Account Details");
		lblAccountDetails.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAccountDetails.setHorizontalAlignment(SwingConstants.CENTER);
		lblAccountDetails.setBounds(10, 11, 416, 36);
		frame.getContentPane().add(lblAccountDetails);
		
		JLabel name = new JLabel(this.name);
		name.setHorizontalAlignment(SwingConstants.CENTER);
		name.setFont(new Font("Times New Roman", Font.BOLD, 14));
		name.setBounds(215, 76, 192, 20);
		frame.getContentPane().add(name);
		
		JLabel username = new JLabel(this.username);
		username.setHorizontalAlignment(SwingConstants.CENTER);
		username.setFont(new Font("Times New Roman", Font.BOLD, 14));
		username.setBounds(215, 107, 192, 20);
		frame.getContentPane().add(username);
		
		JLabel userid = new JLabel(String.valueOf(this.userID));
		userid.setHorizontalAlignment(SwingConstants.CENTER);
		userid.setFont(new Font("Times New Roman", Font.BOLD, 14));
		userid.setBounds(215, 138, 192, 20);
		frame.getContentPane().add(userid);
		
		JLabel contactno = new JLabel(String.valueOf(this.contact));
		contactno.setHorizontalAlignment(SwingConstants.CENTER);
		contactno.setFont(new Font("Times New Roman", Font.BOLD, 14));
		contactno.setBounds(215, 170, 192, 20);
		frame.getContentPane().add(contactno);
		
		
	}
}
