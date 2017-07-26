import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class ChangePassword {

	private JFrame frame;
	private JTextField tfNewPass;
	private JTextField tfConfirmPass;
	
	private int userID;
	private String password,cfPassword;

	private JButton btnNewButton;

	public ChangePassword(int x) {
		this.userID=x;
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(0, 255, 255));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewPassword = new JLabel("New Password");
		lblNewPassword.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblNewPassword.setBounds(20, 68, 151, 28);
		frame.getContentPane().add(lblNewPassword);
		
		tfNewPass = new JTextField();
		tfNewPass.setBounds(180, 76, 227, 20);
		frame.getContentPane().add(tfNewPass);
		tfNewPass.setColumns(10);
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password");
		lblConfirmPassword.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblConfirmPassword.setBounds(10, 122, 161, 43);
		frame.getContentPane().add(lblConfirmPassword);
		
		tfConfirmPass = new JTextField();
		tfConfirmPass.setBounds(180, 137, 227, 20);
		frame.getContentPane().add(tfConfirmPass);
		tfConfirmPass.setColumns(10);
		
		btnNewButton = new JButton("Submit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				password=String.valueOf(tfNewPass.getText());
				cfPassword=String.valueOf(tfConfirmPass.getText());
				System.out.println(password +"     "+ cfPassword);
				if(password.equals(cfPassword))
				{
					Mysql1 abc=new Mysql1();
					abc.setPassword(password,userID);
					
					JOptionPane.showMessageDialog(null, "Password successfully changed !");
					frame.setVisible(false);
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Confirm Password doesnt match with Password. Please Try again!");
				}
			}
		});
		btnNewButton.setBounds(197, 199, 151, 23);
		frame.getContentPane().add(btnNewButton);
	}

}

