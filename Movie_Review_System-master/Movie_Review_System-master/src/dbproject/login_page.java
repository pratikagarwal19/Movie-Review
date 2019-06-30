package dbproject;
import java.sql.*;
import javax.swing.*;
import java.awt.EventQueue;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Font;

public class login_page extends JFrame {

	private JFrame frame;
	Connection conn=null;
	private static JTextField tf_un;
	private JPasswordField tf_pw;
	private JButton btnSubmit;
	private JLabel lbl_pw ;
	private JLabel lbl_un;
	private JRadioButton rdb_log; 
	private JRadioButton rdb_reg ;
	/**
	 * Launch the application.
	 */
	public static String getUserid()
	{
		return tf_un.getText();
	}
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login_page window = new login_page();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public login_page() {
		initialize();
		conn=database.getConnection();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 587, 358);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		rdb_reg = new JRadioButton("Register");
		rdb_reg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				tf_un.setText("");
				tf_pw.setText("");
				rdb_log.setSelected(false);
				tf_un.setVisible(true);
				tf_pw.setVisible(true);
				lbl_un.setVisible(true);
				lbl_pw.setVisible(true);
				btnSubmit.setVisible(true);
			}
		});
		rdb_reg.setBounds(92, 24, 109, 23);
		frame.getContentPane().add(rdb_reg);
		
		rdb_log = new JRadioButton("Login");
		rdb_log.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				tf_un.setText("");
				tf_pw.setText("");
				rdb_reg.setSelected(false);
				tf_un.setVisible(true);
				tf_pw.setVisible(true);
				lbl_un.setVisible(true);
				lbl_pw.setVisible(true);
				btnSubmit.setVisible(true);
			}
		});
		rdb_log.setBounds(304, 24, 109, 23);
		frame.getContentPane().add(rdb_log);
		
		tf_un = new JTextField();
		tf_un.setBounds(92, 101, 135, 31);
		frame.getContentPane().add(tf_un);
		tf_un.setColumns(10);
		tf_un.setVisible(false);
		
		tf_pw = new JPasswordField();
		tf_pw.setBounds(304, 101, 135, 31);
		frame.getContentPane().add(tf_pw);
		tf_pw.setVisible(false);
		
		lbl_un = new JLabel("Enter Username here");
		lbl_un.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lbl_un.setBackground(Color.BLACK);
		lbl_un.setForeground(Color.BLACK);
		lbl_un.setBounds(92, 143, 135, 14);
		frame.getContentPane().add(lbl_un);
		lbl_un.setVisible(false);
		
		lbl_pw = new JLabel("Enter password here");
		lbl_pw.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lbl_pw.setBackground(Color.BLACK);
		lbl_pw.setForeground(Color.BLACK);
		lbl_pw.setBounds(304, 143, 135, 14);
		frame.getContentPane().add(lbl_pw);
		lbl_pw.setVisible(false);
		
		btnSubmit = new JButton("Submit");
		btnSubmit.setIcon(new ImageIcon("C:\\Users\\vinsh\\Desktop\\ok.png"));
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				if(rdb_reg.isSelected())
				{
					try
					{
						JOptionPane.showConfirmDialog(null, "Are you sure?");
						if(tf_un.getText()=="" || tf_pw.getText()=="")
						JOptionPane.showMessageDialog(null, "Enter username and password");
						String query="insert into userdata (userid,password)values(?,?)";
						PreparedStatement pst=conn.prepareStatement(query);
						pst.setString(1, tf_un.getText());
						pst.setString(2, tf_pw.getText());
						pst.execute();
						
						JOptionPane.showMessageDialog(null, "Sucessfully Registered");
						
					}catch(Exception e)
					{
						JOptionPane.showMessageDialog(null, "Duplicate registration for the same username");
					}
					
				}
				else if(rdb_log.isSelected())
				{
					try
					{
						String query="select * from userdata where userid=? and password=?";
						PreparedStatement pst=conn.prepareStatement(query);
						pst.setString(1, tf_un.getText());
						pst.setString(2, tf_pw.getText());
						ResultSet rs=pst.executeQuery();
						int count=0;
						while(rs.next())
							count++;
						if(count==1)
						{
							JOptionPane.showMessageDialog(null, "Username and password is correct");
							frame.setVisible(false);
							Movie.main(null);
						}
						else if(count>1)
						{
							JOptionPane.showMessageDialog(null, "Duplicate username and password");
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Not Correct");
						}
						rs.close();pst.close();
						
					}catch(Exception e)
					{
						System.out.println(e);
					}
				}
			}
		});
		btnSubmit.setBounds(218, 196, 109, 31);
		frame.getContentPane().add(btnSubmit);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\vinsh\\Downloads\\tuxpi.com.1521892582.jpg"));
		lblNewLabel.setBounds(0, 0, 571, 319);
		frame.getContentPane().add(lblNewLabel);
		btnSubmit.setVisible(false);
	}
}
