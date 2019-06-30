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

public class MovieDetails {

	private JFrame frame;
	private JTable table;
	private JTable table1;
	private JTable table2;
	private JTable table3;
	private JTable table4;
	Connection conn;
	private JLabel lblMovieDetails;
	private JLabel lblCastDetails;
	private JButton btnNewButton;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JButton btnNewButton_1; 
	private JButton btnNewButton_2;
	private JButton btnBack;
	private JLabel lblProducer;
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
	
	private String getIdReview()
	{
		try
		{
			String query="select * from review order by idReview desc limit 1";
			PreparedStatement pst=conn.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			rs.next();
			String s=rs.getString("idReview");
			return s;
		}
		catch(Exception e)
		{
			System.out.println(e);
			return  null;
		}
		
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MovieDetails window = new MovieDetails();
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
	public MovieDetails() {
		initialize();
		conn=database.getConnection();
		try
		{
			String name=Movie.getDataTable();
			String query="select * from movie where name=?";
			PreparedStatement pst=conn.prepareStatement(query);
			pst.setString(1, name);
			ResultSet rs=pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
			String idMovie=getIdMovie();
			String query1="select name from stars where idStars in (select idStar from movie_has_stars where idMovie=?)";
			PreparedStatement pst1=conn.prepareStatement(query1);
			pst1.setString(1, idMovie);
			ResultSet rs1=pst1.executeQuery();
			table1.setModel(DbUtils.resultSetToTableModel(rs1));
			
			String query2="select name from producer where idProducer in (select idProducer from movie_has_producer where idMovie=?)";
			PreparedStatement pst2=conn.prepareStatement(query2);
			pst2.setString(1, idMovie);
			ResultSet rs2=pst2.executeQuery();
			table2.setModel(DbUtils.resultSetToTableModel(rs2));
			
			String query3="select name from director where idDirector in (select idDirector from movie_has_director where idMovie=?)";
			PreparedStatement pst3=conn.prepareStatement(query3);
			pst3.setString(1, idMovie);
			ResultSet rs3=pst3.executeQuery();
			table3.setModel(DbUtils.resultSetToTableModel(rs3));
			
			String query4="select name from writer where idWriter in (select idWriter from movie_has_writer where idMovie=?)";
			PreparedStatement pst4=conn.prepareStatement(query4);
			pst4.setString(1, idMovie);
			ResultSet rs4=pst4.executeQuery();
			table4.setModel(DbUtils.resultSetToTableModel(rs4));
			
			lblMovieDetails = new JLabel("Movie Details");
			lblMovieDetails.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblMovieDetails.setBounds(10, 11, 106, 14);
			frame.getContentPane().add(lblMovieDetails);
			
			lblCastDetails = new JLabel("Cast Details");
			lblCastDetails.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblCastDetails.setBounds(10, 109, 83, 14);
			frame.getContentPane().add(lblCastDetails);
			
			btnNewButton = new JButton("Want to Enter a Review?");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					textField.setVisible(true);
					textField_1.setVisible(true);
					lblNewLabel.setVisible(true);
					lblNewLabel_1.setVisible(true);
					btnNewButton_1.setVisible(true);
				}
			});
			btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
			btnNewButton.setBounds(10, 410, 225, 29);
			frame.getContentPane().add(btnNewButton);
			
			textField = new JTextField();
			textField.setBounds(10, 450, 94, 29);
			frame.getContentPane().add(textField);
			textField.setColumns(10);
			textField.setVisible(false);
			
			textField_1 = new JTextField();
			textField_1.setColumns(10);
			textField_1.setBounds(144, 450, 537, 29);
			frame.getContentPane().add(textField_1);
			textField_1.setVisible(false);
			
			lblNewLabel = new JLabel("Enter Rating(out of 10)");
			lblNewLabel.setBounds(10, 490, 134, 14);
			frame.getContentPane().add(lblNewLabel);
			lblNewLabel.setVisible(false);
			
			lblNewLabel_1 = new JLabel("Enter Description");
			lblNewLabel_1.setBounds(144, 490, 106, 14);
			frame.getContentPane().add(lblNewLabel_1);
			lblNewLabel_1.setVisible(false);
			
			btnNewButton_1 = new JButton("Submit");
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try
					{
						String rating=textField.getText();
						String des=textField_1.getText();
						String query="insert into review(rating ,Description,Submitted_By) values(?,?,?)";
						PreparedStatement pst=conn.prepareStatement(query);
						pst.setString(1, rating);
						pst.setString(2, des);
						pst.setString(3, login_page.getUserid());
						pst.execute();
						
						String idMovie=getIdMovie();
						String idReview=getIdReview();
						String query1="insert into movie_has_reviews(idMovie,idReview) values(?,?)";
						PreparedStatement pst1=conn.prepareStatement(query1);
						pst1.setString(1, idMovie);
						pst1.setString(2, idReview);
						pst1.execute();
						JOptionPane.showMessageDialog(null, "Successfully Added the Review");
						
					}catch(Exception e1)
					{
						System.out.println(e1);
					}
					
				}
			});
			btnNewButton_1.setBounds(10, 515, 89, 23);
			frame.getContentPane().add(btnNewButton_1);
			
			btnNewButton_2 = new JButton("Click to see all reviews submitted for this movie yet");
			btnNewButton_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mreview.main(null);
				}
			});
			btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 14));
			btnNewButton_2.setBounds(245, 410, 397, 29);
			frame.getContentPane().add(btnNewButton_2);
			
			btnBack = new JButton("Back");
			btnBack.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					frame.dispose();
				}
			});
			btnBack.setBounds(10, 549, 89, 23);
			frame.getContentPane().add(btnBack);
			
			lblProducer = new JLabel("Producer");
			lblProducer.setBounds(10, 237, 56, 14);
			frame.getContentPane().add(lblProducer);
			
		
			
			JLabel lblDirector = new JLabel("Director");
			lblDirector.setBounds(245, 237, 56, 14);
			frame.getContentPane().add(lblDirector);
			
			
			
			JLabel lblRiter = new JLabel("Writer");
			lblRiter.setBounds(484, 237, 46, 14);
			frame.getContentPane().add(lblRiter);
			
			
			
		}catch(Exception e)
		{
			System.out.println(e);
		}
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 825, 633);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		table=new JTable();
		table1=new JTable();
		table2=new JTable();
		table3=new JTable();
		table4=new JTable();
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 29, 632, 60);
		frame.getContentPane().add(scrollPane);
		
		JScrollPane scrollPane_1 = new JScrollPane(table1);
		scrollPane_1.setBounds(10, 124, 632, 87);
		frame.getContentPane().add(scrollPane_1);
		
		JScrollPane sp = new JScrollPane(table2);
		sp.setBounds(10, 262, 184, 54);
		frame.getContentPane().add(sp);
		
		
		JScrollPane sp1 = new JScrollPane(table3);
		sp1.setBounds(228, 262, 184, 54);
		frame.getContentPane().add(sp1);
		
		JScrollPane sp2 = new JScrollPane(table4);
		sp2.setBounds(461, 262, 161, 54);
		frame.getContentPane().add(sp2);

	
	}
}
