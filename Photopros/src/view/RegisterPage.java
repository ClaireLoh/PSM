package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class RegisterPage extends JFrame {

	private JPanel contentPane;
	private JTextField textField_2;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterPage frame = new RegisterPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RegisterPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblRegistration = new JLabel("Registration");
		lblRegistration.setBounds(174, 11, 64, 14);
		contentPane.add(lblRegistration);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(47, 59, 46, 14);
		contentPane.add(lblName);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(29, 103, 64, 14);
		contentPane.add(lblPassword);
		
		JLabel lblEmail = new JLabel("E-Mail:");
		lblEmail.setBounds(47, 143, 46, 14);
		contentPane.add(lblEmail);
		
		JLabel lblPhoneno = new JLabel("PhoneNo:");
		lblPhoneno.setBounds(29, 189, 52, 14);
		contentPane.add(lblPhoneno);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(93, 140, 257, 26);
		contentPane.add(textField_2);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(93, 56, 257, 26);
		contentPane.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(93, 100, 257, 26);
		contentPane.add(textField_1);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(93, 186, 257, 26);
		contentPane.add(textField_3);
		
		JButton btnSignUp = new JButton("Sign Up");
		btnSignUp.setBounds(294, 223, 89, 23);
		contentPane.add(btnSignUp);
		
		JButton btnReset = new JButton("Reset");
		btnReset.setBounds(194, 223, 89, 23);
		contentPane.add(btnReset);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(93, 223, 89, 23);
		contentPane.add(btnBack);
	}
}
