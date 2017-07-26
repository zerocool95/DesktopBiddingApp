import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.sql.*;

public class Login {

	private JFrame loginFrame;
	private JTextField usernameField;
	private JPasswordField passwordField;
	   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://localhost:3306/test";
	   static final String USER = "root";
	   static final String PASS = "root";
	   
	   String name;
	   String password;
	   String Username;
	   String databasePassword;
	   int userid;
	   int isPasswordOk=0;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.loginFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
		loginFrame.setVisible(true);
	}

	/**
	 * Initialize the contents of the loginFrame.
	 */
	private void initialize() {
		ImageIcon i1=new ImageIcon("SoldOut.jpg");    
		loginFrame = new JFrame();
		loginFrame.getContentPane().setFont(new Font("Coffee At Midnight Demo", Font.PLAIN, 39));
		loginFrame.getContentPane().setBackground(Color.WHITE);
		loginFrame.setBackground(Color.LIGHT_GRAY);
		//loginFrame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Rohan\\Downloads\\Capture.JPG"));
		loginFrame.setBounds(100, 100, 716, 681);
		loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginFrame.getContentPane().setLayout(null);
		
		JLabel LogoLabel = new JLabel(i1);
		LogoLabel.setBounds(36, 191, 356, 364);
		LogoLabel.setBackground(Color.WHITE);
		//LogoLabel.setIcon(new ImageIcon("./Encheres/Encheres/src/rsz_1rsz_154892547f0baaimage_1.jpg"));
		loginFrame.getContentPane().add(LogoLabel);
		
		JLabel TitleLabel = new JLabel("ench\u00E8res");
		TitleLabel.setForeground(new Color(139, 0, 0));
		TitleLabel.setFont(new Font("Coffee At Midnight Demo", Font.PLAIN, 79));
		TitleLabel.setBounds(177, 48, 305, 75);
		loginFrame.getContentPane().add(TitleLabel);
		
		usernameField = new JTextField();
		usernameField.setBackground(new Color(0, 255, 127));
		usernameField.setBounds(492, 285, 146, 26);
		loginFrame.getContentPane().add(usernameField);
		usernameField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBackground(new Color(0, 255, 127));
		passwordField.setBounds(492, 361, 146, 26);
		loginFrame.getContentPane().add(passwordField);
		
		JLabel UsernameLabel = new JLabel("Username");
		UsernameLabel.setForeground(new Color(139, 0, 0));
		UsernameLabel.setFont(new Font("Coffee At Midnight Demo", Font.PLAIN, 26));
		UsernameLabel.setBounds(363, 280, 119, 35);
		loginFrame.getContentPane().add(UsernameLabel);
		
		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setForeground(new Color(139, 0, 0));
		passwordLabel.setFont(new Font("Coffee At Midnight Demo", Font.PLAIN, 26));
		passwordLabel.setBounds(363, 361, 119, 23);
		loginFrame.getContentPane().add(passwordLabel);
		
		JButton RegisterButton = new JButton("Register");
		RegisterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginFrame.setVisible(false);
				new registration();
					
			}
		});
		RegisterButton.setBounds(537, 559, 89, 23);
		loginFrame.getContentPane().add(RegisterButton);
		
		JButton LoginButton = new JButton("Login");
		LoginButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
				{

				Mysql1 abc=new Mysql1();
			//	System.out.println("asdfsda");
				try {
					 
					userid=abc.CheckPassword(usernameField.getText(),String.valueOf(passwordField.getPassword()));
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
					//CheckPassword();
					System.out.println(userid);
					try { if(userid!=-1)
						{new HomePage(userid);
						loginFrame.setVisible(false);
						}
					else JOptionPane.showMessageDialog(null, "Enter correct details");} catch (SQLException e) {
						e.printStackTrace();
					}
				}
		});
		LoginButton.setBounds(415, 559, 89, 23);
		loginFrame.getContentPane().add(LoginButton);
	}
	
	public void setVisibility(boolean x)
	{
		loginFrame.setVisible(x);
	}

	public int CheckPassword() {
	
			Connection conn = null;
		      Statement stmt = null;
			try {
				  Class.forName("com.mysql.jdbc.Driver");
				  name=usernameField.getText();
				  password=String.valueOf(passwordField.getPassword());
			      conn = DriverManager.getConnection(DB_URL,USER,PASS);
			     // System.out.println(name+password);

			       stmt = conn.createStatement();
			    String SQL = "SELECT * FROM user_detail ;";

			    ResultSet rs = stmt.executeQuery(SQL);
			    
			            // Check Username and Password
			    while (rs.next()) {
			    	Username = rs.getString("username");
 			         databasePassword = rs.getString("password");
				      System.out.println(Username+" "+databasePassword);      
			    if (name.equalsIgnoreCase(Username) && password.equals(databasePassword)) 
			    	{
			    		isPasswordOk=1;
			    		this.userid=rs.getInt("userID");
			    		break;
			    	} 
			    }
			    
			    rs.close();
			    
			    if(isPasswordOk==1)
			    {
			    	System.out.println("Successful Login!\n----");  	 	
			    	isPasswordOk=0;
			    	return 1;
			    }
			}
			catch (Exception e) {
				System.out.println("ExceptionPassword is " + e);
			}
			return 0;
		}
	}