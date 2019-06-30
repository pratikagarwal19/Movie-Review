package dbproject;

import java.sql.*;
import javax.swing.*;
import java.awt.EventQueue;
import javax.swing.JFrame;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class mreview {

	private JFrame frame;
	private JTable table;
	Connection conn=null;

	/**
	 * Launch the application.
	 */
	private String getIdMovie()
	{
		try
		{
			String name=Movie.getDataTable();
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
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mreview window = new mreview();
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
	public mreview() {
		initialize();
		conn=database.getConnection();
		String idMovie=getIdMovie();
		String s=login_page.getUserid();
		
		
		try
		{
			String query="select rating,Description,Submitted_By from review where idReview in (select idReview from movie_has_reviews where idMovie=?) ";
			PreparedStatement pst=conn.prepareStatement(query);
			pst.setString(1, idMovie);
			ResultSet rs=pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
			/*String userid=login_page.getUserid();
			String query1="select * from review where Submitted_By=?";
			PreparedStatement pst1=conn.prepareStatement(query);
			pst1.setString(1, userid);
			ResultSet rs1=pst1.executeQuery();
			rs1.next();
			String rating=rs1.getString("rating");
			if(rating==null)
				lblNewLabel.setText("No Review Yet");
			else 
				{
					lblNewLabel.setText(rating);
					lblNewLabel_1.setText(rs.getString("Description"));
				}
			*/
			
			JButton btnBack = new JButton("Back");
			btnBack.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					frame.dispose();
					
				}
			});
			btnBack.setBounds(21, 365, 89, 23);
			frame.getContentPane().add(btnBack);
			
			JLabel lblNewLabel = new JLabel("New label");
			lblNewLabel.setBounds(24, 299, 46, 14);
			frame.getContentPane().add(lblNewLabel);
			lblNewLabel.setText(s);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 708, 486);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		table=new JTable();
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(21, 45, 606, 225);
		frame.getContentPane().add(scrollPane);
		
		JLabel lblReview = new JLabel("Reviews");
		lblReview.setBounds(21, 11, 83, 14);
		frame.getContentPane().add(lblReview);
	}
}
