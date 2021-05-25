package Project1;

import java.awt.event.ActionEvent;


import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.*;

import bean.Faculty;
import connection.Provider;

public class LoginPage extends JFrame implements ActionListener {
	

	private static final long serialVersionUID = 1L;
	JPanel p;
	JLabel usernamelabel,passwordlabel;
	JTextField username;
	JPasswordField password;
	JButton b1,b2,b3;
	
	public LoginPage(){
		
		setSize(500,250);
		//setResizable(false);
		setTitle("Login or Register New Faculty");
		setLocation(400,250);
		setResizable(false);
		
		p=new JPanel();
		p.setLayout(null);
		
		usernamelabel=new JLabel("Enter User Name : ");
		usernamelabel.setBounds(40, 10, 250, 80);
		p.add(usernamelabel);
		
		username=new JTextField();
		username.setBounds(180,30,250,30);
		p.add(username);
		
		passwordlabel=new JLabel("Enter Password : ");
		passwordlabel.setBounds(40,50,250,80);
		p.add(passwordlabel);
		
		password=new JPasswordField();
		password.setBounds(180,75,250,30);
		p.add(password);
		
		b1=new JButton("Login");
		b1.setBounds(100,140,100,25);
		b1.addActionListener(this);
		p.add(b1);
		
		b2=new JButton("Sign Up");
		b2.setBounds(210,140,100,25);
		b2.addActionListener(this);
		p.add(b2);
		
		b3=new JButton("Exit");
		b3.setBounds(320,140,100,25);
		b3.addActionListener(this);
		p.add(b3);
		
		add(p);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
	}
	public void actionPerformed(ActionEvent ae) {
		if(ae.getActionCommand().equals("Login")){
			if((username.getText().trim()).equals("") || password.getPassword().equals(""))
				JOptionPane.showMessageDialog(null, "Field cannot be left empty");
			else{
				
				try {
					Connection con=Provider.getConn();
					Statement stmt=con.createStatement();
			//		String str=username.getText();
					ResultSet rs=stmt.executeQuery("select * from userinfo");
					int count=0;
					try{
						while(rs.next()){
							if(rs.getString("username").equals(username.getText())){
								if(rs.getString("pwd1").equals(new String(password.getPassword()))){
									//set the values in faculty object
									
									Faculty fc=new Faculty();
									fc.setName(rs.getString("name"));
									fc.setUsername(rs.getString("username"));
									fc.setDesignation(rs.getString("designation"));
									fc.setPhone(rs.getString("phone"));
									fc.setEmail(rs.getString("email"));
									fc.setPwd1(rs.getString("pwd1"));
									
									Options ob=new Options(this,fc);
									this.setVisible(false);
									ob.setVisible(true);
									count=1;
									break;
								}
								else
								{
									JOptionPane.showMessageDialog(this, "Password does not match");
									password.setText("");
									count=1;
									break;
								}
							}
						}
						if(count==0){
							JOptionPane.showMessageDialog(this, "Access Denied");
							password.setText("");
							username.setText("");
						
						}
							
					}
					catch(NullPointerException np){
					
						JOptionPane.showMessageDialog(this, "Access Denied");
						password.setText("");
						username.setText("");
					}
					
					con.close();
					
				} 
				
				catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
		}
		else if(ae.getActionCommand().equals("Sign Up")){
			SignUp su=new SignUp(this);
			this.setVisible(false);
			su.setVisible(true);
		}
		else
		{
			System.exit(0);
		}
	}
	
	public static void main(String[] args) {	
		new LoginPage();

	}
}
