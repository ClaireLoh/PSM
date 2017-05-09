package view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controller.LoginController;

public class LoginPage extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame loginFrame;
	private JLabel lblTitle;
	private JLabel label;
	private String [] lblNames = {"Username","Password"};
	private JTextField txtField;
	private JPasswordField passField;
	private String [] btnNames = {"Submit","Reset","Exit"};
	private JButton btn;
	private JLabel lblDot;

	public LoginPage()
	{	
		init();
		loginFrame.setVisible(true);
		//loginFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	public void init()
	{
		loginFrame = new JFrame();
		loginFrame.setLayout(null);
		loginFrame.setSize(400,200);
			lblTitle = new JLabel("Login Page");
			lblTitle.setBounds(150,10,80,30);
		loginFrame.add(lblTitle);
		int y=50;
		for(String name:lblNames)
		{
			label = new JLabel (name);
			label.setBounds(10,y,80,20);
			loginFrame.add(label);
			y+=30;
		}
		y=50;
		for(int i=0;i<lblNames.length;i++)
		{
			lblDot = new JLabel(":");
			lblDot.setBounds(72,y,3,20);
			loginFrame.add(lblDot);
			y+=30;
		}
		
	
			txtField = new JTextField(50);
			txtField.setBounds(80,50,250,20);
			loginFrame.add(txtField);
			
			passField = new JPasswordField();
			passField.setBounds(80,80,250,20);
			loginFrame.add(passField);
		
		
		int x=80;
		for(String n:btnNames)
		{
			btn = new JButton(n);
			btn.setBounds(x,120,80,20);
			btn.addActionListener(this);
			loginFrame.add(btn);
			x+=90;
		}
		
	}
	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand().equals("Submit"))
		{
			if(new LoginController().doLogin(txtField.getText(),passField.getText()))
			{
				new ImageSelector();
				loginFrame.dispose();
			}
		}
		else if(e.getActionCommand().equals("Exit"))
		{
			System.exit(0);
		}
		else if(e.getActionCommand().equals("Reset"))
		{
			txtField.setText("");
			passField.setText("");
		}
	}
	public static void main(String [] args)
	{
		new LoginPage();
	}
	
}