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

public class CastAddition {

	private JFrame frame;
	private JComboBox comboBox;
	private JLabel lblNewLabel_1;
	Connection conn=null;
	private JButton btnNewButton;
	private JTextField textField;
	private JLabel lblName;
	private JButton btnSubmit;
	private JButton btnBack;
	

	/**
	 * Launch the application.
	 */
	private String getIdStars()
	{
		try
		{
			String name=lblNewLabel_1.getText();
			String query="select * from stars where name=?";
			PreparedStatement pst=conn.prepareStatement(query);
			pst.setString(1, name);
			ResultSet rs=pst.executeQuery();
			rs.next();
			String s=rs.getString("idStars");
			return s;
			
		}catch(Exception e)
		{
			System.out.println(e);
			return null;
		}
	}
	
	private String getIdMovie()
	{
		try
		{
			String name=MovieAddition.getMovie();
			String query="select * from movie where name=?";
			PreparedStatement pst=conn.prepareStatement(query);
			pst.setString(1, name);
			ResultSet rs=pst.executeQuery();
			rs.next();
			String id=rs.getString("idMovie");
			return id;
		}
		catch(Exception e)
		{
			System.out.println(e);
			return null;
		}
	}
	
	private void fillComboBox()
	{
		
		comboBox.removeAllItems();
		try	
		{	
			
			String query="select * from Stars";
			PreparedStatement pst=conn.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				comboBox.addItem(rs.getString("Name"));
			}
			
		}catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
		}
	}
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CastAddition window = new CastAddition();
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
	public CastAddition() {
		initialize();
		conn=database.getConnection();
		fillComboBox();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 623, 399);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					String s=(String)comboBox.getSelectedItem();
					String query="select * from stars where name=?";
					PreparedStatement pst=conn.prepareStatement(query);
					pst.setString(1, s);
					ResultSet rs=pst.executeQuery();
					rs.next();
					lblNewLabel_1.setText(rs.getString("Name"));
				}
				catch(Exception e1)
				{
					System.out.println(e1);
				}
			}
		});
		comboBox.setBounds(47, 84, 176, 27);
		frame.getContentPane().add(comboBox);
		
		JLabel lblNewLabel = new JLabel("Enter lead cast");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel.setBounds(218, 25, 115, 27);
		frame.getContentPane().add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(47, 215, 102, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		btnNewButton = new JButton("Add New Cast");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSubmit.setVisible(true);
				lblName.setVisible(true);
				textField.setVisible(true);
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.setBounds(315, 86, 155, 25);
		frame.getContentPane().add(btnNewButton);
		
		textField = new JTextField();
		textField.setBounds(315, 147, 138, 27);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		textField.setVisible(false);
		
		lblName = new JLabel("Name");
		lblName.setBounds(315, 174, 46, 14);
		frame.getContentPane().add(lblName);
		lblName.setVisible(false);
		
		btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					String name=textField.getText();
					String query="insert into Stars(name) values(?)";
					PreparedStatement pst=conn.prepareStatement(query);
					pst.setString(1, name);
					pst.execute();
					JOptionPane.showMessageDialog(null, "Successfully inserted");
					fillComboBox();
					
				}
				catch(Exception e1)
				{
					System.out.println(e1);
				}
			}
		});
		btnSubmit.setBounds(315, 215, 89, 23);
		frame.getContentPane().add(btnSubmit);
		
		JButton btnNewButton_1 = new JButton("Add this cast");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
				
					String idMovie=getIdMovie();
					String idStars=getIdStars();
					String query1="insert into movie_has_stars(idMovie,idStar) values(?,?)";
					PreparedStatement pst1=conn.prepareStatement(query1);
					pst1.setString(1, idMovie);
					pst1.setString(2, idStars);
					pst1.execute();
					JOptionPane.showMessageDialog(null, "Succesfully added");
					
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		btnNewButton_1.setBounds(47, 240, 115, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnDone = new JButton("Next");
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CrewAddition.main(null);
			}
		});
		btnDone.setBounds(47, 274, 89, 23);
		frame.getContentPane().add(btnDone);
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		btnBack.setBounds(47, 307, 89, 23);
		frame.getContentPane().add(btnBack);
		btnSubmit.setVisible(false);
		
	}
}
