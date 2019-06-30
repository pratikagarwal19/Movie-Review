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

public class MovieAddition {

	private JFrame frame;
	private static JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JLabel lblDuration ;
	Connection conn=null;
	private JButton btnBack;

	/**
	 * Launch the application.
	 */
	
	
	
	public static String getMovie()
	{
		return textField.getText();
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MovieAddition window = new MovieAddition();
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
	public MovieAddition() {
		initialize();
		conn=database.getConnection();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 697, 465);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(28, 59, 119, 30);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(28, 34, 46, 14);
		frame.getContentPane().add(lblName);
		
		JLabel lblRelease = new JLabel("Release Date(YYYY-MM-DD)");
		lblRelease.setBounds(28, 100, 169, 14);
		frame.getContentPane().add(lblRelease);
		
		textField_1 = new JTextField();
		textField_1.setBounds(28, 125, 119, 30);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		lblDuration = new JLabel("Duration");
		lblDuration.setBounds(28, 179, 60, 14);
		frame.getContentPane().add(lblDuration);
		
		textField_2 = new JTextField();
		textField_2.setBounds(28, 204, 119, 30);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblRating = new JLabel("Rating");
		lblRating.setBounds(468, 34, 46, 14);
		frame.getContentPane().add(lblRating);
		
		textField_3 = new JTextField();
		textField_3.setBounds(468, 59, 119, 30);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblGrossincome = new JLabel("Gross-Income");
		lblGrossincome.setBounds(468, 100, 119, 14);
		frame.getContentPane().add(lblGrossincome);
		
		textField_4 = new JTextField();
		textField_4.setBounds(468, 130, 119, 30);
		frame.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setBounds(468, 192, 60, 14);
		frame.getContentPane().add(lblDescription);
		
		textField_5 = new JTextField();
		textField_5.setBounds(468, 217, 203, 183);
		frame.getContentPane().add(textField_5);
		textField_5.setColumns(10);
		
		JButton btnNewButton = new JButton("Next");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				
				try {
					String query="insert into movie(Name,Release_date,Duration,rating,Gross_Income,Description) values(?,?,?,?,?,?)";
					PreparedStatement pst=conn.prepareStatement(query);
					pst.setString(1, textField.getText());
					pst.setString(2, textField_1.getText());
					pst.setString(3, textField_2.getText());
					pst.setString(4, textField_3.getText());
					pst.setString(5, textField_4.getText());
					pst.setString(6, textField_5.getText());
					pst.execute();
					JOptionPane.showMessageDialog(null, "Successfully Added");
					CastAddition.main(null);
					
				}
				catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, e1);
				}
				
				
			}
		});
		btnNewButton.setFont(new Font("Monotype Corsiva", Font.BOLD, 28));
		btnNewButton.setBounds(260, 146, 119, 37);
		frame.getContentPane().add(btnNewButton);
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnBack.setBounds(28, 377, 89, 23);
		frame.getContentPane().add(btnBack);
	}
}
