import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import java.sql.*;

public class registration {

	private JFrame registrationFrame;
	private JTextField nameField;
	private JTextField usernameField;
	private JTextField contactField;
	private JTextField answerField;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private int userID;
	private final Action action = new SwingAction();
	   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://localhost:3306/test";
	   static final String USER = "root";
	   static final String PASS = "root";
	   
	   
	/**
	 * Launch the application.
	 */
	   String name,username,question,answer,contact;
	char[] password;
	String questions[] = new String[50];
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					registration window = new registration();
					window.registrationFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public registration() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		registrationFrame = new JFrame();
		registrationFrame.setTitle("Encheres");
		registrationFrame.getContentPane().setBackground(Color.CYAN);
		registrationFrame.getContentPane().setForeground(Color.CYAN);
		registrationFrame.setBounds(100, 100, 612, 599);
		registrationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		registrationFrame.getContentPane().setLayout(null);
		
		JLabel RegistrationText = new JLabel("Registration");
		RegistrationText.setFont(new Font("Tahoma", Font.PLAIN, 17));
		RegistrationText.setForeground(Color.BLACK);
		RegistrationText.setBounds(74, 23, 297, 20);
		registrationFrame.getContentPane().add(RegistrationText);
		
		JLabel NameLabel = new JLabel("Name");
		NameLabel.setBounds(74, 85, 46, 14);
		registrationFrame.getContentPane().add(NameLabel);
		
		nameField = new JTextField();
		nameField.setBounds(361, 83, 110, 20);
		registrationFrame.getContentPane().add(nameField);
		nameField.setColumns(10);
		
		JLabel UsernameLabel = new JLabel("Username");
		UsernameLabel.setBounds(74, 127, 95, 20);
		registrationFrame.getContentPane().add(UsernameLabel);
		
		usernameField = new JTextField();
		usernameField.setBounds(361, 128, 86, 20);
		registrationFrame.getContentPane().add(usernameField);
		usernameField.setColumns(10);
		
		JLabel PasswordLabel = new JLabel("Password");
		PasswordLabel.setBounds(74, 168, 95, 14);
		registrationFrame.getContentPane().add(PasswordLabel);
		
		JLabel ReenterPasswordLabel = new JLabel("Re-Enter Password");
		ReenterPasswordLabel.setBounds(74, 193, 153, 14);
		registrationFrame.getContentPane().add(ReenterPasswordLabel);
		
		JLabel ContactDetailsLabel = new JLabel("Contact No.");
		ContactDetailsLabel.setBounds(74, 243, 106, 14);
		registrationFrame.getContentPane().add(ContactDetailsLabel);
		
		contactField = new JTextField();
		contactField.setBounds(361, 241, 86, 20);
		registrationFrame.getContentPane().add(contactField);
		contactField.setColumns(10);
		
		JComboBox securityComboBox = new JComboBox();
		
		securityComboBox.setBounds(361, 296, 173, 20);
		registrationFrame.getContentPane().add(securityComboBox);
		questions[0] = "In what city were you born?";
		questions[1] = "What is the name of your first school?";
		questions[2] = "What is your favorite movie?";
		questions[3] = "What is your favorite color?";
		questions[4] = "What is the name of your favorite pet?";

		for (int j= 0 ; j< 5; j++) {
			securityComboBox.addItem(questions[j]);
		}
		
		securityComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event)
			{
				Object selectedStateobj = securityComboBox.getSelectedItem();
				question = String.valueOf(selectedStateobj);
				//index = combobox1.getSelectedIndex();

			}
		});
		JLabel SecurityQuestionLabel = new JLabel("Security Question");
		SecurityQuestionLabel.setBounds(74, 299, 197, 14);
		registrationFrame.getContentPane().add(SecurityQuestionLabel);
		
		JLabel AnswerLabel = new JLabel("Answer");
		AnswerLabel.setBounds(74, 345, 128, 14);
		registrationFrame.getContentPane().add(AnswerLabel);
		
		answerField = new JTextField();
		answerField.setColumns(10);
		answerField.setBounds(361, 343, 86, 20);
		registrationFrame.getContentPane().add(answerField);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener()     //submit button action listner
		{
			public void actionPerformed(ActionEvent event)     
				{

					getUserInformation();
					registerUserData();
					registrationFrame.setVisible(false);
					new Login();

				}
		});
		btnSubmit.setBounds(91, 437, 153, 23);
		registrationFrame.getContentPane().add(btnSubmit);
		
		JButton btnClearDetails = new JButton("Clear details");
		btnClearDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 nameField.setText("");
				 passwordField_1.setText("");
				 passwordField.setText("");
				  usernameField.setText("");
				 contactField.setText("");
				  answerField.setText("");
			}
		});
		btnClearDetails.setBounds(319, 437, 164, 23);
		registrationFrame.getContentPane().add(btnClearDetails);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(361, 191, 110, 19);
		registrationFrame.getContentPane().add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(361, 166, 110, 19);
		registrationFrame.getContentPane().add(passwordField_1);
		
		registrationFrame.setVisible(true);
		
	}
	public void getUserInformation() {
		 name = nameField.getText();
		 password = passwordField_1.getPassword();
		 username = usernameField.getText();
		 contact = contactField.getText();
		 answer = answerField.getText();
		// User userObj=new User();
		//	userObj.setUserID();
			// userID=userObj.getUserID();
			// System.out.println("UserID:"+ userID);

	}

	public void registerUserData() {
		getUserInformation();
		Mysql1 abc=new Mysql1();
		try {
			abc.add(username,String.valueOf(passwordField.getPassword()),question,answer,name,Integer.toString(userID),contact);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	private class SwingAction extends AbstractAction {
		public void actionPerformed(ActionEvent e) {
			
		}
	}
}
